package com.leetcode.assorted_2022;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CountingBits {
    private static final Logger logger = LoggerFactory.getLogger(CountingBits.class);

    public int[] countBits(int n) {
        var result = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            var bits = getNonZeroBits(i);
            result[i] = bits;
            logger.info("{}", bits);
        }

        return result;
    }

    private int getNonZeroBits(int n) {
        int nonZeroBits = 0;
        int nn = n ;
        while (nn != 0) {
            nonZeroBits += nn & 1;
            nn = nn >> 1;
        }
        return nonZeroBits;
    }

    @Test
    public void test() {
        Assert.assertEquals(countBits(2), new int[] {0, 1, 1});
    }
}
