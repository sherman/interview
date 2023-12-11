package io.algoexpert;

import java.util.HashMap;

public class MissingNumbers {
    public int[] missingNumbers(int[] nums) {
        var data = new HashMap<Integer, Integer>();
        for (var i = 0; i < nums.length; i++) {
            data.put(nums[i], nums[i]);
        }

        var result = new int[2];
        for (var i = 1; i <= nums.length + 2; i++) {
            var number = data.get(i);
            if (number == null) {
                if (result[0] == 0) {
                    result[0] = i;
                } else {
                    result[1] = i;
                }
            }
        }

        return result;
    }
}
