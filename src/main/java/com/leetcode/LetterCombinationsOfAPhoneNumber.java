package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LetterCombinationsOfAPhoneNumber {
    private static final Logger logger = LoggerFactory.getLogger(LetterCombinationsOfAPhoneNumber.class);

    // 2 abc
    // 3 def
    // 4 ghi
    // 5 jkl
    // 6 mno
    // 7 pqrs
    // 8 tuv
    // 9 wxyz

    private Map<Character, List<Character>> index;

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return new ArrayList<>();
        }

        index = new HashMap<>();

        index.put('2', Arrays.asList('a', 'b', 'c'));
        index.put('3', Arrays.asList('d', 'e', 'f'));
        index.put('4', Arrays.asList('g', 'h', 'i'));
        index.put('5', Arrays.asList('j', 'k', 'l'));
        index.put('6', Arrays.asList('m', 'n', 'o'));
        index.put('7', Arrays.asList('p', 'q', 'r', 's'));
        index.put('8', Arrays.asList('t', 'u', 'v'));
        index.put('9', Arrays.asList('w', 'x', 'y', 'z'));

        List<List<Character>> result = new ArrayList<>();

        backtracking(result, digits.toCharArray(), new ArrayList<>(), 0);

        logger.info("[{}]", result);

        return result.stream()
                .map(l -> l.stream().map(String::valueOf).collect(Collectors.joining()))
                .collect(Collectors.toList());
    }

    private void backtracking(List<List<Character>> result, char[] digits, List<Character> temp, int start) {
        if (temp.size() == digits.length) {
            result.add(new ArrayList<>(temp));
        }
        for (int i = start; i < digits.length; i++) {
            if (digits[i] != '1') {
                for (int j = 0; j < index.get(digits[i]).size(); j++) {
                    temp.add(index.get(digits[i]).get(j));
                    backtracking(result, digits, temp, i + 1);
                    temp.remove(temp.size() - 1);
                }
            }
        }
    }


    @Test
    public void test() {
        Assert.assertEquals(
                ImmutableSet.copyOf(letterCombinations("23")),
                ImmutableSet.copyOf(ImmutableList.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"))
        );

        Assert.assertEquals(
                ImmutableSet.copyOf(letterCombinations("1")),
                ImmutableSet.copyOf(ImmutableList.of())
        );
    }
}
