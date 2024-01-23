package com.leetcode.assorted_2024;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        var rotatedIndex = getRotatePoint(nums);
        var rotatedValue = rotatedIndex == -1 ? -1 : nums[rotatedIndex];

        int first = nums[0];
        int last = nums[nums.length - 1];

        int l;
        int r;
        // we're lucky
        if (rotatedValue == target) {
            return rotatedIndex;
        } else if (first == target) {
            return 0;
        } else if (last == target) {
            return nums.length - 1;
            // properly sorted
        } else {
            if (rotatedIndex == -1) {
                l = 0;
                r = nums.length - 1;
            } else if (target > nums[rotatedIndex] && target < last) {
                l = rotatedIndex + 1;
                r = nums.length - 1;
            } else {
                l = 0;
                r = rotatedIndex - 1;
            }
        }

        while (l <= r) {
            var mid = (l + r) / 2;
            var midElement = nums[mid];

            if (midElement == target) {
                return mid;
            } else if (target < midElement) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return -1;
    }

    private int getRotatePoint(int[] nums) {
        // sorted
        if (nums[0] < nums[nums.length - 1]) {
            return -1;
        }

        if (nums.length == 1) {
            return 0;
        }

        if (nums.length == 2) {
            if (nums[0] > nums[1]) {
                return 1;
            }
        }

        var l = 0;
        var r = nums.length - 1;

        while (l <= r) {
            var mid = (l + r) / 2;
            var midElement = nums[mid];

            if (mid - 1 >= 0 && mid + 1 < nums.length) {
                var before = nums[mid - 1];
                var after = nums[mid + 1];

                if (before < midElement && midElement > after) {
                    return mid + 1;
                }

                if (before > midElement && midElement < after) {
                    return mid;
                }
            }

            if (nums[l] > midElement) {
                r = mid - 1;
            } else if (nums[r] < midElement) {
                l = mid + 1;
            }
        }

        return 0;
    }

    @Test
    public void cases() {
        Assert.assertEquals(search(new int[]{1, 3}, 0), -1);
        Assert.assertEquals(search(new int[]{2, 3, 1}, 3), 1);
        Assert.assertEquals(search(new int[]{5, 1, 3}, 3), 2);
        Assert.assertEquals(search(new int[]{1, 3}, 3), 1);
        Assert.assertEquals(search(new int[]{1}, 1), 0);
        Assert.assertEquals(search(new int[]{4, 5, 6, 3}, 4), 0);
        Assert.assertEquals(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 7), 3);
    }
}
