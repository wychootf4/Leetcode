/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/
// Tag: Dynamic Programming

/*
state: f[i][j]表示前i天进行j次交易,能够获得的最大收益 function: f[i][j] = max{f[x][j-1] + profit(x+1, i)}
￼answer: f[n][k]
intialize: f[i][0] = 0, f[0][i] = -MAXINT (i>0)
*/

public class Solution {
    public int maxProfit(int k, int[] prices) {
        if (k == 0){
            return 0;
        }
        // 对于prices.length个数，最多的买卖次数就是prices.length / 2,所以如果k大于此则直接遍历求上升区段即可。
        if (k >= prices.length / 2){
            int profit = 0;

            for (int i = 1; i < prices.length; i++){
                if (prices[i] > prices[i - 1]){
                    profit += prices[i] - prices[i - 1];
                }
            }

            return profit;
        }

        int[][] f = new int[2][prices.length + 1];   // local[i][j] 表示前i天，至多进行j次交易，第i天必须sell的最大获益,局部最优
        int[][] g = new int[2][prices.length + 1];  // global[i][j] 表示前i天，至多进行j次交易，第i天可以不sell的最大获益，全局

        f[0][0] = g[0][0] = 0;
        for (int i = 1; i <= k; i++) {
            f[0][i] = g[0][i] = 0;
        }

        for (int i = 1; i < prices.length; i++) {
            int increase = prices[i] - prices[i - 1];
            f[i % 2][0] = 0;
            for (int j = 1; j <= k; j++) {
                // g[(i - 1) % 2][j - 1] + increase: 在前i - 1天中交易j - 1次的全局最优 + 第i - 1天与第i天的差值
                // f[(i - 1) % 2][j] + increase: 取局部第i-1天进行j次交易，然后加上今天的差值（local[i-1][j]是第i-1天卖出的交易
                //，它加上diff后变成第i天卖出，并不会增加交易次数。无论diff是正还是负都要加上，否则就不满足local[i][j]必须在最后
                //一天卖出的条件了）
                f[i % 2][j] = Math.max(g[(i - 1) % 2][j - 1] + increase,
                                       f[(i - 1) % 2][j] + increase);
                // 过往全局最大值和当前局部最大值
                g[i % 2][j] = Math.max(g[(i - 1) % 2][j], f[i % 2][j]);
            }
        }
        return g[(prices.length - 1) % 2][k];
    }
}
