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
        return twoSum(numbers, target, 0, numbers.length - 1);
    }

    private static int[] twoSum(int[] numbers, int target, int left, int right) {
        log.info("{} {}", left, right);

        if (left < 0 || right >= numbers.length || left > right) {
            return new int[]{};
        }

        if (numbers[left] + numbers[right] == target) {
            return new int[]{left, right};
        } else if (numbers[left] + numbers[right] > target) {
            return twoSum(numbers, target, left, right - 1);
        } else {
            return twoSum(numbers, target, left - 1, right);
        }
    }
}
