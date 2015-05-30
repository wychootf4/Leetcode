/*
Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Related problem: Reverse Words in a String II
*/
// Tag: Array

// 这道题可以用三步翻转法，从分割处依次翻转前后两部分，然后再翻转
public class Solution {
    public void rotate(int[] nums, int k) {
        if (k == 0){
            return;
        }

        // 注意k超过数组长度的情况
        k = k % nums.length;
        reverse(nums, 0, nums.length - k - 1);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end){
        while (start < end){
            int temp = nums[end];
            nums[end] = nums[start];
            nums[start] = temp;
            start++;
            end--;
        }
    }
}
