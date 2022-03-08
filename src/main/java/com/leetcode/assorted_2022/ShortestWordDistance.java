package com.leetcode.assorted_2022;

import static org.testng.Assert.assertEquals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class ShortestWordDistance {
    private static final Logger logger = LoggerFactory.getLogger(ShortestWordDistance.class);

    public static int getShortestWordDistance(String[] words, String word1, String word2) {
        int distance = Integer.MAX_VALUE;

        int l = -1;
        int m = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                l = i;
            } else if (words[i].equals(word2)) {
                m = i;
            }

            if (l != -1 && m != -1) {
                distance = Math.min(distance, Math.abs(l - m));
            }
        }

        return distance;
    }

    @Test
    public void test() {
        assertEquals(getShortestWordDistance(new String[] {"aa", "cc", "bb", "aa", "ff", "bb", "mm", "aa"}, "aa", "bb"), 1);
        assertEquals(getShortestWordDistance(new String[] {"aa", "cc", "bb", "aa", "ff", "cc", "mm", "cc", "ff", "aa"}, "aa", "cc"), 1);
    }
}
