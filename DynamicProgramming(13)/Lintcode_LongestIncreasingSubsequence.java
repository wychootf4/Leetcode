/*
Given a sequence of integers, find the longest increasing subsequence (LIS).

You code should return the length of the LIS.

Example
For [5, 4, 1, 2, 3], the LIS  is [1, 2, 3], return 3

For [4, 2, 4, 5, 3, 7], the LIS is [4, 4, 5, 7], return 4

Challenge
Time complexity O(n^2) or O(nlogn)

Clarification
What's the definition of longest increasing subsequence?

    * The longest increasing subsequence problem is to find a subsequence of a given sequence in which the
    subsequence's elements are in sorted order, lowest to highest, and in which the subsequence is as long as possible. This subsequence is not necessarily contiguous, or unique.

    * https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
*/
// Tag: Dynamic Programming

/*
分析：
state：f[i]表示长度为i的串，以i位为LIS的末尾，所求得的LIS的长度
function：f[i] = max{f[j] + 1}, (j < i && a[j] <= a[i])
initialize：f[0...s.length() - 1] = 1;
result: max{f[0...s.length() - 1]}
*/
public class Solution {
    /**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0){
            return 0;
        }

        int[] sum = new int[nums.length];
        int max = 0;

        for (int i = 0; i < nums.length; i++){
            // 初始化每一位LIS为1，因为有可能该位是最小的，长度只能为1
            sum[i] = 1;
            // 检查当前位i的前面有没有值比i位的值小的，如果有取大，更新
            for (int j = 0; j < i; j++){
                if (nums[i] >= nums[j]){
                    sum[i] = Math.max(sum[i], sum[j] + 1);
                }
            }
            // 从各个位的结果中取最大的存为sum返回
            max = Math.max(max, sum[i]);
        }

        return max;
    }
}
