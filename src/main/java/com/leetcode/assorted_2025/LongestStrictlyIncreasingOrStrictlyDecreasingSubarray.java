package com.leetcode.assorted_2025;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LongestStrictlyIncreasingOrStrictlyDecreasingSubarray {

    public int longestMonotonicSubarray(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }

        int maxLength = 1;
        maxLength = Math.max(maxLength, getMaxLength(true, nums, maxLength));
        maxLength = Math.max(maxLength, getMaxLength(false, nums, maxLength));
        return maxLength;
    }

    private int getMaxLength(boolean less, int[] nums, int maxLength) {
        var length = 1;

        for (var i = 1; i < nums.length; i++) {
            var prev = nums[i - 1];
            var current = nums[i];

            if (less ? prev < current : prev > current) {
                length++;
            } else {
                maxLength = Math.max(maxLength, length);
                length = 1;
            }
        }

        return Math.max(maxLength, length);
    }

    @Test
    public void test() {
        Assert.assertEquals(longestMonotonicSubarray(new int[] {1, 1, 5}), 2);
        Assert.assertEquals(longestMonotonicSubarray(new int[] {1, 4, 3, 3, 2}), 2);
    }
}
