package org.sherman.interview.java;

import org.testng.annotations.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 27.10.16
 */
public class ArrayBlockingQueueV2Test {
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

    @Test
    public void offer() throws InterruptedException {
        ArrayBlockingQueueV2<Integer> q = new ArrayBlockingQueueV2<>(2);
        q.offer(1);
        q.offer(2);
        assertEquals(q.take(), Integer.valueOf(1));
    }

    @Test
    public void take() throws InterruptedException {
        ArrayBlockingQueueV2<Integer> q = new ArrayBlockingQueueV2<>(2);

        executorService.schedule(() -> {
            try {
                assertEquals(q.take(), Integer.valueOf(1));
            } catch (InterruptedException ignored) {
            }
        }, 1, TimeUnit.SECONDS);

        q.offer(1);
        q.offer(2);
        q.offer(3);

        assertEquals(q.take(), Integer.valueOf(2));
        assertEquals(q.take(), Integer.valueOf(3));
    }
}
