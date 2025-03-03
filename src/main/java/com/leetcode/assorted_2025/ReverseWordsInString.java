package com.leetcode.assorted_2025;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ReverseWordsInString {
    public String reverseWords(String s) {
        var trimmed = s.trim();
        var words = trimmed.split("\s+");
        var builder = new StringBuilder();
        for (var i = words.length - 1; i >= 0; i--) {
            builder.append(words[i]);
            if (i != 0) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals(reverseWords("the sky is blue"), "blue is sky the");
    }
}
