package com.leetcode.uber;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AddDigits {
    public int addDigits(int num) {
        var finalResult = String.valueOf(num);
        var str = String.valueOf(num);
        while (str.length() > 1) {
            var result = 0;
            for (var i = 0; i < str.length(); i++) {
                result += Integer.parseInt(String.valueOf(str.charAt(i)));
            }
            finalResult = String.valueOf(result);
            str = finalResult;
        }
        return Integer.parseInt(finalResult);
    }

    @Test
    public void test() {
        Assert.assertEquals(addDigits(1), 1);
        Assert.assertEquals(addDigits(38), 2);
        Assert.assertEquals(addDigits(381), 3);
    }
}
