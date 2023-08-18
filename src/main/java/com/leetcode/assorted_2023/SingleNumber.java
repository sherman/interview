package com.leetcode.assorted_2023;

import java.util.HashSet;

public class SingleNumber {
    public int singleNumber(int[] nums) {
        var numbers = new HashSet<Integer>();

        for (var i = 0; i < nums.length; i++) {
            if (!numbers.add(nums[i])) {
                numbers.remove(nums[i]);
            }
        }

        return numbers.iterator().next();
    }
}
