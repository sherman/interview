package com.leetcode.assorted_2024;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharactersTowPointers {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }

        var uniqueChars = new HashMap<Character, Integer>();
        var maxSize = Integer.MIN_VALUE;
        var l = 0;
        var r = 0;

        for (var i = 0; i < s.length(); i++) {
            var c = s.charAt(i);
            if (uniqueChars.containsKey(c)) {
                while (r < i) {
                    var cc = s.charAt(r);
                    var count = uniqueChars.get(cc);
                    if (count == 1) {
                        uniqueChars.remove(cc);
                    } else {
                        uniqueChars.put(cc, count - 1);
                    }
                    r++;
                    if (cc == c) {
                        break;
                    }
                }

            }

            var count = uniqueChars.computeIfAbsent(c, ignored -> 0);
            uniqueChars.put(c, count + 1);
            l++;

            maxSize = Math.max(maxSize, l - r);
        }

        return maxSize;
    }

    @Test
    public void test() {
        Assert.assertEquals(
            lengthOfLongestSubstring("abcabcbb"),
            3
        );
        Assert.assertEquals(
            lengthOfLongestSubstring("nfpdmpi"),
            5
        );
        Assert.assertEquals(
            lengthOfLongestSubstring("bbcabxyz"),
            6
        );

        Assert.assertEquals(
            lengthOfLongestSubstring("aabaab!bb"),
            3
        );
        Assert.assertEquals(
            lengthOfLongestSubstring("pwwkew"),
            3
        );

        Assert.assertEquals(
            lengthOfLongestSubstring("aab"),
            2
        );
    }
}
