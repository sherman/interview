package com.leetcode;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class WordPattern {
    boolean isMatched(String pattern, String words) {
        Map<String, Character> dict = new HashMap<>();
        Map<Character, String> reversedDict = new HashMap<>();

        String[] splited = words.split(" ");

        if (pattern.length() != splited.length) {
            return false;
        }

        for (int i = 0; i < splited.length; i++) {
            Character saved = dict.putIfAbsent(splited[i], pattern.charAt(i));

            String strSaved = reversedDict.putIfAbsent(pattern.charAt(i), splited[i]);

            if (saved != null && !saved.equals(pattern.charAt(i))) {
                return false;
            }

            if (strSaved != null && !strSaved.equals(splited[i])) {
                return false;
            }
        }

        return true;
    }

    @Test(dataProvider = "cases")
    public void isMatched(String pattern, String words, boolean expected) {
        assertEquals(isMatched(pattern, words), expected);
    }

    @DataProvider
    public static Object[][] cases() {
        return new Object[][] {
            {"aabb", "cat cat dog dog", true},

            {"a", "dog", true},

            {"aaa", "dog cat dog", false},

            {"abba", "cat cat cat cat", false}
        };
    }
}
