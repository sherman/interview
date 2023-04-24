package com.leetcode.assorted_2023;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ShuffleTheArray {
    public int[] shuffle(int[] nums, int n) {
        var result = new int[nums.length];

        int k = 0;
        for (var i = 0; i < n; i++) {
            result[k] = nums[i];
            result[k + 1] = nums[i + n];
            k = k + 2;
        }

        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(shuffle(new int[] {2, 5, 1, 3, 4, 7}, 3), new int[] {2, 3, 5, 4, 1, 7});
    }
}
