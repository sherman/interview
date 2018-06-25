package com.leetcode;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LongestWordInDictionaryTest {
    @Test
    public void test() {
        String word = LongestWordInDictionary.longestWord(new String[]{"w","wo","wor","worl", "world"});
        assertEquals(word, "world");
    }
}
