package com.leetcode.assorted_2022;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CoinChange {
    private static final Logger logger = LoggerFactory.getLogger(CoinChange.class);

    public int coinChange(int[] coins, int amount) {
        var res = dp(coins, amount, 0);
        if (res == Integer.MAX_VALUE) {
            return -1;
        } else {
            return res;
        }
    }

    public int dp(int[] coins, int amount, int coinsNumber) {
        //logger.info("[{}]", amount);
        if (amount == 0) {
            return coinsNumber;
        }

        var current = Integer.MAX_VALUE;
        for (var coin : coins) {
            if (amount - coin >= 0) {
                var ans = dp(coins, amount - coin, coinsNumber + 1);
                if (current > ans) {
                    current = ans;
                }
            }
        }

        return current;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, coinChange(new int[] {1, 2, 5}, 11));
        Assert.assertEquals(-1, coinChange(new int[] {2}, 3));
        Assert.assertEquals(0, coinChange(new int[] {1}, 0));
    }
}
