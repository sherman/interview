package com.leetcode.assorted_2025;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CountAndSay {
    public static String countAndSay(int n) {
        assert n > 0;

        var result = "1";
        for (var i = 2; i <= n; i++) {
            result = rle(result);
        }

        return result;
    }

    private static String rle(String data) {
        if (data.length() == 1) {
            return "1" + data.charAt(0);
        }

        var buffer = new StringBuilder();
        var current = data.charAt(0);
        var counter = 1;
        for (var i = 1; i < data.length(); i++) {
            if (data.charAt(i) == current) {
                counter++;
            } else {
                // char is changed
                buffer
                    .append(counter)
                    .append(current);
                current = data.charAt(i);
                counter = 1;
            }
        }

        buffer
            .append(counter)
            .append(current);

        return buffer.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals(countAndSay(1), "1");
        Assert.assertEquals(countAndSay(2), "11");
        Assert.assertEquals(countAndSay(3), "21");
        Assert.assertEquals(countAndSay(4), "1211");
    }
}
