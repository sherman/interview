package com.leetcode.assorted_2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        var result = new HashMap<String, List<String>>();
        for (var str : strs) {
            var chars = str.toCharArray();
            Arrays.sort(chars);
            var sorted = new String(chars);
            var anagrams = result.computeIfAbsent(sorted, ignored -> new ArrayList<>());
            anagrams.add(str);
        }

        return result.values().stream().toList();
    }
}
