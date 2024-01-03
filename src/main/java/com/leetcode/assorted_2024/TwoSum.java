package com.leetcode.assorted_2024;

import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

import java.util.HashMap;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        var cache = new HashMap<Integer, Integer>();

        for (var i = 0; i < nums.length; i++) {
            var second = target - nums[i];
            if (cache.containsKey(second)) {
                return new int[]{cache.get(second), i};
            } else {
                cache.put(nums[i], i);
            }
        }

        return new int[]{};
    }

    @Test
    public void test() {
        ArrayAsserts.assertArrayEquals(twoSum(new int[]{3, 5, -4, 8, 11, 1, -1, 6}, 10), new int[]{4, 6});
    }
}
