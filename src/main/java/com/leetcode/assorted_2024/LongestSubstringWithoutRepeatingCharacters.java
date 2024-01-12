package com.leetcode.assorted_2024;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayDeque;
import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }

        var uniqueChars = new HashSet<Character>();
        var deque = new ArrayDeque<Character>();
        var maxSize = Integer.MIN_VALUE;

        for (var i = 0; i < s.length(); i++) {
            var c = s.charAt(i);
            if (uniqueChars.isEmpty()) {
                uniqueChars.add(c);
                deque.addLast(c);
            } else if (uniqueChars.contains(c)) {
                while (!deque.isEmpty()) {
                    var head = deque.getFirst();
                    if (head == c) {
                        deque.removeFirst();
                        uniqueChars.remove(c);
                        break;
                    } else {
                        deque.removeFirst();
                        uniqueChars.remove(head);
                    }
                }

                uniqueChars.add(c);
                deque.addLast(c);
            } else {
                uniqueChars.add(c);
                deque.addLast(c);
            }

            maxSize = Math.max(maxSize, uniqueChars.size());
        }

        return maxSize;
    }

    @Test
    public void test() {
        /*Assert.assertEquals(
            lengthOfLongestSubstring("nfpdmpi"),
            5
        );*/

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
