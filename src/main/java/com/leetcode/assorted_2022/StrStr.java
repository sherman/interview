package com.leetcode.assorted_2022;

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

        var chars = haystack.toCharArray();
        var needleChars = needle.toCharArray();
        int started = -1;
        int position = 0;
        for (int i = 0; i < chars.length; i++) {
            // chars are equals
            if (chars[i] == needleChars[position]) {
                if (started == -1) {
                    started = i;
                }

                // found an answer
                if (position == needleChars.length - 1) {
                    return started;
                }

                position++;
            } else {
                // reset state
                if (started >= 0) {
                    i = started;
                    logger.info("[{}]", started);
                }
                started = -1;
                position = 0;
            }
        }

        if (position == needleChars.length - 1) {
            return started;
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
