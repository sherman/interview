package com.leetcode.assorted_2024;

import com.google.common.collect.ImmutableList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindCommonCharacters {
    private static final Logger logger = LoggerFactory.getLogger(FindCommonCharacters.class);

    public List<String> commonChars(String[] words) {
        var alphabets = new HashMap<Character, BitSet>();
        for (var i = 0; i < 26; i++) {
            alphabets.put((char) ('a' + i), new BitSet(words.length));
        }
        for (var i = 0; i < words.length; i++) {
            for (var c : words[i].toCharArray()) {
                alphabets.get(c).set(i);
            }
        }

        return alphabets.entrySet().stream()
            .filter(e -> e.getValue().cardinality() == words.length)
            .map(Map.Entry::getKey)
            .map(String::valueOf)
            .toList();
    }

    @Test
    public void test() {
        Assert.assertEquals(ImmutableList.of("e", "l"), commonChars(new String[] {"bella", "label", "roller"}));
        Assert.assertEquals(ImmutableList.of(), commonChars(new String[] {"a", "b", "c"}));
        Assert.assertEquals(ImmutableList.of("a"), commonChars(new String[] {"aa", "baa", "caa", "bxaaa"}));

    }
}
