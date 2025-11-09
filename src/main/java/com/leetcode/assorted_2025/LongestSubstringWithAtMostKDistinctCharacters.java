package com.leetcode.assorted_2025;

import java.util.HashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongestSubstringWithAtMostKDistinctCharacters {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        var maxLen = 0;

        for (var i = 0; i < s.length(); i++) {
            var chars = new HashMap<Character, Integer>();
            for (var j = i; j < s.length(); j++) {
                var c = s.charAt(j);
                var num = chars.getOrDefault(c, 0);
                chars.put(c, num + 1);
                if (chars.size() <= k) {
                    var len = j - i + 1;
                    maxLen = Math.max(maxLen, len);
                }
            }
        }

        return maxLen;
    }

    @Test
    public void test() {
        Assertions.assertEquals(3, lengthOfLongestSubstringKDistinct("ecebe", 2));
        Assertions.assertEquals(2, lengthOfLongestSubstringKDistinct("aa", 1));
        Assertions.assertEquals(6, lengthOfLongestSubstringKDistinct("abggdgee", 3));
    }
}
