package org.sherman.interview.java;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Denis M. Gabaydulin
 * @since 17.02.19
 */
public class DeadlockTest {
    private static final Logger log = LoggerFactory.getLogger(DeadlockTest.class);

    @Test
    public void classicalDeadlock() {
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        Account from = new Account(42L, 1000000000);
        Account to = new Account(43L, 1000000000);

        int tries = 64;
        List<Future<?>> results = new ArrayList<>(tries * 2);
        for (int i = 0; i < tries; i++) {
            results.add(executorService.submit(
                () -> {
                    try {
                        TransferService.transferMoney(from, to, 1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            ));

            results.add(executorService.submit(
                () -> {
                    try {
                        TransferService.transferMoney(to, from, 1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            ));
        }

        results.forEach(f -> {
            try {
                f.get(5, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                throw new IllegalStateException(e);
            }
        });

        executorService.shutdown();
    }

    @Test
    public void noDeadlock() {
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        Account from = new Account(42L, 1000000000);
        Account to = new Account(43L, 1000000000);

        int tries = 64;
        List<Future<?>> results = new ArrayList<>(tries * 2);
        for (int i = 0; i < tries; i++) {
            results.add(executorService.submit(
                () -> {
                    try {
                        TransferService.transferMoneyNoDeadlock(from, to, 1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            ));

            results.add(executorService.submit(
                () -> {
                    try {
                        TransferService.transferMoneyNoDeadlock(to, from, 1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            ));
        }

        results.forEach(f -> {
            try {
                f.get(5, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                throw new IllegalStateException(e);
            }
        });

        executorService.shutdown();
    }

    private static class TransferService {
        private TransferService() {
        }

        public static void transferMoney(Account from, Account to, long amount) throws InterruptedException {
            log.info("Try lock {}", from);
            synchronized (from) {
                log.info("Locked {}", from);
                Thread.sleep(1);
                log.info("Try lock {}", to);
                synchronized (to) {
                    log.info("Locked {}", to);
                    Thread.sleep(1);
                    Preconditions.checkArgument(from.amount >= amount);
                    from.setAmount(from.getAmount() - amount);
                    to.setAmount(to.getAmount() + amount);
                }
            }
        }

        public static void transferMoneyNoDeadlock(Account from, Account to, long amount) throws InterruptedException {
            Account acc1, acc2;

            if (from.getId() >= to.getId()) {
                acc1 = from;
                acc2 = to;
            } else {
                acc1 = to;
                acc2 = from;
            }

            synchronized (acc1) {
                Thread.sleep(1);
                synchronized (acc2) {
                    Thread.sleep(1);
                    Preconditions.checkArgument(from.amount >= amount);
                    from.setAmount(from.getAmount() - amount);
                    to.setAmount(to.getAmount() + amount);
                }
            }
        }
    }

    private static class Account {
        private final long id;
        private long amount;

        private Account(long id, long amount) {
            this.id = id;
            this.amount = amount;
        }

        public long getId() {
            return id;
        }

        public long getAmount() {
            return amount;
        }

        public void setAmount(long amount) {
            this.amount = amount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Account account = (Account) o;
            return id == account.id;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                .add("id", id)
                .toString();
        }
    }
}
