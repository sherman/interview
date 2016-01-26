package org.sherman.interview.sort.counting;

/*
 * Copyright (C) 2015 by Denis M. Gabaydulin
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

import java.util.Arrays;

import static org.testng.Assert.assertTrue;

public class CountingSortTest {
    @Test
    public void sort() {
        long[] result = CountingSort.sort(new long[]{43L, 1L, 3L, 3L, 44L, 42L});

        assertTrue(Arrays.equals(result, new long[]{1L, 3L, 3L, 42L, 43L, 44L}));
    }
}
