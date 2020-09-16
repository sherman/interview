package com.leetcode.fb;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int found = 0;
        for (int i = 0; i < nums.length; i++) {
            int currentSum = 0;
            for (int j = i; j < nums.length; j++) {
                currentSum += nums[j];
                if (currentSum == k) {
                    found++;
                }
            }
        }

        return found;
    }

    @Test
    public void test() {
        Assert.assertEquals(subarraySum(new int[]{1, 1, 1}, 2), 2);
        Assert.assertEquals(subarraySum(new int[]{1, 1, 1}, 3), 1);
        Assert.assertEquals(subarraySum(new int[]{1, 1, 1}, 4), 0);
        Assert.assertEquals(subarraySum(new int[]{1, 2, 3}, 3), 2);
        Assert.assertEquals(subarraySum(new int[]{1}, 1), 1);
        Assert.assertEquals(subarraySum(new int[]{1}, 0), 0);
        Assert.assertEquals(subarraySum(new int[]{-1, -1, 1}, 0), 1);
    }
}
