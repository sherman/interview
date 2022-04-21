package com.leetcode.assorted_2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RemoveDuplicateLetters {
    private static final Logger logger = LoggerFactory.getLogger(RemoveDuplicateLetters.class);

    private static String call(String s, int index, Map<Character, List<Integer>> helpIndex, List<Integer> result) {
        if (result.size() == helpIndex.keySet().size()) {
            var sb = new StringBuilder();
            for (var m : result) {
                sb.append(s.charAt(m));
            }
            logger.info("[{}]", sb.toString());
            //logger.info("[{}]", result);
            return sb.toString();
        }

        String returnValue = null;
        for (var i = index; i < s.length(); i++) {
            var c = s.charAt(i);
            var indexes = helpIndex.get(c);
            if (indexes.size() == 1) {
                int current = indexes.get(0);
                if (result.size() == 0 || result.get(result.size() - 1) < current) {
                    result.add(current);
                    helpIndex.put(c, new ArrayList<>());
                    var candidate = call(s, index + 1, helpIndex, result);
                    if (candidate != null && (returnValue == null || candidate.compareTo(returnValue) < 0)) {
                        returnValue = candidate;
                    }
                    result.remove(result.size() - 1);
                    helpIndex.put(c, indexes);
                }
            } else {
                for (int k = 0; k < indexes.size(); k++) {
                    int current = indexes.get(k);
                    if (result.size() == 0 || result.get(result.size() - 1) < current) {
                        result.add(current);
                        helpIndex.put(c, new ArrayList<>());
                        //logger.info("[{}]", indexes);
                        var candidate = call(s, index + 1, helpIndex, result);
                        if (candidate != null && (returnValue == null || candidate.compareTo(returnValue) < 0)) {
                            returnValue = candidate;
                        }
                        result.remove(result.size() - 1);
                        helpIndex.put(c, indexes);
                    }
                }
            }
        }

        return returnValue;
    }

    public String removeDuplicateLetters(String s) {
        var uniqueIndexes = new HashMap<Character, List<Integer>>();
        for (int i = 0; i < s.length(); i++) {
            var x = s.charAt(i);
            var current = uniqueIndexes.computeIfAbsent(x, ignored -> new ArrayList<>());
            current.add(i);
        }

        return call(s, 0, uniqueIndexes, new ArrayList<>());
    }

    @Test
    public void test() {
        Assert.assertEquals(removeDuplicateLetters("bcabc"), "abc");
        Assert.assertEquals(removeDuplicateLetters("cbacdcbc"), "acdb");
        Assert.assertEquals(removeDuplicateLetters("ecbacba"), "eacb");
    }
}
