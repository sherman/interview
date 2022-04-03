package com.leetcode.assorted_2022;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LongestIncreasingSubsequence {
    private static final Logger logger = LoggerFactory.getLogger(LongestIncreasingSubsequence.class);

    public int lengthOfLIS(int[] nums) {
        var sequences = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            sequences.put(i, 1);
        }

        for (int i = 1; i < nums.length; i++) {
            logger.info("== [{}] ==", i);
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    var current = sequences.get(i);
                    sequences.put(i, Math.max(current, 1 + sequences.get(j)));
                }
                logger.info("[{}] [{}] [{}]", i, j, sequences.get(i));
            }
        }

        logger.info("[{}]", sequences);

        return sequences.values().stream().mapToInt(v -> v).max().orElse(1);
    }

    @Test
    public void test() {
        Assert.assertEquals(lengthOfLIS(new int[] {10, 9, 2, 5, 3, 7, 101, 18}), 4);
        Assert.assertEquals(lengthOfLIS(new int[] {0, 1, 0, 3, 2, 3}), 4);
    }
}
