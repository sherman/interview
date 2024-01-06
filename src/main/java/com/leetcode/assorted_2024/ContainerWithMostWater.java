package com.leetcode.assorted_2024;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        var l = 0;
        var r = height.length - 1;
        var max = Integer.MIN_VALUE;

        while (l < r) {
            var current = getSquare(height, l, r);
            max = Math.max(max, current);

            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }

        return max;
    }

    private static int getSquare(int[] height, int l, int r) {
        var actualHeight = Math.min(height[l], height[r]);
        return actualHeight * (r - l);
    }

    @Test
    public void test() {
        Assert.assertEquals(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}), 49);
    }
}
