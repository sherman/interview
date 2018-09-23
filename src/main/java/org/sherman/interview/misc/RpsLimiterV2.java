package org.sherman.interview.misc;

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

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class RpsLimiterV2 {
    private static final Logger log = LoggerFactory.getLogger(RpsLimiterV2.class);

    private final long period;
    private final TimeUnit periodUnit;
    private final Semaphore bucket;
    private final int maxSize;

    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    public RpsLimiterV2(long period, TimeUnit periodUnit, int bucketSize) {
        this.period = period;
        this.periodUnit = periodUnit;
        this.maxSize = bucketSize;
        this.bucket = new Semaphore(maxSize);

        scheduledExecutorService.scheduleWithFixedDelay(
            () -> {
                try {
                    bucket.release(maxSize - bucket.availablePermits());
                } catch (Exception e) {
                    log.error("Error", e);
                }
            },
            period,
            period,
            periodUnit
        );
    }

    public boolean isAccepted(@NotNull RpsLimiter.Request request) {
        if (bucket.tryAcquire()) {
            return true;
        } else {
            return false;
        }
    }
}
