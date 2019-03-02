package com.leetcode;

/**
 * @author Denis M. Gabaydulin
 * @since 02.03.19
 */
public class MajorityElementBoyerMooreVoting {
    public int majorityElement(int[] nums) {
        int count = 0;
        int majorityElt = 0;

        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                majorityElt = nums[i];
            }

            if (majorityElt == nums[i]) {
                count++;
            } else {
                count--;
            }
        }

        return majorityElt;
    }
}
