package com.leetcode.assorted_2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongestSubarrayOf1sAfterDeletingOneElement {
    public int longestSubarray(int[] nums) {
        var replacedIndex = 0;
        var best = 0;
        var hasZero = false;
        for (var i = replacedIndex; i < nums.length; i++) {
            var replaced = false;
            var current = 0;
            var start = nums[i];
            if (start != 1) {
                hasZero  = true;
                continue;
            }
            var x = 1;
            for (var j = i; j < nums.length; j++) {
                var value = nums[j];
                if (value == 0) {
                    hasZero  = true;
                    if (!replaced && (j >= replacedIndex)) {
                        replacedIndex = j;
                        replaced = true;
                    } else {
                        best = Math.max(best, current);
                        break;
                    }
                } else {
                    current++;
                }
            }
            best = Math.max(best, current);
        }
        return hasZero ? best : best - 1;
    }

    @Test
    public void test() {
        Assertions.assertEquals(2, longestSubarray(new int[] {0, 0, 1, 1}));
        Assertions.assertEquals(0, longestSubarray(new int[] {0, 0, 0}));
        Assertions.assertEquals(2, longestSubarray(new int[] {1, 1, 1}));
        Assertions.assertEquals(5, longestSubarray(new int[] {0, 1, 1, 1, 0, 1, 1, 0, 1}));
        Assertions.assertEquals(3, longestSubarray(new int[] {1, 1, 0, 1}));
    }
}
