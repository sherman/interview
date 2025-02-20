package com.leetcode.assorted_2025;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CountingBits {
    public int[] countBits(int n) {
        var bits = new int[n + 1];
        var i = 0;
        while (i <= n) {
            var counter = 0;
            for (var j = 0; j < 32; j++) {
                var mask = 1 << j;
                if ((i & mask) != 0) {
                    counter++;
                }
            }
            bits[i] = counter;
            i++;
        }
        return bits;
    }

    @Test
    public void test() {
        Assert.assertEquals(countBits(2), new int[] {0, 1, 1});
    }
}
