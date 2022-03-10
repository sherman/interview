package com.leetcode.assorted_2022;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReverseInteger {
    private static final Logger logger = LoggerFactory.getLogger(ReverseInteger.class);

    public static int reverse(int x) {
        long rev = 0;
        while (x != 0) {
            rev = x % 10 + rev * 10;
            if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
                return 0;
            }
            x = x / 10;
        }
        return (int) rev;
    }

    @Test
    public void test() {
        Assert.assertEquals(reverse(120), 21);
        Assert.assertEquals(reverse(31), 13);
        Assert.assertEquals(reverse(1534236469), 0);
    }
}
