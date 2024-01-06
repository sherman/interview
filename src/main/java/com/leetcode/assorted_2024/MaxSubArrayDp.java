package com.leetcode.assorted_2024;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MaxSubArrayDp {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        var maxSum = Integer.MIN_VALUE;
        var storage = new int[nums.length];
        storage[0] = nums[0];

        for (var i = 1; i < nums.length; i++) {
            var prev = storage[i - 1];
            var current = Math.max(prev + nums[i], nums[i]);
            storage[i] = current;
            if (current > maxSum) {
                maxSum = current;
            }
        }

        return maxSum;
    }

    @Test
    public void test() {
        Assert.assertEquals(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}), 6);
        Assert.assertEquals(maxSubArray(new int[]{1}), 1);
        Assert.assertEquals(maxSubArray(new int[]{-1}), -1);
        Assert.assertEquals(maxSubArray(new int[]{-2, -1}), -1);
        Assert.assertEquals(maxSubArray(new int[]{1, 2, 3, 4}), 10);
    }
}
