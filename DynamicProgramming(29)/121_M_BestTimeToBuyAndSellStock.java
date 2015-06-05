/*
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock),
design an algorithm to find the maximum profit.
*/
// Tag: Array, Dynamic Programming

public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0){
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int profit = 0;
        // 遍历每个点，先更新最小值，然后看当前点与最小值的差值，一直更新最大差值
        for (int i : prices){
            min = i < min ? i : min;
            profit = (i - min) > profit ? i - min : profit;
        }

        return profit;
    }
}
