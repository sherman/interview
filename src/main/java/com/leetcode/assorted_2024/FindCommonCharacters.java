package com.leetcode.assorted_2024;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindCommonCharacters {
    private static final Logger logger = LoggerFactory.getLogger(FindCommonCharacters.class);

    public List<String> commonChars(String[] words) {
        var alphabets = new HashMap<Character, int[]>();
        for (var i = 0; i < 26; i++) {
            alphabets.put((char) ('a' + i), new int[words.length]);
        }
        for (var i = 0; i < words.length; i++) {
            for (var c : words[i].toCharArray()) {
                alphabets.get(c)[i]++;
            }
        }

        return alphabets.entrySet().stream()
            .filter(e -> Arrays.stream(e.getValue()).allMatch(i -> i > 0))
            .flatMap((Function<Map.Entry<Character, int[]>, Stream<?>>) freq -> {
                var min = Integer.MAX_VALUE;
                for (var i = 0; i < freq.getValue().length; i++) {
                    min = Math.min(min, freq.getValue()[i]);
                }
                var letters = new ArrayList<Character>();
                for (var i = 0; i < min; i++) {
                    letters.add(freq.getKey());
                }
                return letters.stream();
            })
            .map(String::valueOf)
            .toList();
    }

    @Test
    public void test() {
        Assert.assertEquals(ImmutableList.of("e", "l", "l"), commonChars(new String[] {"bella", "label", "roller"}));
        Assert.assertEquals(ImmutableList.of(), commonChars(new String[] {"a", "b", "c"}));
        Assert.assertEquals(ImmutableList.of("a", "a"), commonChars(new String[] {"aa", "baa", "caa", "bxaaa"}));

    }
}
