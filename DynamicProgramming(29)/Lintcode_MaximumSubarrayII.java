/*
Given an array of integers, find two non-overlapping subarrays which have the largest sum.

The number in each subarray should be contiguous.

Return the largest sum.

Example
For given [1, 3, -1, 2, -1, 2], the two subarrays are [1, 3] and [2, -1, 2] or [1, 3, -1, 2] and [2], they both
have the largest sum 7.

Note
The subarray should contain at least one number

Challenge
Can you do it in time complexity O(n) ?
*/
// Tag: Greedy, Array, Enumeration, Subarray, Forward-Backward Traversal

// 类似买卖股票3，从左向右和从右向左扫两次，然后最后再遍历一次找哪个划分点使得买卖的差值最大
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        // write your code
        if (nums == null || nums.size() == 0){
            return 0;
        }

        int size = nums.size();
        int[] left = new int[size];
        int[] right = new int[size];

        int max = Integer.MIN_VALUE;
        int sum = 0;
        int minSum = 0;
        for (int i = 0; i < size; i++){
            sum += nums.get(i);
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
            left[i] = max;
        }

        max = Integer.MIN_VALUE;
        sum = 0;
        minSum = 0;
        for (int i = size - 1; i >= 0; i--){
            sum += nums.get(i);
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
            right[i] = max;
        }

        max = Integer.MIN_VALUE;
        for (int i = 0; i < size - 1; i++){
            max = Math.max(max, left[i] + right[i + 1]);
        }

        return max;
    }
}
