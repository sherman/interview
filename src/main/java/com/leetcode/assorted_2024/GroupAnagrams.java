package com.leetcode.assorted_2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        var index = new HashMap<String, List<String>>();

        for (var i = 0; i < strs.length; i++) {
            var str = strs[i];
            var chars = str.toCharArray();
            Arrays.sort(chars);
            var sorted = new String(chars);
            var collection = index.computeIfAbsent(sorted, ignored -> new ArrayList<String>());
            collection.add(str);
        }

        return new ArrayList<>(index.values());
    }
}
