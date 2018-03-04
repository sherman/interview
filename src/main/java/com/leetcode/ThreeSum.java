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
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

public class ThreeSum {
    private static final Logger log = LoggerFactory.getLogger(ThreeSum.class);

    /**
     * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
     * Find all unique triplets in the array which gives the sum of zero.
     * <p>
     * For example, given array S = [-1, 0, 1, 2, -1, -4],
     * </p>
     * A solution set is:
     * [
     * [-1, 0, 1],
     * [-1, -1, 2]
     * ]
     * <p>
     * O(n^2) solution
     * </p>
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        Set<Elements> elts = new HashSet<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int l = 0;
            int r = nums.length - 1;

            int current = nums[i];
            while (l < r && l != i && r != i) {
                if (nums[l] + nums[r] + current < 0) {
                    l++;
                } else if (nums[l] + nums[r] + current > 0) {
                    r--;
                } else {
                    log.info("f: {} {} {}", nums[i], nums[l], nums[r]);

                    Elements elements = new Elements();
                    elements.i = nums[i];
                    elements.j = nums[l];
                    elements.k = nums[r];
                    elts.add(elements);

                    l++;
                    r--;
                }
            }
            log.info("{} {} {}", nums[i], l, r);
        }

        return elts.stream()
            .map(
                e -> {
                    List<Integer> l = new ArrayList<>();
                    l.add(e.i);
                    l.add(e.j);
                    l.add(e.k);
                    return l;
                }
            ).collect(Collectors.toList());
    }

    private static class Elements {
        int i;
        int j;
        int k;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Elements elements = (Elements) o;
            return i == elements.i &&
                j == elements.j &&
                k == elements.k;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j, k);
        }
    }

    @Test
    public void test() {
        //threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        //threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        threeSum(new int[]{3, 0, -2, -1, 1, 2}); // -2 -1 0 1 2 3
        //threeSum(new int[]{-2, -7, -11, -8, 9, -7, -8, -15, 10, 4, 3, 9, 8, 11, 1, 12, -6, -14, -2, -1, -7, -13, -11, -15, 11, -2, 7, -4, 12, 7, -3, -5, 7, -7, 3, 2, 1, 10, 2, -12, -1, 12, 12, -8, 9, -9, 11, 10, 14, -6, -6, -8, -3, -2, 14, -15, 3, -2, -4, 1, -9, 8, 11, 5, -14, -1, 14, -6, -14, 2, -2, -8, -9, -13, 0, 7, -7, -4, 2, -8, -2, 11, -9, 2, -13, -10, 2, 5, 4, 13, 13, 2, -1, 10, 14, -8, -14, 14, 2, 10});
    }
}
