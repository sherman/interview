package org.sherman.interview.java;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class BaseLock implements SimpleLock {
    protected final AtomicBoolean locked = new AtomicBoolean();

    @Override
    public void unlock() {
        // Idempotent operation
        locked.set(false);
    }
}
