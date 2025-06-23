package com.leetcode.assorted_2025;

public class IsPalindromeII {
    public boolean validPalindrome(String s) {
        if (isPalindrome(s)) {
            return true;
        }

        for (var i = 0; i < s.length(); i++) {
            var s1 = s.substring(0, i);
            var s2 = s.substring(i + 1);
            if (isPalindrome(s1 + s2)) {
                return true;
            }
        }

        return false;
    }

    private boolean isPalindrome(String s) {
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
