/*
Given an integer array, adjust each integers so that the difference of every adjcent integers are not greater than
a given number target.

If the array before adjustment is A, the array after adjustment is B, you should minimize the sum of |A[i]-B[i]|

Example
Given [1,4,2,3] and target=1, one of the solutions is [2,3,2,3], the adjustment cost is 2 and it's minimal. Return 2.

Note
You can assume each number in the array is a positive integer and not greater than 100
*/
// Tag: Dynamic Programming

public class Solution {
    /**
     * @param A: An integer array.
     * @param target: An integer.
     */
    public int MinAdjustmentCost(ArrayList<Integer> A, int target) {
        // write your code here
        if (A == null || A.size() == 0){
            return 0;
        }

        int size = A.size();
        // dp[i][v]:将index = i的值调整为v的最小花费
        int[][] dp = new int[size][101];

        // 外层i代表有size个元素，内层j代表当前index可以将其调整为0-100
        for (int i = 0; i < size; i++){
            for (int j = 1; j <= 100; j++){
                // 最小花费先都初始化为最大整数，然后计算过程中取小
                dp[i][j] = Integer.MAX_VALUE;
                // 最初遍历第一个元素时，其调整的最小花费为0-100，两数之差的绝对值
                if (i == 0){
                    dp[i][j] = Math.abs(j - A.get(i));
                }else{
                    // 当前的i调整为j时看看i - 1调整为k的情况
                    for (int k = 1; k <= 100; k++){
                        // 调整后j和k的差值不能大于target
                        if (Math.abs(j - k) > target){
                            continue;
                        }
                        // 当前状态下i调整为j，i - 1调整为k的最小花费
                        int diff = Math.abs(j - A.get(i)) + dp[i - 1][k];
                        // 将更新的状态与老状态比较取小
                        dp[i][j] = Math.min(dp[i][j], diff);
                    }
                }
            }
        }

        // 结果返回dp[size - 1][x]，代表将最后一个元素调整为x（0-100）中最小的花费
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= 100; i++){
            res = Math.min(res, dp[size - 1][i]);
        }

        return res;
    }
}
