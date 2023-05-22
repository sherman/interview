package com.leetcode.assorted_2023;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConvertAnArrayIntoA2DArrayWithConditions {
    public List<List<Integer>> findMatrix(int[] nums) {
        var freq = new HashMap<Integer, Integer>();

        // build freq. table
        for (var i = 0; i < nums.length; i++) {
            var count = freq.getOrDefault(nums[i], 0);
            freq.put(nums[i], count + 1);
        }

        var result = new ArrayList<List<Integer>>();

        // get a single digit from each unique bucket
        while (true) {
            var subResult = new ArrayList<Integer>();
            for (var entry : freq.entrySet()) {
                if (entry.getValue() > 0) {
                    subResult.add(entry.getKey());
                    freq.put(entry.getKey(), entry.getValue() - 1);
                }
            }
            if (subResult.isEmpty()) {
                break;
            } else {
                result.add(subResult);
            }
        }

        return result;
    }
}
