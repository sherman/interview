package org.sherman.interview.misc;

import org.openjdk.jmh.annotations.Threads;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RpsLimiterTest {
    @Test
    public void isAccepted() throws InterruptedException {
        RpsLimiter limiter = new RpsLimiter(10, 1000);

        for (int i = 0; i < 10; i++) {
            assertTrue(limiter.isAccepted(new RpsLimiter.Request(System.currentTimeMillis(), i)));
        }

        assertFalse(limiter.isAccepted(new RpsLimiter.Request(System.currentTimeMillis(), 11)));
        Thread.sleep(1000);
        assertTrue(limiter.isAccepted(new RpsLimiter.Request(System.currentTimeMillis(), 11)));
    }
}
