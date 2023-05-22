package com.leetcode.assorted_2023;

public class ConcatenationOfArray {
    public int[] getConcatenation(int[] nums) {
        var result = new int[nums.length * 2];

        for (var i = 0; i < nums.length; i++) {
            result[i] = nums[i];
            result[i + nums.length] = nums[i];
        }

        return result;
    }
}
