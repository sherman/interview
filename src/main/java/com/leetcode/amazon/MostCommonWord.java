package com.leetcode.amazon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        // 1). Copy the banned word list to set (lowercase)
        Set<String> bannedWords = Arrays.stream(banned)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
        // 2). Read chars in paragraph, splitting space and cleaning by the list of punctuation symbols.
        // 3). Make frequent map filtered by the banned list.
        char[] chars = paragraph.toCharArray();
        StringBuilder builder = new StringBuilder();
        Map<String, Integer> freq = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (skip(chars[i])) {
                if (builder.length() > 0) {
                    String newWord = builder.toString().toLowerCase();
                    if (!bannedWords.contains(newWord)) {
                        int v = freq.computeIfAbsent(newWord, ignored -> 0);
                        freq.put(newWord, v + 1);
                    }
                }
                builder = new StringBuilder();
            } else {
                builder.append(chars[i]);
            }
        }

        if (builder.length() > 0) {
            String newWord = builder.toString().toLowerCase();
            if (!bannedWords.contains(newWord)) {
                int v = freq.computeIfAbsent(newWord, ignored -> 0);
                freq.put(newWord, v + 1);
            }
        }

        int max = Integer.MIN_VALUE;
        String maxFrequentWord = null;
        // 4). Get most frequent word
        for (String word : freq.keySet()) {
            if (freq.get(word) > max) {
                max = freq.get(word);
                maxFrequentWord = word;
            }
        }
        return maxFrequentWord;
    }

    private boolean skip(char c) {
        return c == ' ' || c == '!' || c == '?' || c == '\'' || c == ',' || c == ';' || c == '.';
    }

    @Test
    public void test() {
        Assert.assertEquals(
                mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"}),
                "ball"
        );

        Assert.assertEquals(
                mostCommonWord("a.", new String[]{}),
                "a"
        );

        Assert.assertEquals(
                mostCommonWord(" a.", new String[]{"b"}),
                "a"
        );

        Assert.assertEquals(
                mostCommonWord("a, a, a, a, b,b,b,c, c", new String[]{"a"}),
                "b"
        );
    }

}
