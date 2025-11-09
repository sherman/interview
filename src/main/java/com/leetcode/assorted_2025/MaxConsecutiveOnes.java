package com.leetcode.assorted_2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaxConsecutiveOnes {

    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums.length == 1) {
            return nums[0] == 1 ? 1 : 0;
        }

        // case: 0,1 // start = i
        // case: 0,0 // continue
        // case: 1,0 // try to calculate
        // case: 1,1 // continue

        var max = 0;
        var start = nums[0] == 1 ? 0 : -1;
        for (var i = 1; i < nums.length; i++) {
            var prev = nums[i - 1];
            var current = nums[i];

            if (prev == 0 && current == 1) {
                start = i;
            } else if (prev == 1 && current == 0) {
                if (start >= 0) {
                    max = Math.max(i - start, max);
                    start = -1;
                }
            }
        }

        if (start >= 0) {
            max = Math.max(nums.length - start, max);
        }

        return max;
    }

    @Test
    public void test() {
        Assertions.assertEquals(3, findMaxConsecutiveOnes(new int[] {1, 1, 0, 1, 1, 1}));
        Assertions.assertEquals(2, findMaxConsecutiveOnes(new int[] {1, 0, 1, 1, 0, 1}));
    }
}
