package com.leetcode.uber;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        for (var i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }

        return nums.length - 1;
    }

    @Test
    public void test() {
        Assert.assertEquals(findPeakElement(new int[] {1, 2, 1, 3, 5, 6, 4}), 1);
    }
}
