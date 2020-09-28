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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwoSumII {
    private static final Logger log = LoggerFactory.getLogger(TwoSumII.class);

    /**
     * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
     * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
     * You may assume that each input would have exactly one solution and you may not use the same element twice.
     * Input: numbers={2, 7, 11, 15}, target=9
     * Output: index1=1, index2=2
     * <p>
     * O(n)
     * </p>
     */
    public static int[] twoSum(int[] numbers, int target) {
        // 1). Init two pointers l = 0; r = numbers.length = -1;
        // 2). if numbers[l] + numbers[r] == sum, return asnwer
        // 3) if (l + r) < sum  l++
        // 4) if (l + r) > sum  r--

        int l = 0;
        int r = numbers.length - 1;
        while (l < r) {
            int lNum = numbers[l];
            int rNum = numbers[r];

            if (lNum + rNum == target) {
                return new int[]{l + 1, r + 1};
            } else if (lNum + rNum > target) {
                r--;
            } else {
                l++;
            }
        }

        return new int[]{}; // unreachable
    }
}
