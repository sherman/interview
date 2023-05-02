package com.leetcode.assorted_2023;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MaximumAverageSubarrayI {
    public double findMaxAverage(int[] nums, int k) {
        var max = Double.MIN_VALUE;
        for (var i = 0; i < nums.length; i++) {
            if (i + k < nums.length) {
                var sum = 0;
                for (var j = i; j < i + k; j++) {
                    sum += nums[j];
                }
                max = Math.max(max, (double) sum / k);
            }
        }

        return max;
    }

    @Test
    public void test() {
        Assert.assertEquals(findMaxAverage(new int[] {1, 12, -5, -6, 50, 3}, 4), 12.75);
    }
}
