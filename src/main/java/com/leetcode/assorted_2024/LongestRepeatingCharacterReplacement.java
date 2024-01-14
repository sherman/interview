package com.leetcode.assorted_2024;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;

public class LongestRepeatingCharacterReplacement {
    public static int characterReplacement(String s, int k) {
        var uniqueChars = new HashSet<Character>();

        // create unique character list
        for (var i = 0; i < s.length(); i++) {
            uniqueChars.add(s.charAt(i));
        }

        var max = 0;

        for (var c : uniqueChars) {
            for (var i = 0; i < s.length(); i++) {
                var remainingChars = k;
                var completed = false;
                for (var j = i; j < s.length(); j++) {
                    var current = s.charAt(j);
                    if (current != c) {
                        if (remainingChars > 0) {
                            remainingChars--;
                        } else if (remainingChars == 0) {
                            max = Math.max(max, j - i);
                            completed = true;
                            break;
                        }
                    }
                }
                if (remainingChars > 0 || !completed) {
                    max = Math.max(max, s.length() - i);
                }
            }
        }

        return max;
    }

    @Test
    public void test() {
        Assert.assertEquals(LongestRepeatingCharacterReplacement.characterReplacement("ABAB", 2), 4);
        Assert.assertEquals(LongestRepeatingCharacterReplacement.characterReplacement("AABABBA", 1), 4);
    }
}
