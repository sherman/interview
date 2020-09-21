package com.leetcode.google;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LicenceReformat {
    public String licenseKeyFormatting(String data, int k) {
        StringBuilder result = new StringBuilder();
        char[] chars = data.toCharArray();
        int currentGroup = 0;

        for (int i = data.length() - 1; i >= 0; i--) {
            if (chars[i] == '-') {
                continue;
            }

            if (currentGroup == k) {
                result.append('-');
                currentGroup = 0;
            }

            currentGroup++;
            result.append(chars[i]);
        }

        return result
                .reverse()
                .toString()
                .toUpperCase();
    }

    @Test
    public void test() {
        Assert.assertEquals(licenseKeyFormatting("0123456789", 10), "0123456789");
        Assert.assertEquals(licenseKeyFormatting("r", 1), "R");
        Assert.assertEquals(licenseKeyFormatting("2", 2), "2");
        Assert.assertEquals(licenseKeyFormatting("5F3Z-2e-9-w", 4), "5F3Z-2E9W");
        Assert.assertEquals(licenseKeyFormatting("2-5g-3-J", 2), "2-5G-3J");
        Assert.assertEquals(licenseKeyFormatting("2-4A0r7-4k", 4), "24A0-R74K");
        Assert.assertEquals(licenseKeyFormatting("2-4A0r7-4k", 3), "24-A0R-74K");
    }
}
