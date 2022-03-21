package com.leetcode.assorted_2022;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BiggerIsGreater {
    private static final Logger log = LoggerFactory.getLogger(BiggerIsGreater.class);

    /**
     * @param words
     * @return
     */
    public static List<String> biggerIsGreater(List<String> words) {
        return words.stream()
            .map(BiggerIsGreater::biggerIsGreater)
            .toList();
    }

    public static String biggerIsGreater(String word) {
        if (word.length() == 1) {
            return "no answer";
        }

        var chars = word.toCharArray();

        // find longest non-increasing suffix
        int i = chars.length - 1;
        while (i > 0 && chars[i - 1] >= chars[i]) {
            i--;
            //log.info("[{}]", chars[i]);
        }

        if (i <= 0) {
            return "no answer";
        }

        //abcfdda
        //lmno
        log.info("[{}]", chars[i]);

        // Find the rightmost successor to pivot in the suffix
        int j = i;
        while (j < chars.length && chars[j] > chars[i - 1]) {
            j++;
        }

        log.info("[{}]", j);

        // swap
        char temp = chars[i - 1];
        chars[i - 1] = chars[j - 1];
        chars[j - 1] = temp;

        log.info("[{}]", chars);

        char[] newChar = new char[chars.length];
        for (int k = 0; k < i; k++) {
            newChar[k] = chars[k];
        }
        log.info("l: [{}]", newChar);
        int end = chars.length - 1;
        for (int k = i; k < chars.length; k++) {
            newChar[k] = chars[end--];
        }
        return new String(newChar);
    }

    @Test
    public void test() {
        Assert.assertEquals(
            biggerIsGreater(List.of("abcfdda")),
            List.of("abdacdf")
        );

        Assert.assertEquals(
            biggerIsGreater(List.of("dkhc")),
            List.of("hcdk")
        );

        Assert.assertEquals(
            biggerIsGreater(List.of("lmno", "dcba", "dcbb", "abdc", "abcd", "fedcbabcd")),
            List.of("lmon", "no answer", "no answer", "acbd", "abdc", "fedcbabdc")
        );
    }
}
