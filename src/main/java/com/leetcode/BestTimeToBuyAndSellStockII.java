package com.leetcode;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BestTimeToBuyAndSellStockII {
    public static int maxProfit(int[] prices) {
        boolean hasPosition = false;
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (hasPosition) {
                profit += prices[i] - prices[i - 1];
                if (prices[i] > prices[i + 1]) {
                    hasPosition = false;
                }
            } else {
                if (prices[i] < prices[i + 1]) {
                    hasPosition = true;
                }
            }
        }

        if (hasPosition) {
            profit += prices[prices.length - 1] - prices[prices.length - 2];
        }

        return profit;
    }

    @Test
    public void maxProfit() {
        Assert.assertEquals(maxProfit(new int[]{7, 1, 5, 3, 6, 4}), 7);
        Assert.assertEquals(maxProfit(new int[]{1, 2, 3, 4, 5}), 4);
        Assert.assertEquals(maxProfit(new int[]{7, 6, 4, 3, 1}), 0);
    }
}
