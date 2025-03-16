package com.leetcode.assorted_2025;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) {
            return true;
        }

        if (n == 1 && flowerbed.length == 1 && flowerbed[0] == 0) {
            return true;
        }

        for (var i = 0; i < flowerbed.length; i++) {
            if (n == 0) {
                return true;
            }

            var current = flowerbed[i];
            if (current == 0) {
                // fist element
                if (i - 1 < 0 && flowerbed[i + 1] == 0) {
                    flowerbed[i] = 1;
                    n--;
                    continue;
                }

                // last element
                if (i + 1 >= flowerbed.length && flowerbed[i - 1] == 0) {
                    flowerbed[i] = 1;
                    n--;
                    continue;
                }

                // middle
                if (i > 0 && i < flowerbed.length - 1 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
                    flowerbed[i] = 1;
                    n--;
                    continue;
                }
            }
        }

        return n == 0;
    }

    @DataProvider(name = "testCases")
    public Object[][] flowerbedTestCases() {
        return new Object[][] {
            {new int[] {1, 0, 0, 0, 1}, 1, true},
            {new int[] {1, 0, 0, 0, 1}, 2, false},
            {new int[] {0, 0, 1, 0, 1}, 1, true},
            {new int[] {0}, 1, true},
            {new int[] {1}, 1, false},
            {new int[] {0, 0, 0, 0, 0}, 2, true},
            {new int[] {1, 0, 0, 0, 0, 1}, 1, true},
            {new int[] {1, 0, 0, 0, 0, 0, 1}, 2, true},
            {new int[] {0, 0, 0, 0, 0, 0}, 3, true},
            {new int[] {0, 0, 0, 0, 0, 0}, 4, false}
        };
    }

    @Test(dataProvider = "testCases")
    public void testCanPlaceFlowers(int[] flowerbed, int n, boolean expected) {
        Assert.assertEquals(canPlaceFlowers(flowerbed, n), expected);
    }
}
