package com.leetcode.uber;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AddDigitsV2 {
    public int addDigits(int num) {
        int digitalRoot = 0;
        while (num > 0) {
            digitalRoot += num % 10;
            num = num / 10;

            if (num == 0 && digitalRoot > 9) {
                num = digitalRoot;
                digitalRoot = 0;
            }
        }
        return digitalRoot;
    }

    @Test
    public void test() {
        Assert.assertEquals(addDigits(1), 1);
        Assert.assertEquals(addDigits(38), 2);
        Assert.assertEquals(addDigits(381), 3);
    }
}
