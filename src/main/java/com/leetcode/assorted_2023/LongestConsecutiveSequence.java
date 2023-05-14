package com.leetcode.assorted_2023;

import java.util.HashMap;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        var data = new HashMap<Long, Boolean>();

        for (var i = 0; i < nums.length; i++) {
            data.put(Long.valueOf(nums[i]), false);
        }

        var maxCount = Integer.MIN_VALUE;
        var count = 0;
        for (var entry : data.entrySet()) {
            if (!entry.getValue()) {
                var current = entry.getKey();
                data.put(current, true);
                count++;

                while (current != null) {
                    var next = data.get(current + 1);
                    if (next != null && !next) {
                        current = current + 1;
                        data.put(current, true);
                        count++;
                    } else {
                        current = null;
                    }
                }

                current = entry.getKey();

                while (current != null) {
                    var next = data.get(current - 1);
                    if (next != null && !next) {
                        current = current - 1;
                        data.put(current, true);
                        count++;
                    } else {
                        current = null;
                    }
                }

                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }

        return maxCount;
    }
}
