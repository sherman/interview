package com.leetcode.assorted_2024;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        var currentState = new HashMap<Character, Integer>();
        var requiredChars = new HashMap<Character, Integer>();

        // create unique character list from t
        for (var i = 0; i < t.length(); i++) {
            var c = t.charAt(i);
            var current = requiredChars.computeIfAbsent(c, ignored -> 0);
            requiredChars.put(c, current + 1);
        }

        // point for window
        var left = 0;
        var right = 0;

        var uniqueRequiredChars = 0;

        var best = "";

        for (var i = 0; i < s.length(); i++) {
            var c = s.charAt(i);

            // add char to current state
            if (requiredChars.containsKey(c)) {
                addChar(c, currentState);
                uniqueRequiredChars = currentState.keySet().size();
            }

            var minLeft = left;
            // check a number of unique chars
            while (uniqueRequiredChars == requiredChars.keySet().size() && left <= right) {
                minLeft = left;
                var currentChar = s.charAt(left);
                if (stateIfFull(requiredChars, currentState)) {
                    var size = right - minLeft;
                    if (best.isEmpty() || size < best.length()) {
                        best = s.substring(minLeft, right + 1);
                    }

                    if (requiredChars.containsKey(currentChar)) {
                        removeChar(currentChar, currentState);
                        if (!currentState.containsKey(currentChar)) {
                            uniqueRequiredChars--;
                        }
                    }
                    left++;
                } else {
                    break;
                }
            }

            if (right < s.length() - 1) {
                right++;
            }
        }

        return best;
    }

    public void addChar(char c, Map<Character, Integer> state) {
        var current = state.computeIfAbsent(c, ignored -> 0);
        state.put(c, current + 1);
    }

    public void removeChar(char c, Map<Character, Integer> state) {
        var current = state.get(c);
        if (current != null) {
            if (current == 1) {
                state.remove(c);
            } else {
                state.put(c, current - 1);
            }
        }
    }

    private static boolean stateIfFull(Map<Character, Integer> expected, Map<Character, Integer> actual) {
        if (expected.size() != actual.size()) {
            return false;
        }

        for (var entry : expected.entrySet()) {
            if (actual.get(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void cases() {
        Assert.assertEquals(minWindow("BBAA", "ABA"), "BAA");
        Assert.assertEquals(minWindow("BDAB", "AB"), "AB");
        Assert.assertEquals(minWindow("AA", "AA"), "AA");
        Assert.assertEquals(minWindow("BBA", "AB"), "BA");
        Assert.assertEquals(minWindow("ABC", "B"), "B");
        Assert.assertEquals(minWindow("AB", "A"), "A");
        Assert.assertEquals(minWindow("AB", "B"), "B");
        Assert.assertEquals(minWindow("A", "AA"), "");
        Assert.assertEquals(minWindow("ADOBECODEBANC", "ABC"), "BANC");
        Assert.assertEquals(minWindow("A", "A"), "A");
    }
}
