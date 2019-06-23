package com.leetcode;

/**
 * @author Denis M. Gabaydulin
 * @since 23.06.19
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        int len = getMinLength(strs);

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < len; i++) {
            Character c = null;

            boolean exit = false;

            for (String str : strs) {
                if (c == null) {
                    c = str.charAt(i);
                } else {
                    if (c != str.charAt(i)) {
                        return builder.toString();
                    }
                }
            }

            builder.append(c);
        }

        return builder.toString();
    }

    private int getMinLength(String[] strs) {
        int minLen = Integer.MAX_VALUE;

        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }

        return minLen;
    }
}
