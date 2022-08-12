package com.leetcode.assorted_2022;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ClimbingStairsDP {
    public int climbStairs(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        var cache = new int[n + 1];
        cache[0] = 0;
        cache[1] = 1;
        cache[2] = 2;

        for (int i = 3; i <= n; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }

        return cache[n];
    }

    @Test
    public void test() {
        Assert.assertEquals(  climbStairs(4), 5);
    }
}
