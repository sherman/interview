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

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RpsLimiterV2Test {
    @Test
    public void isAccepted() throws InterruptedException {
        RpsLimiterV2 limiter = new RpsLimiterV2(1000, TimeUnit.MILLISECONDS, 10);

        for (int i = 0; i < 10; i++) {
            assertTrue(limiter.isAccepted(new RpsLimiter.Request(System.currentTimeMillis(), i)));
        }

        assertFalse(limiter.isAccepted(new RpsLimiter.Request(System.currentTimeMillis(), 11)));
        Thread.sleep(1000);
        assertTrue(limiter.isAccepted(new RpsLimiter.Request(System.currentTimeMillis(), 11)));
    }
}
