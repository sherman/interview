package com.leetcode.assorted_2023;

import java.util.HashMap;

public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        var leftToRight = new HashMap<Integer, Integer>();
        var rightToLeft = new HashMap<Integer, Integer>();

        var current = 1;
        for (var i = 0; i < nums.length; i++) {
            current = current * nums[i];
            leftToRight.put(i, current);
        }

        current = 1;
        for (var i = nums.length - 1; i >= 0; i--) {
            current = current * nums[i];
            rightToLeft.put(i, current);
        }

        var result = new int[nums.length];
        for (var i = 0; i < nums.length; i++) {
            if (i == 0) {
                result[i] = rightToLeft.get(i + 1);
            } else if (i == nums.length - 1) {
                result[i] = leftToRight.get(i - 1);
            } else {
                result[i] = leftToRight.get(i - 1) * rightToLeft.get(i + 1);
            }
        }

        return result;
    }
}
