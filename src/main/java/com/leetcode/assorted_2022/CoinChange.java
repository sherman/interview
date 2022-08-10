package com.leetcode.assorted_2022;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CoinChange {
    private static final Logger logger = LoggerFactory.getLogger(CoinChange.class);
    private Map<Integer, Integer> cache = new HashMap<>();

    public int coinChange(int[] coins, int amount) {
        cache.clear();

        var res = dp(coins, amount);
        if (res == 10000) {
            return -1;
        } else {
            return res;
        }
    }

    public int dp(int[] coins, int amount) {
        var result = cache.get(amount);
        if (result != null) {
            return result;
        }

        //logger.info("[{}]", amount);
        if (amount == 0) {
            return 0;
        }

        var current = 10000;
        for (var coin : coins) {
            if (amount - coin >= 0) {
                var ans = dp(coins, amount - coin) + 1;
                if (current > ans) {
                    current = ans;
                }
            }
        }

        cache.put(amount, current);
        return current;
    }

    @Test
    public void test() {
        Assert.assertEquals(coinChange(new int[] {1, 2, 5}, 11), 3);
        Assert.assertEquals(coinChange(new int[] {2}, 3), -1);
        Assert.assertEquals(coinChange(new int[] {1}, 0), 0);
        Assert.assertEquals(coinChange(new int[] {3, 7, 405, 436}, 8839), 25);
        //Assert.assertEquals(coinChange(new int[] {1}, 10000), 10000);
    }
}
