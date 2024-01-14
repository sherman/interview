package com.leetcode.assorted_2024;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayDeque;
import java.util.HashSet;

public class LongestRepeatingCharacterReplacementSlidingWindow {
    public static int characterReplacement(String s, int k) {
        var uniqueChars = new HashSet<Character>();

        // create unique character list
        for (var i = 0; i < s.length(); i++) {
            uniqueChars.add(s.charAt(i));
        }

        var max = 0;
        var queue = new ArrayDeque<Character>();

        for (var c : uniqueChars) {
            var remainingChars = k;
            queue.clear();
            for (var i = 0; i < s.length(); i++) {
                var current = s.charAt(i);
                if (current != c) {
                    if (remainingChars > 0) {
                        remainingChars--;
                        queue.add(current);
                    } else {
                        if (!queue.isEmpty()) {
                            max = Math.max(max, queue.size());
                            if (queue.size() >= 6) {
                                var x = 1;
                            }
                        }

                        // drop all until
                        while (!queue.isEmpty()) {
                            var head = queue.peek();
                            if (head == c) {
                                queue.poll();
                            } else {
                                // found another char, stop here
                                queue.poll();
                                break;
                            }
                        }
                        if (k > 0) {
                            queue.add(current);
                        }
                    }
                } else {
                    queue.add(current);
                }
            }
            if (!queue.isEmpty()) {
                max = Math.max(max, queue.size());
                if (queue.size() >= 6) {
                    var x = 1;
                }
            }
        }

        return max;
    }

    @Test
    public void test() {
        Assert.assertEquals(LongestRepeatingCharacterReplacementSlidingWindow.characterReplacement("AABBBAAABBAAA", 2), 8);
        Assert.assertEquals(LongestRepeatingCharacterReplacementSlidingWindow.characterReplacement("KRSCDCSONAJNHLBMDQGIFCPEKPOHQIHLTDIQGEKLRLCQNBOHNDQGHJPNDQPERNFSSSRDEQLFPCCCARFMDLHADJADAGNNSBNCJQOF", 4), 7);
        Assert.assertEquals(LongestRepeatingCharacterReplacementSlidingWindow.characterReplacement("ABAA", 0), 2);
        Assert.assertEquals(LongestRepeatingCharacterReplacementSlidingWindow.characterReplacement("ABAB", 2), 4);
        Assert.assertEquals(LongestRepeatingCharacterReplacementSlidingWindow.characterReplacement("AABABBA", 1), 4);
    }
}
