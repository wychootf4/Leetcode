/*
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
Return the sum of the three integers. You may assume that each input would have exactly one solution.

For example, given array S = {-1 2 1 -4}, and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/
// Tag: Array, Two Pointers

/*
主要思路：与3sum基本一致，只需要在每次循环遍历时维护一个变量closest记录与target差的最小的sum值，如果当前sum与target的差小于原closest
值则更新
*/
public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3){
            return 0;
        }

        Arrays.sort(nums);
        // 与3 sum比较只需多加一个变量记录与target差距最小的sum即可
        int closest = Integer.MAX_VALUE / 2;

        for (int i = 0; i < nums.length - 2; i++){
            int start = i + 1;
            int end = nums.length - 1;

            while (start < end){
                int sum = nums[i] + nums[start] + nums[end];
                if (sum == target){
                    return sum;
                }else if (sum > target){
                    end--;
                }else{
                    start++;
                }

                closest = Math.abs(sum - target) < Math.abs(closest - target) ? sum : closest;
            }
        }

        return closest;
    }
}
