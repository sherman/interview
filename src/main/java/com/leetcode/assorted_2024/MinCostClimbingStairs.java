package com.leetcode.assorted_2024;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        var left = recursion(cost, 0, 0);
        var right = recursion(cost, 1, 0);
        return Math.min(left, right);
    }

    private static int recursion(int[] cost, int current, int sum) {
        if (current >= cost.length) {
            return sum;
        } else {
            var left = recursion(cost, current + 1, sum + cost[current]);
            var right = recursion(cost, current + 2, sum + cost[current]);
            return Math.min(left, right);
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(minCostClimbingStairs(new int[] {10, 15, 20}), 15);
        Assert.assertEquals(minCostClimbingStairs(new int[] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1}), 6);
    }

}
