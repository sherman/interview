package com.leetcode.assorted_2024;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class NumberOf1Bits {
    public int hammingWeight(long n) {
        var counter = 0;
        var number = 1L;
        for (var i = 0; i < 32; i++) {
            if ((n & number) != 0L) {
                counter++;
            }
            number = number << 1;
        }
        return counter;
    }


    @Test
    public void test() {
        assertEquals(hammingWeight(100), 3);
        assertEquals(hammingWeight(4294967293L), 31);
    }
}
