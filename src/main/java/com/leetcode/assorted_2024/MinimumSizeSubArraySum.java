package com.leetcode.assorted_2024;

import static org.testng.Assert.assertEquals;

import com.leetcode.MinimumSizeSubarraySum;
import java.util.Arrays;
import org.testng.annotations.Test;

public class MinimumSizeSubArraySum {
    public static int minSubArrayLen(int target, int[] nums) {
        var begin = 0;
        var end = 0;
        var sum = 0;
        var length = Integer.MAX_VALUE;
        while (begin < nums.length && end < nums.length) {
            sum += nums[end];
            while (sum >= target && begin <= end) {
                length = Math.min(length, end - begin + 1);
                sum -= nums[begin];
                begin++;
            }
            end++;
        }

        return length == Integer.MAX_VALUE ? 0 : length;
    }

    @Test
    public void MinimumSizeSubarraySum() {
        assertEquals(MinimumSizeSubArraySum.minSubArrayLen(7, new int[] {2, 3, 1, 2, 4, 3}), 2);
        assertEquals(MinimumSizeSubArraySum.minSubArrayLen(11, new int[] {1, 2, 3, 4, 5}), 3);
        assertEquals(MinimumSizeSubArraySum.minSubArrayLen(22, new int[] {1, 2, 3, 4, 5}), 0);
    }


}
