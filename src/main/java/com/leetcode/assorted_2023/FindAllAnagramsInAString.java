package com.leetcode.assorted_2023;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        var current = new ArrayDeque<Character>();

        var needed = new HashMap<Character, Integer>();
        for (var i = 0; i < p.length(); i++) {
            var counter = needed.computeIfAbsent(p.charAt(i), ignored -> 0);
            needed.put(p.charAt(i), counter + 1);
        }

        var result = new ArrayList<Integer>();
        for (var i = 0; i < s.length(); i++) {
            if (current.size() < p.length()) {
                if (needed.containsKey(s.charAt(i))) {
                    current.addLast(s.charAt(i));
                } else {
                    if (!current.isEmpty()) {
                        current.clear();
                    }
                }
            }

            if (current.size() == p.length()) {
                var temp = new HashMap<Character, Integer>();
                for (var c : current) {
                    var counter = temp.computeIfAbsent(c, ignored -> 0);
                    temp.put(c, counter + 1);
                }
                if (temp.equals(needed)) {
                    result.add(i - p.length() + 1);
                }
                current.removeFirst();
            }
        }

        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(findAnagrams("cbaebabacd", "abc"), List.of(0, 6));
        Assert.assertEquals(findAnagrams("abc", "abc"), List.of(0));
        Assert.assertEquals(findAnagrams("abddca", "abc"), List.of());
    }
}
