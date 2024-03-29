package org.sherman.interview.java;

import java.util.concurrent.atomic.AtomicInteger;

public class ReentrantLock {
    private static final long NO_ID = -1;

    private long currentThreadId = NO_ID;
    private AtomicInteger acquires = new AtomicInteger();

    public boolean tryLock() {
        var id = Thread.currentThread().getId();
        if (currentThreadId == NO_ID) {
            // try to acquire lock first time
            if (acquires.compareAndSet(0, 1)) {
                currentThreadId = id;
                return true;
            } else {
                return false;
            }
        } else if (currentThreadId == id) {
            acquires.incrementAndGet();
            return true;
        } else {
            return false;
        }
    }

    public boolean tryUnlock() {
        if (currentThreadId == NO_ID) {
            return false;
        }

        var id = Thread.currentThread().getId();
        if (id != currentThreadId) {
            return false;
        }

        // firstly, we should change an owner (in case of acquires = 0)  and then decrement value
        var current = acquires.get() - 1;
        if (current == 0) {
            currentThreadId = NO_ID;
        }
        acquires.set(current);
        return true;
    }
}
