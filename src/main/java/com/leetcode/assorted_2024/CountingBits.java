package com.leetcode.assorted_2024;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CountingBits {
    public int[] countBits(int n) {
        var result = new int[n + 1];
        for (var i = 0; i <= n; i++) {
            result[i] = countOneBits(i);
        }
        return result;
    }

    private int countOneBits(int v) {
        var counter = 0;
        for (var i = 0; i < 32; i++) {
            var mask = 1 << i;
            if ((v & mask) != 0) {
                counter++;
            }
        }
        return counter;
    }

    @Test
    public void test() {
        Assert.assertEquals(countBits(2), new int[]{0, 1, 1});
    }
}
