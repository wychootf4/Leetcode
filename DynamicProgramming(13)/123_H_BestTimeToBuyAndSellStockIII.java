/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/
// Tag: Array, Dynamic Programming

public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0){
            return 0;
        }
        // left[i]或者right[i]代表从i划分时所产生的i左侧或者右侧的最大利润
        int[] left = new int[prices.length];
        int[] right = new int[prices.length];
        // 初始化从左边起，划在最左边的话其左边是空的，最大利润肯定为0
        left[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++){
            min = Math.min(min, prices[i]);
            // 左边DP，不取第i位或者取第i位，两者取大
            left[i] = Math.max(left[i - 1], prices[i] - min);
        }
        // 同理从右边起
        right[prices.length - 1] = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--){
            max = Math.max(max, prices[i]);
            right[i] = Math.max(right[i + 1], max - prices[i]);
        }
        // 最后从左到右再遍历一遍，以i点划分，将其左右的最大利润相加，随着遍历profit取最大值
        int profit = 0;
        for (int i = 0; i < prices.length; i++){
            profit = Math.max(profit, left[i] + right[i]);
        }

        return profit;
    }
}
