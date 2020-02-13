package com.leetcode;

public class BestTimeToSell {
    public static int maxProfit(int[] prices) {
        int position = 0;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (i == 0) {
                position = prices[i];
            } else {
                if (prices[i] < position) {
                    position = prices[i];
                } else {
                    profit = Math.max(profit, prices[i] - position);
                }
            }
        }

        return profit;
    }
}
