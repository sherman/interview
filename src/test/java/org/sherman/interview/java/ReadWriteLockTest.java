package org.sherman.interview.java;

import org.testng.annotations.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 28/12/2016
 */
public class ReadWriteLockTest {
    @Test
    public void lockWrite() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger();

        ReadWriteLock lock = new ReadWriteLock();

        new Thread(
                () -> {
                    try {
                        Thread.sleep(500);
                        lock.lockWrite();
                    } catch (InterruptedException ignored) {
                    }

                    counter.incrementAndGet();
                    lock.unlockWrite();
                }
        ).start();

        lock.lockWrite();

        Thread.sleep(1_000);

        lock.unlockWrite();

        assertEquals(counter.get(), 0);
    }

    @Test
    public void lockRead() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger();

        ReadWriteLock lock = new ReadWriteLock();

        new Thread(
                () -> {
                    try {
                        Thread.sleep(500);
                        lock.lockWrite();
                    } catch (InterruptedException ignored) {
                    }

                    counter.incrementAndGet();
                    lock.unlockWrite();
                }
        ).start();

        lock.lockRead();

        Thread.sleep(1_000);

        lock.unlockRead();

        assertEquals(counter.get(), 0);
    }

    @Test(expectedExceptions = TimeoutException.class)
    public void writeReadExclusive() throws InterruptedException, TimeoutException, ExecutionException {
        CompletableFuture.runAsync(() -> {
            ReadWriteLock lock = new ReadWriteLock();
            try {
                lock.lockWrite();
                lock.lockRead();
            } catch (InterruptedException ignored) {
            }
        }).get(1, TimeUnit.SECONDS);
    }

    @Test(expectedExceptions = TimeoutException.class)
    public void writeExclusive() throws TimeoutException, ExecutionException, InterruptedException {
        CompletableFuture.runAsync(() -> {
            ReadWriteLock lock = new ReadWriteLock();
            try {
                lock.lockWrite();
                lock.lockWrite();
            } catch (InterruptedException ignored) {
            }
        }).get(1, TimeUnit.SECONDS);
    }

    @Test(expectedExceptions = TimeoutException.class)
    public void readWriteExclusive() throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture.runAsync(() -> {
            ReadWriteLock lock = new ReadWriteLock();
            try {
                lock.lockRead();
                lock.lockWrite();
            } catch (InterruptedException ignored) {
            }
        }).get(1, TimeUnit.SECONDS);
    }

    @Test(expectedExceptions = TimeoutException.class)
    public void multipleReads() throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture.runAsync(() -> {
            ReadWriteLock lock = new ReadWriteLock();
            try {
                lock.lockRead();
                lock.lockRead();
                lock.unlockRead();
                lock.lockWrite();
            } catch (InterruptedException ignored) {
            }
        }).get(1, TimeUnit.SECONDS);
    }

    @Test(timeOut = 1_000)
    public void multipleReadsOk() throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture.runAsync(() -> {
            ReadWriteLock lock = new ReadWriteLock();
            try {
                lock.lockRead();
                lock.lockRead();
                lock.unlockRead();
                lock.unlockRead();
                lock.lockWrite();
            } catch (InterruptedException ignored) {
            }
        }).get(1, TimeUnit.SECONDS);
    }

}
