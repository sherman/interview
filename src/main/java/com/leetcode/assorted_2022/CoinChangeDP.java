package com.leetcode.assorted_2022;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CoinChangeDP {
    private static final Logger logger = LoggerFactory.getLogger(CoinChangeDP.class);

    public int coinChange(int[] coins, int amount) {
        var cache = new int[amount + 1];
        Arrays.fill(cache, Integer.MAX_VALUE);
        cache[0] = 0;

        for (var coin : coins) {
            for (var i = 0; i < cache.length; i++) {
                if (coin > amount) {
                    continue;
                }
                var value = 0;
                var left = i - coin;
                if (left >= 0) {
                    if (cache[left] == Integer.MAX_VALUE) {
                        value = cache[left];
                    } else {
                        value = 1 + cache[left];
                    }
                    cache[i] = Math.min(value, cache[i]);
                }
            }
        }

        return cache[amount] != Integer.MAX_VALUE ? cache[amount] : -1;
    }

    @Test
    public void test() {
        Assert.assertEquals(coinChange(new int[] {1, 2, 5}, 11), 3);
        Assert.assertEquals(coinChange(new int[] {2}, 3), -1);
        Assert.assertEquals(coinChange(new int[] {1}, 0), 0);
        Assert.assertEquals(coinChange(new int[] {3, 7, 405, 436}, 8839), 25);
        Assert.assertEquals(coinChange(new int[] {1}, 10000), 10000);
    }
}
