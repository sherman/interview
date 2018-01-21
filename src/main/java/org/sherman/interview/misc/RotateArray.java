package org.sherman.interview.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class RotateArray {
    private static final Logger log = LoggerFactory.getLogger(RotateArray.class);

    public static int[] rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
        return nums;
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    @Test
    public void test() {
        assertArrayEquals(RotateArray.rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3), new int[]{5, 6, 7, 1, 2, 3, 4});
        assertArrayEquals(RotateArray.rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 1), new int[]{7, 1, 2, 3, 4, 5, 6});
        assertArrayEquals(RotateArray.rotate(new int[]{1}, 0), new int[]{1});
        assertArrayEquals(RotateArray.rotate(new int[]{-1}, 2), new int[]{-1});
    }
}
