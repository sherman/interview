package com.leetcode.assorted_2025;

public class ReverseVowelsOfAString {
    private static final String VOWELS = "aeiouAEIOU";

    public String reverseVowels(String s) {
        var chars = s.toCharArray();
        var l = 0;
        var r = chars.length - 1;
        while (l < r) {
            var lc = chars[l];
            var rc = chars[r];
            var leftIsVowel = isVowel(lc);
            var rightIsVowel = isVowel(rc);

            if (leftIsVowel && rightIsVowel) {
                chars[r] = lc;
                chars[l] = rc;
                l++;
                r--;
            } else {
                if (!leftIsVowel) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return new String(chars);
    }

    private static boolean isVowel(char c) {
        return VOWELS.indexOf(c) != -1;
    }
}
