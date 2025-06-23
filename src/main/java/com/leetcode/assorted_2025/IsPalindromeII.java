package com.leetcode.assorted_2025;

public class IsPalindromeII {
    public boolean validPalindrome(String s) {
        var l = 0;
        var r = s.length() - 1;

        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return isPalindrome(s, l, r - 1) || isPalindrome(s, l + 1, r);
            }
            l++;
            r--;
        }

        return true;
    }

    private boolean isPalindrome(String s, int l, int r) {
        var chars = s.toCharArray();

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
