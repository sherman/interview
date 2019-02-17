package org.sherman.interview.java;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.*;

/**
 * @author Denis M. Gabaydulin
 * @since 17.02.19
 */
public class ExecutorProblemOOMTest {
    private static final Logger log = LoggerFactory.getLogger(ExecutorProblemOOMTest.class);

    /**
     * Run with -Xmx64m
     * <p>
     * Expected: java.lang.OutOfMemoryError: unable to create new native thread
     */
    @Test(expectedExceptions = OutOfMemoryError.class)
    public void oomTooManyThreads() {
        ExecutorService executorService = Executors.newCachedThreadPool(
            new ThreadFactoryBuilder()
                .setDaemon(true)
                .setNameFormat("oom-worker-%s")
                .build()
        );

        while (true) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * Run with -Xmx16m
     * <p>
     * Expected:
     * *** java.lang.instrument ASSERTION FAILED ***: "!errorOutstanding" with message can't create byte arrau at JPLISAgent.c line: 813
     * java.lang.OutOfMemoryError: Java heap space
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     */
    @Test(expectedExceptions = OutOfMemoryError.class)
    public void oomTooManyTasks() {
        ExecutorService executorService = Executors.newFixedThreadPool(
            10,
            new ThreadFactoryBuilder()
                .setDaemon(true)
                .setNameFormat("oom-worker-%s")
                .build()
        );

        while (true) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Test
    public void noOutOfMemory() {
        ExecutorService executorService = new ThreadPoolExecutor(
            10,
            10,
            0L, TimeUnit.MILLISECONDS,
            new SynchronousQueue<>(),
            new ThreadFactoryBuilder()
                .setDaemon(true)
                .setNameFormat("oom-worker-%s")
                .build()
        );

        while (true) {
            try {
                executorService.submit(() -> {
                    try {
                        Thread.sleep(Integer.MAX_VALUE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } catch (RejectedExecutionException e) {
                log.error("Too many tasks in the work");
                break;
            }
        }

        executorService.shutdown();
    }

    /**
     * Expected:
     * <p>
     * java.lang.StackOverflowError, because SynchronousQueue based on stack impl. of the transfer.
     */
    @Test(expectedExceptions = java.lang.StackOverflowError.class)
    public void discardOldestPolicyStackOverFlow() {
        ExecutorService executorService = new ThreadPoolExecutor(
            10,
            10,
            0L, TimeUnit.MILLISECONDS,
            new SynchronousQueue<>(),
            new ThreadFactoryBuilder()
                .setDaemon(true)
                .setNameFormat("oom-worker-%s")
                .build(),
            new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        while (true) {
            try {
                executorService.submit(() -> {
                    try {
                        Thread.sleep(Integer.MAX_VALUE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } catch (RejectedExecutionException e) {
                log.error("Too many tasks in the work");
                break;
            }
        }

        executorService.shutdown();
    }

    @Test
    public void discardOldestPolicy() {
        ExecutorService executorService = new ThreadPoolExecutor(
            10,
            10,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(100),
            new ThreadFactoryBuilder()
                .setDaemon(true)
                .setNameFormat("oom-worker-%s")
                .build(),
            new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }
}
