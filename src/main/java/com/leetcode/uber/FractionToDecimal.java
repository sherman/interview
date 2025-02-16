package com.leetcode.uber;

import java.util.LinkedHashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FractionToDecimal {
    private static final Logger logger = LoggerFactory.getLogger(FractionToDecimal.class);

    public String fractionToDecimal(int numerator, int denominator) {
        var remainder = numerator % denominator;
        if (remainder == 0) {
            return String.valueOf(numerator / denominator);
        }

        StringBuilder res = new StringBuilder();
        if (numerator > denominator) {
            res.append(numerator / denominator).append(".");
        } else {
            res.append("0.");
        }

        var mp = new LinkedHashMap<Integer, Integer>();
        var fraction = new StringBuilder();
        var repeatedIndex = -1;

        while ((remainder != 0) && (!mp.containsKey(remainder))) {
            // Store this remainder
            mp.put(remainder, res.length());

            // Multiply remainder with 10
            remainder = remainder * 10;

            // Append reminder / denominator to result
            int resPart = remainder / denominator;
            fraction.append(resPart);

            // Update remainder
            remainder = remainder % denominator;
            if (mp.containsKey(remainder)) {
                repeatedIndex = List.copyOf(mp.keySet()).indexOf(remainder);
            }
        }

        if (repeatedIndex >= 0) {
            var repeated = fraction.substring(repeatedIndex);
            res
                .append(fraction, 0, repeatedIndex)
                .append("(").append(repeated).append(")");
        } else {
            res.append(fraction);
        }

        return res.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals(fractionToDecimal(1, 6), "0.1(6)");
        Assert.assertEquals(fractionToDecimal(2, 1), "2");
        Assert.assertEquals(fractionToDecimal(1, 2), "0.5");
        Assert.assertEquals(fractionToDecimal(4, 333), "0.(012)");
    }

}
