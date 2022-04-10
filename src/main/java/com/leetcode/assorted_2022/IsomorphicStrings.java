package com.leetcode.assorted_2022;

import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        if (s.length() == 1) {
            return true;
        }

        var mapping1 = new HashMap<Character, Character>();
        var mapping2 = new HashMap<Character, Character>();

        for (int i = 0; i < s.length(); i++) {
            var mapping = mapping1.get(s.charAt(i));
            // ok, we have such mapping
            if (mapping == null) {
                // if t.charAt(i) is already mapped
                if (mapping2.containsKey(t.charAt(i)) && mapping2.get(t.charAt(i)) != s.charAt(i)) {
                    return false;
                }

                mapping1.put(s.charAt(i), t.charAt(i));
                mapping2.put(t.charAt(i), s.charAt(i));
            } else {
                // if mapping is not equals on the right side
                if (mapping != t.charAt(i)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Test
    public void test() {
        Assert.assertFalse(isIsomorphic("bbbaaaba", "aaabbbba"));
        Assert.assertFalse(isIsomorphic("ab", "aa"));
        Assert.assertTrue(isIsomorphic("egg", "add"));
        Assert.assertFalse(isIsomorphic("foo", "bar"));
        Assert.assertTrue(isIsomorphic("paper", "title"));
        Assert.assertFalse(isIsomorphic("badc", "baba"));
    }
}
