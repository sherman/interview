package com.leetcode.assorted_2023;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }

        var maxProfit = 0;
        var left = 0;
        var right = 1;

        while (right < prices.length) {
            if (prices[left] > prices[right]) {
                left = right;
            } else {
                var profit = prices[right] - prices[left];
                maxProfit = Math.max(maxProfit, profit);
            }
            right++;
        }

        return maxProfit;
    }

    @Test
    public void test() {
        Assert.assertEquals(5, maxProfit(new int[] {7, 1, 5, 3, 6, 4}));
    }
}
