package com.leetcode.assorted_2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        var writeIndex = 0;
        var readIndex = 0;
        var zeroes = 0;

        for (var i = 0; i < nums.length; i++) {
            if (nums[readIndex] == 0) {
                zeroes++; // increment zeroes
            } else {
                nums[writeIndex] = nums[readIndex];
                writeIndex++; // increment write index
            }
            readIndex++; // increment read index
        }

        // fill rest of the array with zeroes
        for (var i = nums.length - zeroes; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    @Test
    public void moveZeroes() {
        int[] arr = new int[] {0, 1, 0, 3, 12};
        moveZeroes(arr);
        Assertions.assertArrayEquals(new int[] {1, 3, 12, 0, 0}, arr);
    }
}
