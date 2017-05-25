/*
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical
lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together
with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.
*/
// Tag: Array, Two Pointers

/*
分析：
头尾两个指针，更新最大的面积。哪边高度更低就说明有改善的空间，移动相应方向的指针。
*/

public class Solution {
    public int maxArea(int[] height) {
        if (height == null || height.length < 2){
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right){
            max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]){
                left++;
            }else{
                right--;
            }
        }

        return max;
    }
}
