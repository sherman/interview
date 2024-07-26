package com.leetcode.assorted_2024;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        var result = "";
        for (var i = s.length(); i >= 0; i--) {
            for (var j = 0; j < s.length(); j++) {
                if (j <= i) {
                    var sub = s.substring(j, i);
                    if (i - j + 1 > result.length()) {
                        if (isPalindrome(sub)) {
                            result = sub;
                        }
                    }
                }
            }
        }

        return result;
    }

    public static boolean isPalindrome(String string) {
        char[] chars = string.toCharArray();

        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - i - 1]) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void test() {
        Assert.assertEquals(longestPalindrome("a"), "a");
        Assert.assertEquals(longestPalindrome("abacccc"), "cccc");
        Assert.assertEquals(longestPalindrome("cccc"), "cccc");
        Assert.assertEquals(longestPalindrome("abacccc"), "cccc");
        Assert.assertEquals(longestPalindrome("aba"), "aba");
        Assert.assertEquals(longestPalindrome("ab"), "a");
    }
}
