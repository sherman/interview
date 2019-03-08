package com.leetcode;

/**
 * @author Denis M. Gabaydulin
 * @since 08.03.19
 */
public class SortArrayByParity {

    /**
     * Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.
     * <p>
     * You may return any answer array that satisfies this condition.
     */
    public static int[] sortArrayByParity(int[] nums) {
        int i = 0;
        int j = nums.length - 1;

        while (i < j) {
            int v1 = nums[i];
            int v2 = nums[j];

            if (v1 % 2 != 0 && v2 % 2 == 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }

            if (v1 % 2 == 0) {
                i++;
            }

            if (v2 % 2 != 0) {
                j--;
            }
        }

        return nums;
    }
}
