package com.leetcode.assorted_2024;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MinCostClimbingStairsDP {
    public int minCostClimbingStairs(int[] cost) {
        var table = new int[cost.length + 1];
        // cost of reach for step 0 or step 1 is zero (starting points)
        for (var i = 2; i < table.length; i++) {
            table[i] = Math.min(table[i - 2] + cost[i - 2], table[i - 1] + cost[i - 1]);
        }
        return table[table.length - 1];
    }

    @Test
    public void test() {
        Assert.assertEquals(minCostClimbingStairs(new int[] {10, 15, 20}), 15);
        Assert.assertEquals(minCostClimbingStairs(new int[] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1}), 6);
    }
    // 1, 3, 2, 5, 6, 10, 4
    // 0, 0, min(1 + 0), min(3 + 0)
}
