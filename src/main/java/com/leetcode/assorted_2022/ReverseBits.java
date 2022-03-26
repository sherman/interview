package com.leetcode.assorted_2022;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReverseBits {
    private static final Logger logger = LoggerFactory.getLogger(ReverseBits.class);

    public int reverseBits(int n) {
        var result = 0;
        for (int i = 0; i < 32; i++) {
            result = result << 1;
            result = result | (n & 1);
            n = n >> 1;

        }
        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(reverseBits(1000), 398458880);
    }
}
