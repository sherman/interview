package com.leetcode.assorted_2023;

import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DegreeOfAnArray {
    public int findShortestSubArray(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }

        var index = new HashMap<Integer, Value>();

        for (var i = 0; i < nums.length; i++) {
            var value = index.get(nums[i]);
            if (value == null) {
                value = new Value();
                value.count = 1;
                value.first = i;
                index.put(nums[i], value);
            } else {
                value.count++;
                value.last = i;
            }
        }

        var max = Integer.MIN_VALUE;
        var min = Integer.MIN_VALUE;
        for (var entry : index.entrySet()) {
            var value = entry.getValue();
            if (value.count > max) {
                max = value.count;
                min = value.last >= 0 ? value.last - value.first + 1 : 1;
            } else if (value.count == max) {
                var newMin = value.last >= 0 ? value.last - value.first + 1 : 1;
                if (newMin < min) {
                    min = newMin;
                }
            }
        }

        return min;
    }

    private static class Value {
        int count = 0;
        int first = -1;
        int last = -1;
    }

    @Test
    public void test() {
        Assert.assertEquals(findShortestSubArray(new int[] {1, 2, 2, 3, 1}), 2);
    }
}
