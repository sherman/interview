package com.leetcode.assorted_2025;

import java.util.HashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongestSubstringWithAtMostKDistinctCharacters {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        var maxLen = 0; // best solution
        var start = 0; // start of the window
        var chars = new HashMap<Character, Integer>(); // chars stat tracking

        for (var i = 0; i < s.length(); i++) {
            var c = s.charAt(i);
            var num = chars.getOrDefault(c, 0);
            chars.put(c, num + 1);
            if (chars.size() <= k) {
                maxLen = Math.max(maxLen, i - start + 1);
            } else {
                var charToRemove = s.charAt(start);
                var num2 = chars.get(charToRemove);
                if (num2 > 1) {
                    chars.put(charToRemove, num2 - 1);
                } else {
                    chars.remove(charToRemove);
                }
                start++;
            }
        }

        if (chars.size() <= k) {
            maxLen = Math.max(maxLen, s.length() - start);
        }

        return maxLen;
    }

    @Test
    public void test() {
        Assertions.assertEquals(3, lengthOfLongestSubstringKDistinct("ecebe", 2));
        Assertions.assertEquals(2, lengthOfLongestSubstringKDistinct("aa", 1));
        Assertions.assertEquals(2, lengthOfLongestSubstringKDistinct("abee", 1));
        Assertions.assertEquals(6, lengthOfLongestSubstringKDistinct("abggdgee", 3));
    }
}
