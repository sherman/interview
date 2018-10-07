package org.sherman.interview.java;

/*
 * Copyright (C) 2018 by Denis M. Gabaydulin
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.testng.annotations.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedSizePoolTest {
    @Test
    public void singleThread() throws InterruptedException {
        Pool pool = new FixedSizePool(2);
        Connection connection1 = pool.get();
        Connection connection2 = pool.get();
        connection1.close();
        connection2.close();
        connection1 = pool.get();
        connection2 = pool.get();
        connection1.execute(() -> {
        });
        connection1.close();
        connection2.close();
    }

    @Test
    public void closeTwice() throws InterruptedException {
        Pool pool = new FixedSizePool(2);
        Connection connection = pool.get();
        connection.close();
        connection.close();
        connection.close();
    }

    @Test(timeOut = 30_000)
    public void execute() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(16);

        Pool pool = new FixedSizePool(2);

        CountDownLatch latch = new CountDownLatch(10_000_000);
        for (int i = 0; i < 10_000_000; i++) {
            executorService.submit(
                new Runnable() {
                    @Override
                    public void run() {
                        Connection c = null;
                        try {
                            c = pool.get();
                            c.execute(() -> {});
                            c.close();
                            c.close(); // close twice is wrong
                            latch.countDown();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            );
        }

        latch.await();
        executorService.shutdown();
    }
}
