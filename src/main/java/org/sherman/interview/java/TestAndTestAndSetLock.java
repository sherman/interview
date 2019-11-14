package org.sherman.interview.java;

import java.util.concurrent.atomic.AtomicBoolean;

public class TestAndTestAndSetLock extends BaseLock {
    @Override
    public void lock() {
        while (true) {
            while (locked.get()) {
                // read data from cache (hopefully), while it's locked by another thread
            }

            if (!locked.getAndSet(true)) {
                // locked state is updated, and the previous value was false
                return;
            }
        }
    }
}
