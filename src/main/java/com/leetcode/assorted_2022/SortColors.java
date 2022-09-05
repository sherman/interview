package com.leetcode.assorted_2022;

import java.util.HashMap;

public class SortColors {
    public void sortColors(int[] nums) {
        var l = 0;
        var r = nums.length - 1;

        var state = new HashMap<Integer, Integer>();
        state.put(0, 0);
        state.put(1, 0);
        state.put(2, 0);

        for (var i = 0; i < nums.length; i++) {
            var c = state.get(nums[i]);
            state.put(nums[i], c + 1);
        }

        for (var i = 0; i < nums.length; i++) {
            if (state.get(0) > 0) {
                nums[i] = 0;
                state.put(0, state.get(0) - 1);
            } else if (state.get(1) > 0) {
                nums[i] = 1;
                state.put(1, state.get(1) - 1);
            } else if (state.get(2) > 0) {
                nums[i] = 2;
                state.put(2, state.get(2) - 1);
            }
        }
    }
}
