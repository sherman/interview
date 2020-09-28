package com.leetcode;

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

import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class TwoSumIITest {
    @Test
    public void twoSum() {
        assertArrayEquals(TwoSumII.twoSum(new int[]{2, 7, 11, 15}, 9), new int[]{1, 2});
        assertArrayEquals(TwoSumII.twoSum(new int[]{1, 2, 3, 15}, 9), new int[]{});
        assertArrayEquals(TwoSumII.twoSum(new int[]{1, 3, 5, 15}, 6), new int[]{1, 3});
    }
}
