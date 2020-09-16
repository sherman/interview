package com.leetcode.fb;

import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int total = nums[0];
        for (int i = 1; i < nums.length; i++) {
            total = total * nums[i];
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = total / nums[i];
        }
        return result;
    }

    @Test
    public void test() {
        ArrayAsserts.assertArrayEquals(productExceptSelf(new int[]{1, 2, 3, 4}), new int[]{24, 12, 8, 6});
    }
}
