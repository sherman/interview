package com.leetcode.assorted_2025;

import org.testng.Assert;
import org.testng.annotations.Test;

public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        var tp = 0;
        for (var i = 0; i < t.length(); i++) {
            if (tp >= s.length()) {
                return true;
            }
            if (s.charAt(tp) == t.charAt(i)) {
                tp++;
            }
        }
        return tp == s.length();
    }

    @Test
    public void test() {
        Assert.assertTrue(isSubsequence("abc", "ahbgdc"));
    }
}
