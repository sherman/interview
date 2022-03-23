package com.leetcode.assorted_2022;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CountingBitsDP {
    private static final Logger logger = LoggerFactory.getLogger(CountingBits.class);

    public int[] countBits(int n) {
        if (n == 0) {
            return new int[] {0};
        }

        if (n == 1) {
            return new int[] {0, 1};
        }

        if (n == 2) {
            return new int[] {0, 1, 1};
        }

        var result = new int[n + 1];
        result[0] = 0;
        result[1] = 1;

        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                result[i] = result[i / 2];
                logger.info("{}", result[i]);
            } else {
                result[i] = result[i - 1] + 1;
            }
        }

        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(countBits(2), new int[] {0, 1, 1});
        Assert.assertEquals(countBits(12), new int[] {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2});
    }
}
