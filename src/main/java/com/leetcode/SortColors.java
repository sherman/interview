package com.leetcode;

public class SortColors {
    /**
     * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
     *
     * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
     *
     * Note: You are not suppose to use the library's sort function for this problem.
     */
    public static void sortColors(int[] nums) {
        int i = 0;
        int red = 0;
        int blue = nums.length - 1;

        while (i <= blue) {
            if (nums[i] == 0) {
                int temp = nums[red];
                nums[red] = nums[i];
                nums[i] = temp;
                red++;
            } else if (nums[i] == 2) {
                int temp = nums[blue];
                nums[blue] = nums[i];
                nums[i] = temp;
                blue--;
                i--;
            }
            i++;
        }
    }
}
