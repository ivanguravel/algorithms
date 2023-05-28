package org.ivzh.greddy.algos;

import java.util.*;


// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
public class BestTimeToBuyAndSellStock {
    
    // kadane
    public int maxProfit(int[] prices) {
        int max = Integer.MIN_VALUE;
        int minprice = Integer.MAX_VALUE;

        for (int i : prices) {
            if (i < minprice) {
                minprice = i;
            }
            max = Math.max(i - minprice, max);
        }

        return max < 0 ? 0 : max;
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
