package com.leetcode.assorted_2022;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindLowestStartingStair {
    private static final Logger logger = LoggerFactory.getLogger(FindLowestStartingStair.class);

    public static int findLowestStartingStair(List<Integer> jumps) {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < jumps.size(); i++) {
            sum = sum + jumps.get(i);
            min = Math.min(sum, min);
        }

        logger.info("[{}]", min);

        return Math.max(0, -1 * min) + 1;
    }

    @Test
    public void test() {
        Assert.assertEquals(
            findLowestStartingStair(List.of(-5, 5, 4, -2, 3, 1, -1, -6, -1, 0, 5)),
            6
        );

        Assert.assertEquals(
            findLowestStartingStair(List.of(1, -4, -2, 3)),
            6
        );

        Assert.assertEquals(
            findLowestStartingStair(List.of(0, 0, 0, 0, 0)),
            1
        );

        Assert.assertEquals(
            findLowestStartingStair(List.of(1)),
            1
        );

        Assert.assertEquals(
            findLowestStartingStair(List.of(-1)),
            2
        );

        Assert.assertEquals(
            findLowestStartingStair(List.of(-1, -2, -3, -4)),
            11
        );

        Assert.assertEquals(
            findLowestStartingStair(List.of(1, 3, 4)),
            1
        );
    }
}
