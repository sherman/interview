package com.leetcode.assorted_2022;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Sqrt {
    public int getSqrt(int number) {
        var x = number;
        while (x * x > number) {
            x = (x + number / x) / 2;
        }
        return x;
    }

    @Test
    public void test() {
        Assert.assertEquals(getSqrt(8), 2);
        Assert.assertEquals(getSqrt(16), 4);
    }
}
