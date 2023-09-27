package com.leetcode.assorted_2023;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class Permutations {
    private static final Logger logger = LoggerFactory.getLogger(Permutations.class);

    private List<String> getPermutations(String number) {
        var result = new ArrayList<String>();
        recursion(result, number.toCharArray(), 0);
        return result;
    }

    private static void recursion(List<String> result, char[] chars, int position) {
        if (position == chars.length - 1) {
            logger.info("[{}]", chars);
            result.add(new String(chars));
        }

        for (var i = position; i < chars.length; i++) {
            swap(chars, position, i);
            recursion(result, chars, position + 1);
            swap(chars, position, i);
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    @Test
    public void test1() {
        assertEquals(getPermutations("123"), ImmutableList.of("123", "132", "213", "231", "321", "312"));
    }
}
