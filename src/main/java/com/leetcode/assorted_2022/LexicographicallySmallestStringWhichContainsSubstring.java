package com.leetcode.assorted_2022;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LexicographicallySmallestStringWhichContainsSubstring {
    private static final Logger logger = LoggerFactory.getLogger(LexicographicallySmallestStringWhichContainsSubstring.class);

    private static int getNumberOfPlaceholders(String data) {
        int count = 0;
        for (var i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '?') {
                count++;
            }
        }
        return count;
    }

    public String findString(String str, String substring) {
        var placeholders = getNumberOfPlaceholders(str);

        var result = new ArrayList<String>();
        var source = new ArrayList<Character>();
        for (var i = 0; i < substring.length(); i++) {
            source.add(substring.charAt(i));
        }

        for (var i = 0; i < placeholders - substring.length(); i++) {
            source.add('a');
        }

        generatePermutations(new ArrayList<>(), source, result);

        logger.info("[{}] [{}] [{}]", placeholders, result, result.size());

        String resultStr = null;
        for (var element : result) {
            var candidate = str;
            for (var i = 0; i < element.length(); i++) {
                candidate = candidate.replaceFirst("[?]", String.valueOf(element.charAt(i)));
            }

            if (candidate.contains(substring)) {
                if (resultStr == null) {
                    resultStr = candidate;
                } else {
                    if (candidate.compareTo(resultStr) < 0) {
                        resultStr = candidate;
                    }
                }
            }

        }

        return resultStr;
    }

    private void generatePermutations(List<Character> selected, List<Character> source, List<String> result) {
        if (source.isEmpty()) {
            StringBuilder b = new StringBuilder();
            selected.forEach(b::append);
            result.add(b.toString());
            return;
        }

        for (var i = 0; i < source.size(); i++) {
            var character = source.remove(i);
            selected.add(character);

            generatePermutations(selected, source, result);

            source.add(i, character);
            selected.remove(character);
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(findString("a?b?dk??", "abc"), "aabcdkab");
        Assert.assertEquals(findString("a?b?dk???", "abc"), "aabadkabc");
    }
}
