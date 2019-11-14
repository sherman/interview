package org.sherman.interview.java;

public class TestAndSetLock extends BaseLock {
    @Override
    public void lock() {
        while (locked.compareAndSet(false, true)) {
            // do nothing
        }
    }
}
