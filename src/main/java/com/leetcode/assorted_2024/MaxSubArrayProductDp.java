package com.leetcode.assorted_2024;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MaxSubArrayProductDp {
    public int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        var maxProduct = Integer.MIN_VALUE;
        var suffix = 1;
        var prefix = 1;

        for (var i = 0; i < nums.length; i++) {
            if (prefix == 0) {
                prefix = 1;
            }

            if (suffix == 0) {
                suffix = 1;
            }

            prefix = prefix * nums[i];
            suffix = suffix * nums[nums.length - 1 - i];

            maxProduct = Math.max(maxProduct, Math.max(suffix, prefix));
        }

        return maxProduct;
    }

    @Test
    public void test() {
        Assert.assertEquals(maxProduct(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}), 960);
        Assert.assertEquals(maxProduct(new int[]{-2, 3, -4}), 24);
        Assert.assertEquals(maxProduct(new int[]{-2, 3, 4}), 12);
    }
}
