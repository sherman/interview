package com.leetcode.assorted_2023;

import java.util.HashMap;

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        var letters = new HashMap<Character, Integer>();

        var lettersNum = 0;
        for (var i = 0; i < s.length(); i++) {
            var count = letters.getOrDefault(s.charAt(i), 0);
            letters.put(s.charAt(i), count + 1);
            lettersNum++;
        }

        for (var i = 0; i < t.length(); i++) {
            var letter = t.charAt(i);
            var count = letters.get(letter);
            if (count == null) {
                return false;
            }
            if (count == 0) {
                return false;
            }
            letters.put(letter, count - 1);

            lettersNum--;
        }

        return lettersNum == 0;
    }
}
