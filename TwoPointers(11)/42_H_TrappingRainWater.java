/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much
water it is able to trap after raining.

For example,
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
*/
// Tag: Array, Stack, Two Pointers

/*
分析：
对于数组中的每个元素，其自身是由宽度的，可以用两个数组分别记录某个元素左右两边分别能达到的最高的bar，然后该元素能蓄的水就是左右两边的bar中的
小者与该元素本身高度的差值。
*/
public class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0){
            return 0;
        }

        int water = 0;
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        left[0] = height[0];
        right[height.length - 1] = height[height.length - 1];

        for (int i = 1; i < height.length; i++){
            left[i] = Math.max(left[i - 1], height[i]);
        }
        for (int i = height.length - 2; i >= 0; i--){
            right[i] = Math.max(right[i + 1], height[i]);
        }
        // 这里可以省去下面的遍历过程，直接在计算right bar时候累加即可，如果当前元素比左右两边的bar都高差值就是负数，负数代表由于
        // 该元素太高所少蓄的水
        for (int i = 0; i < height.length; i++){
            int bar = Math.min(left[i], right[i]);
            if (bar > height[i]){
                water += bar - height[i];
            }
        }

        return water;
    }
}
