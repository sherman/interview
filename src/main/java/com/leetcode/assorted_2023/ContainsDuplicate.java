package com.leetcode.assorted_2023;

import java.util.HashSet;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        var elements = new HashSet<Integer>();
        for (var i = 0; i < nums.length; i++) {
            if (!elements.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        Assert.assertTrue(containsDuplicate(new int[] {1, 2, 3, 1}));
    }
}
