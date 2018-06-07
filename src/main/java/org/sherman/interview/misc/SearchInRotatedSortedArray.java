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

public class SearchInRotatedSortedArray {
    public static int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        int first = nums[0];
        int last = nums[nums.length - 1];

        int rotateIndex = findMin(nums);
        int rotateValue = nums[rotateIndex];

        // we're lucky
        if (rotateValue == target) {
            return rotateIndex;
        } else if (first == target) {
            return 0;
        } else if (last == target) {
            return nums.length - 1;
            // properly sorted
        } else {
            if (target > nums[rotateIndex] && target < last) {
                return search(nums, target, rotateIndex, nums.length - 1);
            } else {
                return search(nums, target, 0, rotateIndex);
            }
        }
    }

    // find a rotate point
    private static int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (nums[low] > nums[high]) {
            int mid = (low + high) / 2;

            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    // binary search to find a target
    private static int search(int[] nums, int target, int l, int r) {
        if (l <= r) {
            int mid = (l + r) / 2;

            int midValue = nums[mid];

            if (target < midValue) {
                return search(nums, target, l, mid - 1);
            } else if (target > midValue) {
                return search(nums, target, mid + 1, r);
            } else {
                return mid;
            }
        }

        return -1;
    }
}
