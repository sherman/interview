package com.leetcode;

import java.util.Arrays;

/**
 * @author Denis M. Gabaydulin
 * @since 02.03.19
 */
public class MajorityElementSorted {
    public static int majorityElement(int[] nums) {
        Arrays.sort(nums);

        return nums[nums.length / 2];
    }
}
