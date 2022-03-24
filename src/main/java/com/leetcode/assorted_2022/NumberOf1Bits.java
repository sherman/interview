package com.leetcode.assorted_2022;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class NumberOf1Bits {
    public int hammingWeight(long n) {
        var bits = 0;
        for (int i = 0; i < 32; i++, n = n >> 1) {
            bits += n & 1;
        }
        return bits;
    }

    @Test
    public void test() {
        assertEquals(hammingWeight(100), 3);
        assertEquals(hammingWeight(4294967293L), 31);
    }
}
