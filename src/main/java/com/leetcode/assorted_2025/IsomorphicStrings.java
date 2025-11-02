package com.leetcode.assorted_2025;

import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IsomorphicStrings {

    public boolean isIsomorphic(String s, String t) {
        if (s.length() == 1 && t.length() == 1) {
            return true;
        }

        var mapping1 = new HashMap<Character, Character>();
        var mapping2 = new HashMap<Character, Character>();

        for (int i = 0; i < s.length(); i++) {
            var c1 = s.charAt(i);
            var c2 = t.charAt(i);

            var mapped1 = mapping1.get(c1);
            var mapped2 = mapping2.get(c2);
            if (mapped1 == null && mapped2 == null) {
                mapping1.put(c1, c2);
                mapping2.put(c2, c1);
            } else {
                // if mappings are not equals
                var a1 = (mapped1 != null && c2 == mapped1);
                var a2 = (mapped2 != null && c1 == mapped2);
                if (!(a1 && a2)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void test() {
        Assert.assertTrue(isIsomorphic("paper", "title"));
        Assert.assertFalse(isIsomorphic("bbbaaaba", "aaabbbba"));
        Assert.assertFalse(isIsomorphic("ab", "aa"));
        Assert.assertTrue(isIsomorphic("egg", "add"));
        Assert.assertFalse(isIsomorphic("foo", "bar"));
        Assert.assertFalse(isIsomorphic("badc", "baba"));
    }
}
