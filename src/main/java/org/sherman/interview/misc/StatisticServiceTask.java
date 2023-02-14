package org.sherman.interview.misc;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.Striped;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StatisticServiceTask {
    private static final Logger logger = LoggerFactory.getLogger(StatisticServiceTask.class);

    private static class StatisticStorage {
        private final Map<String, Queue<Event>> statistics = new ConcurrentHashMap<>();

        private final Striped<Lock> stripedLock = Striped.lock(128);

        private final int period;

        private StatisticStorage(int period) {
            this.period = period;
        }

        void addEvent(String username) {
            Preconditions.checkArgument(username != null, "Username is required!");

            var queue = statistics.computeIfAbsent(
                username, ignored -> new LinkedList<>()
            );

            var lock = stripedLock.get(username);
            try {
                lock.lock();
                queue.add(new Event(System.currentTimeMillis()));
            } finally {
                lock.unlock();
            }
        }

        int getEventsCount(String username) {
            Preconditions.checkArgument(username != null, "Username is required!");

            var queue = statistics.computeIfAbsent(
                username, ignored -> new LinkedList<>()
            );

            var now = System.currentTimeMillis();

            var lock = stripedLock.get(username);
            try {
                lock.lock();

                while (!queue.isEmpty()) {
                    var event = queue.peek();
                    if (event != null && now - period > event.ts) {
                        queue.poll();
                    } else {
                        break;
                    }
                }

                return queue.size();
            } finally {
                lock.unlock();
            }
        }
    }

    record Event(long ts) {
    }

    @Test
    public void test() throws InterruptedException {
        var storage = new StatisticStorage(1000);
        for (var i = 0; i < 100; i++) {
            storage.addEvent("user1");
            storage.addEvent("user2");
        }

        Assert.assertEquals(storage.getEventsCount("user1"), 100);
        Assert.assertEquals(storage.getEventsCount("user2"), 100);

        Thread.sleep(1500);

        Assert.assertEquals(storage.getEventsCount("user1"), 0);
        Assert.assertEquals(storage.getEventsCount("user2"), 0);

        for (var i = 0; i < 10; i++) {
            storage.addEvent("user1");
            storage.addEvent("user2");
        }

        Assert.assertEquals(storage.getEventsCount("user1"), 10);
        Assert.assertEquals(storage.getEventsCount("user2"), 10);
    }
}
