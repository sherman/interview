package com.leetcode.assorted_2024;

import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

import org.testng.annotations.Test;

public class SquaresOfSortedArray {
    public int[] sortSquares(int[] nums) {
        var result = new int[nums.length];

        // 1). make squares and find a turn pointer
        var zeroOrNegative = -1;
        for (var i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                zeroOrNegative = i;
            }
            nums[i] = nums[i] * nums[i];
        }

        //-5,-3,-2,-1
        // 15 9 4 1
        // l = 0
        // r = 4

        // 2). assemble result from two parts
        if (zeroOrNegative == -1 || zeroOrNegative == 0) {
            // we are lucky, no negative or zero items
            return nums;
        } else {
            var l = zeroOrNegative;
            var r = zeroOrNegative + 1;

            var c = 0;
            while (l >= 0 && r < nums.length) {
                var left = nums[l];
                var right = nums[r];
                if (left <= right) {
                    result[c++] = left;
                    l--;
                } else {
                    result[c++] = right;
                    r++;
                }
            }

            while (l >= 0) {
                result[c++] = nums[l];
                l--;
            }

            while (r < nums.length) {
                result[c++] = nums[r];
                r++;
            }
        }

        return result;
    }

    @Test
    public void check() {
        assertArrayEquals(sortSquares(new int[] {-3, -1, 2, 3, 4}), new int[] {1, 4, 9, 9, 16});
        assertArrayEquals(sortSquares(new int[] {-6, -3, -1, 2, 3, 4}), new int[] {1, 4, 9, 9, 16, 36});
    }
}
