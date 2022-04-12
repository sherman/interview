package com.leetcode.assorted_2022;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DistinctSubsequences {
    private static final Logger logger = LoggerFactory.getLogger(DistinctSubsequences.class);

    private Map<Call, Integer> cache = new HashMap<>();

    public int numDistinct(String s, String t) {
        cache.clear();

        var chars = s.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        var expected = t.chars().mapToObj(c -> (char) c).toList();
        return call(chars, expected, 0, 0);
    }

    private int call(List<Character> s, List<Character> t, int sIndex, int tIndex) {
        logger.info("[{}] [{}]", sIndex, tIndex);

        // base case
        if (sIndex == s.size()
            || tIndex == t.size()
            || s.size() - sIndex < t.size() - tIndex
        ) {
            return tIndex == t.size() ? 1 : 0;
        }


        var key = new Call(sIndex, tIndex);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        // search current char of T in next index of S
        int count = call(s, t, sIndex + 1, tIndex);

        // current char matched, look for next char
        if (s.get(sIndex) == t.get(tIndex)) {
            count += call(s, t, sIndex + 1, tIndex + 1);
        }

        cache.put(key, count);

        return count;
    }

    @Test
    public void test() {
        Assert.assertEquals(numDistinct("babgbag", "bag"), 5);
        Assert.assertEquals(numDistinct("rabbbit", "rabbit"), 3);
        Assert.assertEquals(numDistinct("bccbcdcabadabddbccaddcbabbaaacdba", "bccbbdc"), 172);
    }

    private record Call(int index1, int index2) {
    }
}
