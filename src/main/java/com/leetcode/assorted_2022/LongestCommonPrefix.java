package com.leetcode.assorted_2022;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }

        var prefix = new StringBuilder();

        var length = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            length = Math.min(length, strs[i].length());
        }

        for (int i = 0; i < length; i++) {
            var letter = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != letter) {
                    return prefix.toString();
                }
            }
            prefix.append(letter);
        }

        return prefix.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals(longestCommonPrefix(new String[] {"flower", "flow", "flight"}), "fl");
    }
}
