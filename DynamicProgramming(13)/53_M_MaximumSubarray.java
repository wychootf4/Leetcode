/*
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach,
which is more subtle.
*/
// Tag: Divide And Conquer, Array, Dynamic Programming

public class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        // max维护当前的最大值
        // sum记录加到当前位的所有数的和
        // minSum记录从开头到某位的所有区段中，和最小的区段
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int minSum = 0;

        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            // 更新max，当前位的所有数之和减去前面的最小和，得到当前的最大和区段
            max = Math.max(max, sum - minSum);
            // 更新最小区段
            minSum = Math.min(minSum, sum);
        }

        return max;
    }
}
