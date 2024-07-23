package com.leetcode.assorted_2022;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InterleavingString {
    private static final Logger logger = LoggerFactory.getLogger(InterleavingString.class);
    private final Map<String, Boolean> cache = new HashMap<>();

    public boolean isInterleave(String s1, String s2, String s3) {
        var builder = new StringBuilder();
        return isInterleave(s1, s2, s3, 0, 0, 0, builder);
    }

    public boolean isInterleave(
        String s1,
        String s2,
        String needed,
        int pointer1,
        int pointer2,
        int pointer,
        StringBuilder builder
    ) {
        logger.info("[{}] [{}] [{}]", builder.toString(), pointer1, pointer2);
        // no more chars left
        if (pointer == needed.length()) {
            var result = builder.toString();
            var equals = result.length() == s1.length() + s2.length() && result.equals(needed);
            logger.info("R: [{}]", result);
            return equals;
        }

        var c1 = getNextChar(pointer1, s1);

        if (c1 != null) {
            builder.append(c1);
            var r1 = isInterleave(
                s1,
                s2,
                needed,
                pointer1 + 1,
                pointer2,
                pointer + 1,
                builder
            );
            builder.deleteCharAt(builder.length() - 1);
            if (r1) {
                return true;
            }
        }

        var c2 = getNextChar(pointer2, s2);

        if (c2 != null) {
            builder.append(c2);
            var r2 = isInterleave(
                s1,
                s2,
                needed,
                pointer1,
                pointer2 + 1,
                pointer + 1,
                builder
            );
            builder.deleteCharAt(builder.length() - 1);
            return r2;
        }

        return false;
    }

    private static Character getNextChar(int i, String s) {
        if (i < s.length()) {
            return s.charAt(i);
        } else {
            return null;
        }
    }

    @Test
    public void test() {
        Assert.assertFalse(isInterleave("a", "b", "a"));
        Assert.assertTrue(isInterleave("a", "", "a"));
        Assert.assertTrue(isInterleave("", "a", "a"));
        Assert.assertFalse(isInterleave("a", "", "c"));
        Assert.assertTrue(isInterleave("aa", "bb", "aabb"));
        Assert.assertTrue(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        Assert.assertFalse(isInterleave("aabcc", "dbbca", "aadbbbaccc"));
    }
}

