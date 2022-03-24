package com.leetcode.assorted_2022;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class NumberOf1Bits {
    public int hammingWeight(int n) {
        var bits = 0;
        while (n != 0) {
            bits += n & 1;
            n = n >> 1;
        }
        return bits;
    }

    @Test
    public void test() {
        assertEquals(hammingWeight(100), 3);
    }
}
