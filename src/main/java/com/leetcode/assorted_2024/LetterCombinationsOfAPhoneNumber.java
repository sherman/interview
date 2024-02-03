package com.leetcode.assorted_2024;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfAPhoneNumber {
    private static final Logger logger = LoggerFactory.getLogger(LetterCombinationsOfAPhoneNumber.class);

    private static final Map<Character, List<Character>> data = Map.of(
        '2', List.of('a', 'b', 'c'),
        '3', List.of('d', 'e', 'f'),
        '4', List.of('g', 'h', 'i'),
        '5', List.of('j', 'k', 'l'),
        '6', List.of('m', 'n', 'o'),
        '7', List.of('q', 'p', 'r', 's'),
        '8', List.of('t', 'u', 'v'),
        '9', List.of('w', 'x', 'y', 'z')
    );

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return List.of();
        } else {
            var result = new ArrayList<String>();
            backtrack(0, digits, "", result);
            return result;
        }
    }

    public void backtrack(int i, String digits, String current, List<String> result) {
        logger.info("[{}] [{}]", i, current);
        if (current.length() == digits.length()) {
            result.add(current);
            return;
        }

        var letters = data.get(digits.charAt(i));
        for (var letter : letters) {
            backtrack(i + 1, digits, current + letter, result);
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(
            ImmutableSet.copyOf(letterCombinations("23")),
            ImmutableSet.copyOf(ImmutableList.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"))
        );

        Assert.assertEquals(
            ImmutableSet.copyOf(letterCombinations("")),
            ImmutableSet.copyOf(ImmutableList.of())
        );
    }
}
