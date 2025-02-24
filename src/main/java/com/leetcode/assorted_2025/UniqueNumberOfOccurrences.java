package com.leetcode.assorted_2025;

import java.util.HashMap;
import java.util.HashSet;

public class UniqueNumberOfOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        var counter = new HashMap<Integer, Integer>();
        for (var i = 0; i < arr.length; i++) {
            var count = counter.getOrDefault(arr[i], 0);
            counter.put(arr[i], count + 1);
        }

        var unique = new HashSet<Integer>();
        for (var value : counter.values()) {
            if (!unique.add(value)) {
                return false;
            }
        }

        return true;
    }
}
