package com.leetcode.assorted_2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RepeatedDNASequencesRollingHash {
    private static final Logger logger = LoggerFactory.getLogger(RepeatedDNASequencesRollingHash.class);

    private static final int STR_WIDTH = 10;
    private static final int MAX_SYMBOL_WIDTH = 3;
    private static final int MASK = (1 << (MAX_SYMBOL_WIDTH * (STR_WIDTH - 1))) - 1;

    private static final Map<Character, Integer> MAPPING = new HashMap<>();
    static {
        MAPPING.put('A', 1);
        MAPPING.put('C', 2);
        MAPPING.put('G', 3);
        MAPPING.put('T', 4);
    }

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> repeated = new ArrayList<>();
        Map<Integer, Integer> seen = new HashMap<>();
        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = (hash << 3) | MAPPING.get(s.charAt(i));

            if (i >= STR_WIDTH - 1) {
                if (seen.merge(hash, 1, Integer::sum) == 2) {
                    repeated.add(s.substring(i - STR_WIDTH + 1, i + 1));
                }

                hash = hash & MASK;
            }
        }

        return repeated;
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
