package com.leetcode.assorted_2023;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeMap;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        var current = new TreeMap<Character, Integer>();

        var count = 0;
        var neededCount = 0;

        var needed = new TreeMap<Character, Integer>();
        for (var i = 0; i < p.length(); i++) {
            var counter = needed.computeIfAbsent(p.charAt(i), ignored -> 0);
            needed.put(p.charAt(i), counter + 1);
            neededCount++;
        }

        var result = new ArrayList<Integer>();
        for (var i = 0; i < s.length(); i++) {
            if (current.size() < p.length()) {
                if (needed.containsKey(s.charAt(i))) {
                    var counter = current.computeIfAbsent(s.charAt(i), ignored -> 0);
                    current.put(s.charAt(i), counter + 1);
                    count++;
                } else {
                    if (!current.isEmpty()) {
                        current.clear();
                        count = 0;
                    }
                }
            }

            if (count == neededCount) {
                if (current.equals(needed)) {
                    result.add(i - p.length() + 1);
                }
            }

            // clean up
            if (count >= neededCount) {
                if (i - p.length() + 1 >= 0) {
                    var currentChar = s.charAt(i - p.length() + 1);
                    var currentCharCount = current.get(currentChar);
                    if (currentCharCount != null) {
                        if (currentCharCount == 1) {
                            current.remove(currentChar);
                        } else {
                            current.put(currentChar, currentCharCount - 1);
                        }
                    }
                }
                count--;
            }
        }

        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(findAnagrams("aaabaaa", "aaa"), List.of(0,4));
        Assert.assertEquals(findAnagrams("baa", "aa"), List.of(1));
        Assert.assertEquals(findAnagrams("cbaebabacd", "abc"), List.of(0, 6));
        Assert.assertEquals(findAnagrams("abc", "abc"), List.of(0));
        Assert.assertEquals(findAnagrams("abddca", "abc"), List.of());
    }
}
