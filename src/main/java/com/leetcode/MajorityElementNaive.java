package com.leetcode;

import java.util.Arrays;

/**
 * @author Denis M. Gabaydulin
 * @since 02.03.19
 */
public class MajorityElementNaive {
    public static int majorityElement(int[] nums) {
        Arrays.sort(nums);

        Integer elt = null;
        Integer current = null;
        int max = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (current == null) {
                current = num;
                count = 1;
                continue;
            }

            if (current != num) {
                if (count > max) {
                    elt = current;
                    max = count;
                    count = 0;
                }
            }

            current = num;
            count++;
        }

        if (elt == null || count > max) {
            elt = current;
        }

        return elt;
    }
}
