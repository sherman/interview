package com.leetcode.assorted_2025;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DetermineIfTwoStringsAreClose {
    private static final Logger logger = LoggerFactory.getLogger(DetermineIfTwoStringsAreClose.class);

    public boolean closeStrings(String word1, String word2) {
        var cnt1 = index(word1);
        var cnt2 = index(word2);

        if (!cnt1.keySet().equals(cnt2.keySet())) {
            return false;
        }

        var entries1 = cnt1.entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();
        var entries2 = cnt2.entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();

        for (var i = 0; i < cnt1.size(); i++) {
            if (!entries1.get(i).getValue().equals(entries2.get(i).getValue())) {
                return false;
            }
        }

        return true;
    }

    private Map<Character, Integer> index(String word) {
        var res = new HashMap<Character, Integer>();
        for (var c : word.toCharArray()) {
            var num = res.getOrDefault(c, 0);
            res.put(c, num + 1);
        }
        return res;
    }

    @Test
    public void test() {
        Assert.assertEquals(closeStrings("uau", "ssx"), false);
        Assert.assertEquals(closeStrings("cabbba", "abbccc"), true);
        Assert.assertEquals(closeStrings("a", "aa"), false);
        Assert.assertEquals(closeStrings("abc", "bca"), true);
    }
}
