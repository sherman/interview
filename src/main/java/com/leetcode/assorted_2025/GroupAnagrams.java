package com.leetcode.assorted_2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Key, Value> bitsToCounts = new HashMap<>();
        for (var i = 0; i < strs.length; i++) {
            var str = strs[i];
            var bits = toKey(str);
            var index = bitsToCounts.getOrDefault(bits, new Value());
            index.indices.add(i);
            bitsToCounts.put(bits, index);
        }

        var result = new ArrayList<List<String>>();
        for (var entry : bitsToCounts.entrySet()) {
            var strings = new ArrayList<String>();
            for (var i : entry.getValue().indices) {
                strings.add(strs[i]);
            }
            result.add(strings);
        }

        return result;
    }

    private static Key toKey(String str) {
        var key = new Key();
        for (var c : str.toCharArray()) {
            var index = c - 'a';
            key.lettersFreq[index]++;
        }
        return key;
    }

    private static class Key {
        private final int[] lettersFreq = new int[26];

        public int hashCode() {
            return Arrays.hashCode(lettersFreq);
        }

        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (object instanceof Key other) {
                return Arrays.equals(lettersFreq, other.lettersFreq);
            }
            return false;
        }
    }

    private static class Value {
        private Set<Integer> indices = new HashSet<>();
    }

    @Test
    void test() {
        var actual = groupAnagrams(new String[] {"eat","tea","tan","ate","nat","bat"});
        Assertions.assertEquals(actual, List.of(List.of("tan", "nat"), List.of("bat"), List.of("eat", "tea", "ate")));
    }
}
