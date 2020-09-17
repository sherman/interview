package com.leetcode.fb;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AddString {
    public String addStrings(String num1, String num2) {
        if (num1.length() < num2.length()) {
            num1 = align(num1, num2.length() - num1.length());
        }

        if (num1.length() > num2.length()) {
            num2 = align(num2, num1.length() - num2.length());
        }

        StringBuilder builder = new StringBuilder();
        int carryover = 0;
        for (int i = num1.length() - 1; i >= 0; i--) {
            int i1 = Integer.parseInt(String.valueOf(num1.charAt(i)));
            int i2 = Integer.parseInt(String.valueOf(num2.charAt(i)));

            int res = i1 + i2 + carryover;
            if (res > 9) {
                res = res - 10;
                carryover = 1;
            } else {
                carryover = 0;
            }

            builder.append(res);
        }

        if (carryover != 0) {
            builder.append(carryover);
        }

        return builder
                .reverse()
                .toString();
    }

    private String align(String src, int padding) {
        char[] str = new char[src.length() + padding];
        for (int i = 0; i < padding; i++) {
            str[i] = '0';
        }
        char[] srcChars = src.toCharArray();
        System.arraycopy(srcChars, 0, str, padding, srcChars.length);
        return new String(str);
    }

    @Test
    public void test() {
        Assert.assertEquals(addStrings("9", "9"), "18");
    }
}
