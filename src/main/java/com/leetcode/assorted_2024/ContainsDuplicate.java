package com.leetcode.assorted_2024;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        var cache = new HashMap<Integer, Integer>();
        for (var i = 0; i < nums.length; i++) {
            if (cache.containsKey(nums[i])) {
                return true;
            } else {
                cache.put(nums[i], 1);
            }
        }

        return false;
    }

    @Test
    public void test() {
        Assert.assertTrue(containsDuplicate(new int[]{1, 2, 3, 1}));
    }
}
