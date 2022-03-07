package com.leetcode.assorted_2022;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddString {
    private static final Logger logger = LoggerFactory.getLogger(AddString.class);

    public String addStrings(String num1, String num2) {
        if (num1.length() > num2.length()) {
            num2 = "0".repeat(num1.length() - num2.length()) + num2;
        }
        if (num2.length() > num1.length()) {
            num1 = "0".repeat(num2.length() - num1.length()) + num1;
        }

        var length = num1.length();
        var chars1 = num1.toCharArray();
        var chars2 = num2.toCharArray();
        var digit = 0;
        var res = new StringBuilder();
        for (int i = length - 1; i >= 0; i--) {
            var a = 0;
            var b = 0;
            if (i < chars1.length) {
                a = Integer.parseInt(String.valueOf(chars1[i]));
            }
            if (i < chars2.length) {
                b = Integer.parseInt(String.valueOf(chars2[i]));
            }

            if (digit + a + b >= 10) {
                res.append(digit + (a + b - 10));
                logger.info("[{}] [{}]", digit * 10 +  (a + b - 10));
                digit = 1;
            } else {
                res.append(digit + a + b);
                logger.info("[{}] [{}]", digit + a + b);
                digit = 0;
            }
        }

        if (digit > 0) {
            res.append("1");
        }
        return res.reverse().toString();
    }

    @Test
    public void test() {
        Assert.assertEquals(addStrings("1", "2"), "3");
        Assert.assertEquals(addStrings("0", "0"), "0");
        Assert.assertEquals(addStrings("5", "7"), "12");
        Assert.assertEquals(addStrings("10", "10"), "20");
        Assert.assertEquals(addStrings("13", "18"), "31");
        Assert.assertEquals(addStrings("1", "101"), "102");
        Assert.assertEquals(addStrings("999", "999"), "1998");
    }
}
