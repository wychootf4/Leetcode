/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie,
buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions
at the same time (ie, you must sell the stock before you buy again).
*/
// Tag: Array, Greedy

// 找到所有上升的区段，加到一起就是最大利润
public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0){
            return 0;
        }

        int profit = 0;

        for (int i = 0; i < prices.length - 1; i++){
            int diff = prices[i + 1] - prices[i];

            if (diff > 0){
                profit += diff;
            }
        }

        return profit;
    }
}
