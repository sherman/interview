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

import java.util.Arrays;

public class MinimumSizeSubarraySum {
    private static final Logger log = LoggerFactory.getLogger(MinimumSizeSubarraySum.class);

    /**
     * Given an array of n positive integers and a positive integer s, find the minimal length of any subarray of which the sum ≥ s.
     * If there isn't one, return 0 instead.
     * For example, given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal length under the problem constraint.
     */
    public static int minSubArrayLen(int s, int[] nums) {
        Arrays.sort(nums);

        int length = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            s = -nums[i];
            length++;
            if (s <= 0) {
                return length;
            }
        }

        return 0;
    }

    public static int minSubArrayLenV2(int s, int[] nums) {
        int i = 0;
        int j = 0;

        int current = 0;
        int result = Integer.MAX_VALUE;

        while (i < nums.length && j < nums.length) {
            current += nums[i];
            while (current >= s) {
                result = Math.min(result, i + 1 - j);
                current -= nums[j++];
                log.info("{}, {}, {}, {}", i, j, current, result);
            }
            i++;
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
