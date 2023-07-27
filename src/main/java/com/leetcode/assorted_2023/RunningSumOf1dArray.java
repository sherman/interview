package com.leetcode.assorted_2023;

public class RunningSumOf1dArray {
    public int[] runningSum(int[] nums) {
        var sum = 0;
        for (var i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            nums[i] = sum;
        }
        return nums;
    }
}
