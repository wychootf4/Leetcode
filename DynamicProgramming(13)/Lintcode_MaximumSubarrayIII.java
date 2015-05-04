/*
Given an array of integers and a number k, find k non-overlapping subarrays which have the largest sum.

The number in each subarray should be contiguous.

Return the largest sum.

Example
Given [-1,4,-2,3,-2,3], k=2, return 8

Note
The subarray should contain at least one number
*/
// Tag: Dynamic Programming, Array, Subarray

public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: An integer denote to find k non-overlapping subarrays
     * @return: An integer denote the sum of max k non-overlapping subarrays
     */
    public int maxSubArray(ArrayList<Integer> nums, int k) {
        // write your code
        if (nums == null || nums.size() == 0 || nums.size() < k){
            return 0;
        }

        int len = nums.size();
        // f[i][k]:表示从前i个数中取k个子数组的和的最大值
        // f[i][k] = max{f[p][k - 1] + maxSubarray(p + 1, i), p为i- 1到k-1因为最少要分k-1组}
        // ,枚举p从前p个数中取出k-1个子数组，再加上从p+1到i的最大子数组。最后取得枚举的p中最大的
        int[][] f = new int[len + 1][k + 1];
        // 初始化从前i个数中取0个子数组的最大值就是0
        for (int i = 0; i <= len; i++){
            f[i][0] = 0;
        }

        // 循环j取从1到k个子数组
        for (int j = 1; j <= k; j++){
            // 循环i取从j到len，表示从前i个数中取，因为要取j个子数组，因此至少要从前j个数中取
            // ，循环从j开始
            for (int i = j; i <= len; i++){
                f[i][j] = Integer.MIN_VALUE;

                int currMax = 0;
                int max = Integer.MIN_VALUE;
                // 外层的两重循环表示从前i个数中取j个子数组，内层的p枚举前i个数的划分点在哪
                // 因为至少得有j个数才能取j个子数组，因此p枚举到j-1即可，对应nums中的第j
                for (int p = i - 1; p >= j - 1; p--){
                    // 维护currMax求当前从p+1到i的最大子数组
                    currMax = Math.max(nums.get(p), currMax + nums.get(p));
                    max = Math.max(max, currMax);
                    /*
                    控制条件：
                    1. f[i][j]:从前i个数中取j个子数组所取得的和的最大值
                    2. f[p][j - 1]: 从前p个数中取j-1个子数组的和的最大值，p为i-1到j-1
                    3. max：从p+1到i的最大子数组
                    */
                    if (f[i][j] < f[p][j - 1] + max){
                        f[i][j] = f[p][j - 1] + max;
                    }
                }
            }
        }

        return f[len][k];
    }
}
