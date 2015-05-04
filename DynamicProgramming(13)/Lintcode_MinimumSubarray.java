/*
Given an array of integers, find the subarray with smallest sum.

Return the sum of the subarray.

Example
For [1, -1, -2, 1], return -3

Note
The subarray should contain at least one integer.
*/
// Tag: Greedy, Array, Subarray

/*
分析：
将整个数组变为相反数，转化为求最大子数组的问题，然后最后的返回值再取反即可
*/
public class Solution {
    /**
     * @param nums: a list of integers
     * @return: A integer indicate the sum of minimum subarray
     */
    public int minSubArray(ArrayList<Integer> nums) {
        // write your code
        if (nums == null || nums.size() == 0){
            return 0;
        }

        int max = Integer.MIN_VALUE;
        int sum = 0;
        int minSum = 0;

        for (int i = 0; i < nums.size(); i++){
            sum += nums.get(i) * -1;
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
        }

        return max * (-1);
    }
}
