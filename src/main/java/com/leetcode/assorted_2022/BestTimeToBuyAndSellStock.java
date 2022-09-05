package com.leetcode.assorted_2022;

public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        var currentPrice = -1;
        var profit = 0;
        for (var i = 0; i < prices.length; i++) {
            if (currentPrice == -1) {
                currentPrice = prices[i];
            } else {
                if (prices[i] < currentPrice) {
                    currentPrice = prices[i];
                } else {
                    profit = Math.max(profit, prices[i] - currentPrice);
                }
            }
        }

        return profit;
    }
}
