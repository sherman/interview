package com.leetcode.assorted_2024;

import java.util.ArrayDeque;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StrStr {
    private static final Logger logger = LoggerFactory.getLogger(StrStr.class);

    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }

        int needleLength = needle.length();
        int charLength = haystack.length();

        for (int windowStart = 0; windowStart <= charLength - needleLength; windowStart++) {
            for (int i = 0; i < needleLength; i++) {
                if (needle.charAt(i) != haystack.charAt(windowStart + i)) {
                    break;
                }
                if (i == needleLength - 1) {
                    return windowStart;
                }
            }
        }

        return -1;
    }

    @Test
    public void test() {
        Assert.assertEquals(strStr("hello", "ll"), 2);
        Assert.assertEquals(strStr("aaaaa", "bba"), -1);
        Assert.assertEquals(strStr("", ""), 0);
        Assert.assertEquals(strStr("mississippi", "issip"), 4);
        Assert.assertEquals(strStr("mississippi", "pi"), 9);
        Assert.assertEquals(strStr("aabaaabaaac", "aabaaac"), 4);
    }
}
