package org.sherman.interview.java;

import java.util.concurrent.CompletableFuture;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReentrantLockTest {
    @Test
    public void tryLock() {
        var lock = new ReentrantLock();
        Assert.assertTrue(lock.tryLock());
        Assert.assertTrue(lock.tryUnlock());
    }

    @Test
    public void tryLockMultipleTimes() {
        var lock = new ReentrantLock();
        Assert.assertTrue(lock.tryLock());
        Assert.assertTrue(lock.tryLock());
        Assert.assertTrue(lock.tryUnlock());
        Assert.assertTrue(lock.tryUnlock());
    }

    @Test
    public void tryLockFromDifferentThreads() {
        var lock = new ReentrantLock();
        Assert.assertTrue(lock.tryLock());
        CompletableFuture.runAsync(() -> Assert.assertFalse(lock.tryUnlock()))
            .join();
        Assert.assertTrue(lock.tryUnlock());
    }

    @Test
    public void lockByAnotherThread() {
        var lock = new ReentrantLock();
        Assert.assertTrue(lock.tryLock());
        CompletableFuture.runAsync(() -> Assert.assertFalse(lock.tryUnlock()))
            .join();
        Assert.assertTrue(lock.tryUnlock());
        CompletableFuture.runAsync(() -> Assert.assertTrue(lock.tryLock()))
            .join();
    }
}
