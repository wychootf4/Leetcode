/*
Given n distinct positive integers, integer k (k <= n) and a number target.

Find k numbers where sum is target. Calculate how many solutions there are?

Example
Given [1,2,3,4], k=2, target=5. There are 2 solutions:

[1,4] and [2,3], return 2.
*/
// Tag: Dynamic Programming

/*
分析：
state：f[i][j][t]代表从i个数中取出j个数组成的和为t的方案总数
*/
public class Solution {
    /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return an integer
     */
    public int kSum(int A[], int k, int target) {
        // write your code here
        int n = A.length;
        int[][][] dp = new int[n + 1][k + 1][target + 1];

        // 初始化从前i个数中取0个数组成0只有1种方案就是不取
        for (int i = 0; i <= n; i++){
            dp[i][0][0] = 1;
        }

        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= k && j <= i; j++){
                for (int t = 1; t <= target; t++){
                    // 初始化为0种方案
                    dp[i][j][t] = 0;
                    // A[i - 1]实际是第i个数，如果t大于第i个数能取
                    if (t >= A[i - 1]){
                        // 如果能取第i个数，方案总数为取或不取的总和
                        dp[i][j][t] = dp[i - 1][j - 1][t - A[i - 1]] + dp[i - 1][j][t];
                    }else{
                        // 不能取第i个数的话则方案数与取到第i - 1个数一样多
                        dp[i][j][t] = dp[i - 1][j][t];
                    }
                }
            }
        }

        return dp[n][k][target];
    }
}
