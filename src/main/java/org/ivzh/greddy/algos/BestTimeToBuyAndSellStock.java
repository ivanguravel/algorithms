package org.ivzh.greddy.algos;

import java.util.*;


// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
public class BestTimeToBuyAndSellStock {
    
    // kadane
     public int maxProfit(int[] prices) {
        int price = Integer.MAX_VALUE;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < price)
                price = prices[i];
            else if (prices[i] - price > profit)
                profit = prices[i] - price;
        }
        if (profit < 0) {
            profit = 0;
        }
        return profit;
    }
    
    public int maxProfitTrivial(int[] prices) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i+1; j < prices.length; j++) {
                max = Math.max(prices[j] - prices[i], max);
                
            }
        }
        if (max < 0) {
            max = 0;
        }
        return max;
    }

}
