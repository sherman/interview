package com.leetcode;

public class AddStrings {
    public static String addStrings(String num1, String num2) {
        int transfer = 0;

        if (num1.length() > num2.length()) {
            num2 = padZero(num2, num1.length() - num2.length());
        } else if (num1.length() < num2.length()) {
            num1 = padZero(num1, num2.length() - num1.length());
        }

        StringBuilder builder = new StringBuilder();
        for (int i = num1.length() - 1; i >= 0; i--) {
            int digit1 = getDigit(num1, i);
            int digit2 = getDigit(num2, i);

            int result = digit1 + digit2 + transfer;
            if (result > 9) {
                result = result - 10;
                transfer = 1;
            } else {
                transfer = 0;
            }

            builder.append(result);
        }

        if (transfer > 0) {
            builder.append(1);
        }

        return builder.reverse().toString();
    }

    private static String padZero(String num, int zeros) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < zeros; i++) {
            b.append("0");
        }
        b.append(num);
        return b.toString();
    }

    private static int getDigit(String num, int index) {
        if (index >= num.length()) {
            return 0;
        } else {
            return Integer.parseInt(String.valueOf(num.charAt(index)));
        }
    }
}
