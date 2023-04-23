package com.leetcode.assorted_2023;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExcelSheetColumnTitle {
    private static final Logger logger = LoggerFactory.getLogger(ExcelSheetColumnTitle.class);
    public String convertToTitle(int columnNumber) {
        var builder = new StringBuilder();
        var n = columnNumber;
        while (n > 0) {
            n--;
            //logger.info("[{}]", 'A' + n % 26);
            builder.append((char) ('A' + n % 26));
            n = n / 26;

        }
        return builder.reverse().toString();
    }

    @Test
    public void test() {
        Assert.assertEquals(convertToTitle(1), "A");
        Assert.assertEquals(convertToTitle(28), "AB");
    }
}
