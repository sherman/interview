package com.leetcode.assorted_2023;

public class IsPalindrome {
    public boolean isPalindrome(String s) {
        var chars = s.toCharArray();
        var l = 0;
        var r = chars.length - 1;

        while (l < r) {
            var leftChar = Character.toLowerCase(chars[l]);
            var rightChar = Character.toLowerCase(chars[r]);

            if (!isAlphaNumeric(leftChar)) {
                l++;
            } else if (!isAlphaNumeric(rightChar)) {
                r--;
            } else {
                if (leftChar != rightChar) {
                    return false;
                }
                l++;
                r--;
            }
        }

        return true;
    }

    private static boolean isAlphaNumeric(char c) {
        return ((c >= '0' & c <= '9') || (c >= 'a' && c <= 'z'));
    }
}
