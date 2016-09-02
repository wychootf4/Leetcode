/*
There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.
*/
// Company: Google
// Tag: Dynamic Programming

/*
思路：用dp解决，dp[i]表示有i个post时的总涂色方法。post为1和2时解法固定，而post为3时有两种可能性：
一是post1和post2使用同一种颜色，共有k种方法，而post3有k-1种，共k*(k-1);二是post1和post2颜色不同，
则一共是k*(k-1)*k，这两种情况相加为(k-1)*(k+k*k);k是dp[1]结果，k*k是dp[2]结果，即
dp[i] = (k - 1) * (dp[i - 1] + dp[i - 2]) (i > 2)
*/
public class Solution {
    public int numWays(int n, int k) {
        // 这里采用固定数组是为了简化代码，否则会out of bound
        int[] dp = {0, k, k * k, 0};

        if (n <= 2) {
            return dp[n];
        }
        // 由于初始dp[1]和dp[2]已经求得，因此要从dp[3]求到dp[n],这中间一共要进行n-3次操作
        for (int i = 2; i < n; i++) {
            dp[3] = (k - 1) * (dp[1] + dp[2]);
            dp[1] = dp[2];
            dp[2] = dp[3];
        }

        return dp[3];
    }
}
