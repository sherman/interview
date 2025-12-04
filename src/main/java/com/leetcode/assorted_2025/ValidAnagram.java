package com.leetcode.assorted_2025;

import java.util.HashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        var index = new HashMap<Character, Integer>();
        for (var c : s.toCharArray()) {
            var count = index.getOrDefault(c, 0);
            index.put(c, count + 1);
        }

        for (var c : t.toCharArray()) {
            var count = index.get(c);
            if (count == null) {
                return false;
            }
            if (count == 1) {
                index.remove(c);
            } else {
                index.put(c, count - 1);
            }
        }

        return index.isEmpty();
    }

    @Test
    void test() {
        Assertions.assertTrue(isAnagram("anagram", "nagaram"));
    }
}
