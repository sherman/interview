package com.leetcode.assorted_2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RepeatedDNASequences {
    private static final Logger logger = LoggerFactory.getLogger(RepeatedDNASequences.class);

    public List<String> findRepeatedDnaSequences(String s) {
        if (s.length() <= 10) {
            return List.of();
        }

        var hash = new HashMap<String, List<Integer>>();

        for (int i = 0; i < s.length() - 9; i++) {
            var seq = s.substring(i, i + 10);
            var list = hash.getOrDefault(seq, new ArrayList<>());
            list.add(i);
            hash.put(seq, list);
        }

        var result = new ArrayList<String>();
        for (var entry : hash.entrySet()) {
            var indexes = entry.getValue();
            if (indexes.size() > 1) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(
            findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"),
            List.of("AAAAACCCCC", "CCCCCAAAAA")
        );

        Assert.assertEquals(
            findRepeatedDnaSequences("AAAAAAAAAAAAA"),
            List.of("AAAAAAAAAA")
        );

        Assert.assertEquals(
            findRepeatedDnaSequences("AAAAAAAAAAA"),
            List.of("AAAAAAAAAA")
        );
    }
}
