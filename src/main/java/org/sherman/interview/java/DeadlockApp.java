package org.sherman.interview.java;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeadlockApp {
    private static final Logger logger = LoggerFactory.getLogger(DeadlockApp.class);

    public static void main(String[] args) throws InterruptedException {
        var executor = new ThreadPoolExecutor(4, 4,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(10000));

        /*var executor = new ForkJoinPool(
            2,
            defaultForkJoinWorkerThreadFactory, null, false,
            2, 2, 2, null, 10, TimeUnit.MILLISECONDS
        );*/

        logger.info("Start");

        CountDownLatch l = new CountDownLatch(16 * 4);

        for (var i = 0; i < 4; i++) {
            final int m = i;
            executor.submit(
                () -> {
                    logger.info("Start: {}", m);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    var result = new ArrayList<Future<?>>();
                    for (var k = 0; k < 16; k++) {
                        var r = executor.submit(
                            new Runnable() {
                                @Override
                                public void run() {
                                    logger.info("fixed: {}", m);
                                    l.countDown();
                                }
                            }
                        );
                        result.add(r);
                    }

                    for (var r : result) {
                        try {
                            r.get();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        } catch (ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            );
        }

        logger.info("Wait");
        l.await();
        logger.info("Completed");

        Thread.sleep(50000);
    }
}
