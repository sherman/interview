package com.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstUniqueCharacter {
    public static int firstUniqChar(String s) {
        char[] data = s.toCharArray();
        Map<Character, Integer> frequency = new LinkedHashMap<>();
        Map<Character, Integer> firstOccurance = new LinkedHashMap<>();
        for (int i = 0; i < data.length; i++) {
            char c = data[i];
            int v = frequency.computeIfAbsent(c, ignored -> 0);
            frequency.put(c, v + 1);

            firstOccurance.putIfAbsent(c, i);
        }

        for (char c : frequency.keySet()) {
            if (frequency.get(c) == 1) {
                return firstOccurance.get(c);
            }
        }

        return -1;
    }

    @Test
    public void firstUniqChar() {
        Assert.assertEquals(firstUniqChar("leetcode"), 0);
        Assert.assertEquals(firstUniqChar("loveleetcode"), 2);
        Assert.assertEquals(firstUniqChar("aabb"), -1);
        Assert.assertEquals(firstUniqChar("dddccdbba"), 8);
    }
}
