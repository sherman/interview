package org.sherman.interview.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Denis M. Gabaydulin
 * @since 21.02.19
 */
public class LoadBalancingTest {
    private static final Logger log = LoggerFactory.getLogger(LoadBalancingTest.class);

    private static final String SERVER1 = "server1";
    private static final String SERVER2 = "server2";
    private static final String SERVER3 = "server3";

    private final Queue<Integer> blockingQueue1 = new LinkedList<>();
    private final Queue<Integer> blockingQueue2 = new LinkedList<>();
    private final Queue<Integer> blockingQueue3 = new LinkedList<>();

    @Test
    public void random() {
        loadBalance(new Random());
    }

    @Test
    public void roundRobin() {
        loadBalance(new RoundRobin());
    }

    public void loadBalance(BalancingAlgorithm balancingAlgorithm) {
        Map<String, AtomicInteger> waits = new HashMap<>();

        for (int i = 0; i < 100; i++) {
            int server = balancingAlgorithm.nextServer();

            if (server == 1) {
                blockingQueue1.add(server);
            }

            if (server == 2) {
                blockingQueue2.add(server);
            }

            if (server == 3) {
                blockingQueue3.add(server);
            }
        }

        String server1, server2, server3;
        do {
            server1 = Optional.ofNullable(blockingQueue1.poll()).map(v -> "R").orElse("E");
            server2 = Optional.ofNullable(blockingQueue2.poll()).map(v -> "R").orElse("E");
            server3 = Optional.ofNullable(blockingQueue3.poll()).map(v -> "R").orElse("E");

            log.info("{} {} {}", server1, server2, server3);
            if (server1.equals("E")) {
                addEmpty(SERVER1, waits);
            }

            if (server2.equals("E")) {
                addEmpty(SERVER2, waits);
            }

            if (server3.equals("E")) {
                addEmpty(SERVER3, waits);
            }
        } while (server1.equals("R") || server2.equals("R") || server3.equals("R"));

        log.info("{}", waits);
    }

    private static final class RoundRobinIterator implements Iterator<Integer> {
        private Integer count = 0;

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Integer next() {
            if (count == 3) {
                count = 0;
            }

            return ++count;
        }
    }

    private void addEmpty(String server, Map<String, AtomicInteger> waits) {
        waits.putIfAbsent(server, new AtomicInteger());
        waits.get(server).incrementAndGet();
    }

    private static abstract class BalancingAlgorithm {
        abstract int nextServer();
    }

    private static class RoundRobin extends BalancingAlgorithm {
        private final RoundRobinIterator iterator = new RoundRobinIterator();

        @Override
        int nextServer() {
            return iterator.next();
        }
    }

    private static class Random extends BalancingAlgorithm {
        @Override
        int nextServer() {
            return ThreadLocalRandom.current().nextInt(1, 4);
        }
    }
}
