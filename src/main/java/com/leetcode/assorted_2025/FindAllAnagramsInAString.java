package com.leetcode.assorted_2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        var lettersFreq = new int[26];
        var need = new int[26];

        // make a freq. table for p
        for (var i = 0; i < p.length(); i++) {
            var c = p.charAt(i);
            need[c - 'a']++;
        }

        // find all anagrams
        var result = new ArrayList<Integer>();
        for (var i = 0; i < s.length(); i++) {
            var c = s.charAt(i);
            lettersFreq[c - 'a']++;
            if (i >= p.length()) {
                var remove = s.charAt(i - p.length());
                lettersFreq[remove - 'a']--;
            }
            if (Arrays.equals(lettersFreq, need)) {
                result.add(i - p.length() + 1);
            }
        }
        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(findAnagrams("aaabaaa", "aaa"), List.of(0, 4));
        Assert.assertEquals(findAnagrams("baa", "aa"), List.of(1));
        Assert.assertEquals(findAnagrams("cbaebabacd", "abc"), List.of(0, 6));
        Assert.assertEquals(findAnagrams("abc", "abc"), List.of(0));
        Assert.assertEquals(findAnagrams("abddca", "abc"), List.of());
    }
}
