package com.leetcode.assorted_2024;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.ToIntFunction;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReorganizeString {
    public String reorganizeString(String s) {
        var chars = new HashMap<Character, Integer>();

        for (var i = 0; i < s.length(); i++) {
            var c = s.charAt(i);

            var freq = chars.getOrDefault(c, 0);
            chars.put(c, freq + 1);
        }

        Character prev = null;
        var result = new StringBuilder();
        for (var i = 0; i < s.length(); i++) {
            var c = nextChar(prev, chars);
            if (c == null) {
                return "";
            }
            result.append(c);
            prev = c;
        }

        return result.toString();
    }

    public Character nextChar(Character prev, Map<Character, Integer> freq) {
        var c = freq.entrySet().stream()
            .filter(e -> e.getKey() != prev)
            .filter(e -> e.getValue() > 0)
            .map(e -> e.getKey())
            .sorted(Comparator.comparingInt((ToIntFunction<Character>) freq::get).reversed())
            .findFirst();

        if (c.isPresent()) {
            var charKey = c.get();
            freq.compute(charKey, (k, charFreq) -> charFreq - 1);
            return charKey;
        } else {
            return null;
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(reorganizeString("aab"), "aba");
        Assert.assertEquals(reorganizeString("aaab"), "");
        Assert.assertEquals(reorganizeString("aabbcc"), "abcabc");
        Assert.assertEquals(reorganizeString("aabc"), "abac");
    }
}
