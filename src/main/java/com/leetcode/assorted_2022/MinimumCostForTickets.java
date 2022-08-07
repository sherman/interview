package com.leetcode.assorted_2022;

import java.util.HashSet;
import java.util.Set;
import joptsimple.internal.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MinimumCostForTickets {
    private static final Logger logger = LoggerFactory.getLogger(MinimumCostForTickets.class);

    int[] costs;
    Integer[] memo;
    Set<Integer> dayset;

    public int minCostTickets(int[] days, int[] costs) {
        this.costs = costs;
        memo = new Integer[21];
        dayset = new HashSet<>();
        for (int d : days) {
            dayset.add(d);
        }

        return dp(0, 1, 0);
    }

    public int dp(int prev, int i, int level) {
        //if ((i - prev) % 30 == 0) {
        draw(prev, i, level);
        //}
        if (i > 20) {
            return 0;
        }
        if (memo[i] != null) {
            return memo[i];
        }

        int ans;
        if (dayset.contains(i)) {
            ans = Math.min(
                dp(i, i + 1, level + 1) + costs[0],
                dp(i, i + 7, level + 1) + costs[1]);
            ans = Math.min(
                ans, dp(i, i + 30, level + 1) + costs[2]
            );
        } else {
            ans = dp(i, i + 1, level + 1);
        }

        memo[i] = ans;
        return ans;
    }

    private void draw(int prev, int param, int level) {
        logger.info("[{}] -> [{}]{}", prev, param, Strings.repeat('=', 2 * level));
    }

    @Test
    public void test() {
        Assert.assertEquals(11, minCostTickets(new int[] {1, 4, 6, 7, 8, 20}, new int[] {2, 7, 15}));
    }
}
