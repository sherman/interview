package com.leetcode.fb;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SingleNumber {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }

    @Test
    public void test() {
        Assert.assertEquals(singleNumber(new int[]{2, 2, 1}), 1);
        Assert.assertEquals(singleNumber(new int[]{1, 1, 5, 3, 3}), 5);
    }
}
