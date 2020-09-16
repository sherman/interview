package com.leetcode.fb;

import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        left[0] = 1;
        right[right.length - 1] = 1;

        for (int i = 1; i < left.length; i++) {
            left[i] = nums[i - 1] * left[i - 1];
        }

        for (int i = right.length - 2; i >= 0; i--) {
            right[i] = nums[i + 1] * right[i + 1];
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = left[i] * right[i];
        }

        return result;
    }

    @Test
    public void test() {
        ArrayAsserts.assertArrayEquals(productExceptSelf(new int[]{1, 2, 3, 4}), new int[]{24, 12, 8, 6});
        ArrayAsserts.assertArrayEquals(productExceptSelf(new int[]{0, 1}), new int[]{1, 0});
    }
}
