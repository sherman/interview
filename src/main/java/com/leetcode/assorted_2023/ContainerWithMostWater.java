package com.leetcode.assorted_2023;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        var l = 0;
        var r = height.length - 1;

        var res = 0;
        while (l < r) {
            var current = getSquare(l, r, height);
            res = Math.max(res, current);

            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }

        return res;
    }

    private static int getSquare(int x, int y, int[] height) {
        return (y - x) * Math.min(height[x], height[y]);
    }

    @Test
    public void test() {
        Assert.assertEquals(maxArea(new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7}), 49);
    }
}
