package com.leetcode;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindCommonCharacters {
    private static final Logger logger = LoggerFactory.getLogger(FindCommonCharacters.class);

    public List<String> commonChars(String[] words) {
        int[][] allFreq = new int[words.length][];

        for (int j = 0; j < words.length; j++) {
            int[] freq = new int[26];
            String word = words[j];
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                freq[chars[i] - 'a']++;
            }
            allFreq[j] = freq;
        }

        List<String> letters = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            int min = Integer.MAX_VALUE;
            char letter = (char) ((char) i + 'a');
            for (int j = 0; j < words.length; j++) {
                min = Math.min(allFreq[j][i], min);
            }
            if (min < Integer.MAX_VALUE) {
                for (int k = 0; k < min; k++) {
                    letters.add(String.valueOf(letter));
                }
            }
        }

        logger.info("[{}]", letters);

        return letters;
    }

    @Test
    public void test() {
        Assert.assertEquals(ImmutableList.of("e", "l", "l"), commonChars(new String[]{"bella", "label", "roller"}));
        Assert.assertEquals(ImmutableList.of(), commonChars(new String[]{"a", "b", "c"}));
        Assert.assertEquals(ImmutableList.of("a", "a"), commonChars(new String[]{"aa", "baa", "caa", "bxaaa"}));
    }
}
