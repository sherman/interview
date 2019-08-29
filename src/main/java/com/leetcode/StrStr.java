package com.leetcode;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class StrStr {

    public static int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }

        char[] data = haystack.toCharArray();

        for (int i = 0; i < data.length; i++) {
            if (data[i] == needle.charAt(0)) {
                int len = 0;
                for (int j = i; j < data.length && len < needle.length(); j++) {
                    if (data[j] == needle.charAt(len)) {
                        len++;
                    } else {
                        break;
                    }

                }

                if (len == needle.length()) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Test(dataProvider = "strings")
    public void tests(String haystack, String needle, int expected) {
        assertEquals(strStr(haystack, needle), expected);
    }

    @DataProvider
    private static Object[][] strings() {
        return new Object[][]{
            {"hello", "ll", 2},

            {"", "", 0},

            {"", "a", -1},

            {"mississippi", "issip", 4},

            {"aabaaabaaac", "aabaaac", 4}
        };
    }

}
