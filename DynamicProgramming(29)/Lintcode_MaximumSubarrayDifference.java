/*
Given an array with integers.

Find two non-overlapping subarrays A and B, which |SUM(A) - SUM(B)| is the largest.

Return the largest difference.

Example
For [1, 2, -3, 1], return 6

Note
The subarray should contain at least one number

Challenge
O(n) time and O(n) space.
*/
// Tag: Greedy, Enumeration, Array, Subarray, Forward-Backward Traversal

/*
分析：
寻找分割点，左边的最大子数组-右边的最小子数组，与右边的最大子数组-左边的最小子数组，两者取大；
然后枚举分割点，找出最大的
*/
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer indicate the value of maximum difference between two
     *          Subarrays
     */
    public int maxDiffSubArrays(ArrayList<Integer> nums) {
        // write your code
        if (nums == null || nums.size() == 0){
            return 0;
        }

        int size = nums.size();
        int[] leftMax = new int[size];
        int[] leftMin = new int[size];
        int[] rightMax = new int[size];
        int[] rightMin = new int[size];

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int sum1 = 0;
        int sum2 = 0;
        int minSum1 = 0;
        int minSum2 = 0;
        for (int i = 0; i < size; i++){
            sum1 += nums.get(i);
            max1 = Math.max(max1, sum1 - minSum1);
            minSum1 = Math.min(minSum1, sum1);
            leftMax[i] = max1;

            sum2 += nums.get(i) * -1;
            max2 = Math.max(max2, sum2 - minSum2);
            minSum2 = Math.min(minSum2, sum2);
            leftMin[i] = max2 * -1;
        }

        max1 = Integer.MIN_VALUE;
        max2 = Integer.MIN_VALUE;
        sum1 = 0;
        sum2 = 0;
        minSum1 = 0;
        minSum2 = 0;
        for (int i = size - 1; i >= 0; i--){
            sum1 += nums.get(i);
            max1 = Math.max(max1, sum1 - minSum1);
            minSum1 = Math.min(minSum1, sum1);
            rightMax[i] = max1;

            sum2 += nums.get(i) * -1;
            max2 = Math.max(max2, sum2 - minSum2);
            minSum2 = Math.min(minSum2, sum2);
            rightMin[i] = max2 * -1;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < size - 1; i++){
            max = Math.max(max, Math.max(leftMax[i] - rightMin[i + 1], rightMax[i + 1] - leftMin[i]));
        }

        return max;
    }
}
