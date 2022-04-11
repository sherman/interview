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
        var chars = s.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        var expected = t.chars().mapToObj(c -> (char) c).toList();
        return call(chars, expected, 0);
    }

    private int call(List<Character> s, List<Character> expected, int index) {
        if (s.equals(expected)) {
            return 1;
        }

        var sum = 0;
        for (var i = index; i < s.size(); i++) {
            var c = s.remove(i);
            var result = call(s, expected, i);
            s.add(i, c);
            sum += result;
        }

        return sum;
    }

    @Test
    public void test() {
        Assert.assertEquals(numDistinct("babgbag", "bag"), 5);
        Assert.assertEquals(numDistinct("rabbbit", "rabbit"), 3);
        //Assert.assertEquals(numDistinct("bccbcdcabadabddbccaddcbabbaaacdba", "bccbbdc"), 3);
    }

    private record Call(List<Character> prefix, int index) {
    }
}
