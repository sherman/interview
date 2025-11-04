package com.leetcode.assorted_2025;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LongestStrictlyIncreasingOrStrictlyDecreasingSubarray {

    public int longestMonotonicSubarray(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }

        var maxLength = 1;
        var length = 1;
        for (var i = 1; i < nums.length; i++) {
            var prev = nums[i - 1];
            var current = nums[i];

            if (prev < current) {
                length++;
            } else {
                maxLength = Math.max(maxLength, length);
                length = 1;
            }
        }

        maxLength = Math.max(maxLength, length);

        length = 1;
        for (var i = 1; i < nums.length; i++) {
            var prev = nums[i - 1];
            var current = nums[i];

            if (prev > current) {
                length++;
            } else {
                maxLength = Math.max(maxLength, length);
                length = 1;
            }
        }

        maxLength = Math.max(maxLength, length);
        return maxLength;
    }

    @Test
    public void test() {
        Assert.assertEquals(longestMonotonicSubarray(new int[] {1, 1, 5}), 2);
        Assert.assertEquals(longestMonotonicSubarray(new int[] {1, 4, 3, 3, 2}), 2);
    }
}
