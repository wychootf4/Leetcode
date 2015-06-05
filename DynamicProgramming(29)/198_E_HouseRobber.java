/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system
connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount
of money you can rob tonight without alerting the police.
*/
// Tag: Dynamic Programming

// Solution1: 用DP，走到当前位置要么不取当前位置，要么取
public class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }

        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        // i是从2开始，所以如果数组中只有一个元素就根本不会进入for循环，也就不会产生越界
        for (int i = 2; i <= nums.length; i++){
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }

        return dp[nums.length];
    }
}

// Solution2: 分别维护两个变量从奇数和偶数点出发，随着遍历更新当前的最大值。
public class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }

        int odd = 0;
        int even = 0;

        for (int i = 0; i < nums.length; i++){
            if (i % 2 == 0){
                even += nums[i];
                // 注意这里偶数点加上当前值后还得跟上一个奇数点比较，看看当前哪个大
                even = Math.max(odd, even);
            }else{
                odd += nums[i];
                odd = Math.max(odd, even);
            }
        }

        return Math.max(odd, even);
    }
}
