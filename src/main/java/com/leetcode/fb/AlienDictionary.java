package com.leetcode.fb;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlienDictionary {
    private static final Logger logger = LoggerFactory.getLogger(AlienDictionary.class);

    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> index = getIndex(order);

        int max = Arrays.stream(words)
                .max(Comparator.comparing(String::length))
                .get()
                .length();

        for (int i = 0; i <= max; i++) {
            for (int j = 1; j < words.length; j++) {
                if (!compareWords(words[j - 1], words[j], index)) {
                    return false;
                }
            }
        }

        return true;
    }

    private Map<Character, Integer> getIndex(String order) {
        Map<Character, Integer> data = new HashMap<>();
        char[] chars = order.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            data.put(chars[i], i);
        }
        return data;
    }

    private int getCharNumber(String word, int i, Map<Character, Integer> index) {
        char[] chars = word.toCharArray();
        if (i < chars.length) {
            return index.get(chars[i]);
        } else {
            return -1;
        }
    }

    private boolean compareWords(String word1, String word2, Map<Character, Integer> index) {
        int one = 0;
        int two = 0;
        while (one < word1.length() && two < word2.length()) {
            int pos1 = getCharNumber(word1, one, index);
            int pos2 = getCharNumber(word2, one, index);

            if (pos1 > pos2) {
                return false;
            } else if (pos1 == pos2) {
                one++;
                two++;
            } else {
                return true;
            }
        }

        if (word1.length() > word2.length()) {
            return false;
        }

        return true;
    }

    @Test
    public void test() {
        Assert.assertTrue(isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
        Assert.assertFalse(isAlienSorted(new String[]{"word", "world", "row"}, "worldabcefghijkmnpqstuvxyz"));
    }
}

