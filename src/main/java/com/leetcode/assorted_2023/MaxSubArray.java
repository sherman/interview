package com.leetcode.assorted_2023;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MaxSubArray {
    private static final Logger logger = LoggerFactory.getLogger(MaxSubArray.class);

    public int maxSubArray(int[] nums) {
        var sum = 0;
        var maxSum = Integer.MIN_VALUE;

        for (var i = 0; i < nums.length; i++) {
            var current = nums[i];
            sum = Math.max(current, current + sum);
            maxSum = Math.max(sum, maxSum);
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
