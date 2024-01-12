package com.leetcode.assorted_2022;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.LinkedList;

public class LongestSubstringWithoutRepeatingCharacters {
    private static final Logger logger = LoggerFactory.getLogger(LongestSubstringWithoutRepeatingCharacters.class);

    public int lengthOfLongestSubstring(String s) {
        var uniqueLetters = new HashSet<Character>();
        var window = new LinkedList<Character>();
        int maxSize = 0;

        var chars = s.toCharArray();
        for (var i = 0; i < chars.length; i++) {
            logger.info("[{}]", window);

            // try to add a char
            if (uniqueLetters.add(chars[i])) {
                window.offer(chars[i]);
            } else {
                // update result
                maxSize = Math.max(maxSize, window.size());

                // drop all letters before current
                while (!window.isEmpty()) {
                    var dropped = window.poll();
                    uniqueLetters.remove(dropped);

                    if (window.isEmpty() || dropped == chars[i]) {
                        break;
                    }
                }
                // update state
                uniqueLetters.add(chars[i]);
                window.add(chars[i]);
            }
        }

        maxSize = Math.max(maxSize, window.size());

        return maxSize;
    }

    @Test
    public void test() {
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
            lengthOfLongestSubstring("abcabcbb"),
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
