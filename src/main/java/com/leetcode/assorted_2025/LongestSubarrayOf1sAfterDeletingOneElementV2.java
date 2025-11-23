package com.leetcode.assorted_2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongestSubarrayOf1sAfterDeletingOneElementV2 {
    public int longestSubarray(int[] nums) {
        var maxLength = 0;
        var zeroCount = 0;
        var start = 0;

        for (int i = 0; i < nums.length; i++) {
            zeroCount += (nums[i] == 0 ? 1 : 0);

            if (zeroCount > 1) {
                zeroCount -= (nums[start] == 0 ? 1 : 0);
                start++;
            }

            maxLength = Math.max(i - start, maxLength);
        }
        return maxLength;
    }

    @Test
    public void test() {
        Assertions.assertEquals(4, longestSubarray(new int[] {1, 1, 0, 0, 1, 1, 1, 0, 1}));
        Assertions.assertEquals(2, longestSubarray(new int[] {0, 0, 1, 1}));
        Assertions.assertEquals(0, longestSubarray(new int[] {0, 0, 0}));
        Assertions.assertEquals(2, longestSubarray(new int[] {1, 1, 1}));
        Assertions.assertEquals(5, longestSubarray(new int[] {0, 1, 1, 1, 0, 1, 1, 0, 1}));
        Assertions.assertEquals(3, longestSubarray(new int[] {1, 1, 0, 1}));
    }
}
