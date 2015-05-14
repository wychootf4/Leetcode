/*
Given an array and a value, remove all instances of that value in place and return the new length.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.
*/
// Tag: Array, Two Pointers

/*
分析：
利用两个指针，left从最左边起代表当前需要检查的位，right从最右边起记录新的长度
*/
public class Solution {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0){
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            // 如果当前检查位是要找的元素，拿right指向的元素代替它，同时right--，由于还要检查从right替换过来的元素，所以left在原处
            if (nums[left] == val){
                nums[left] = nums[right];
                right--;
            }else{
            // 当前位检查过不是要找的元素，继续检查下一位
                left++;
            }
        }
        // 由于数组的index从0开始，所以实际长度为right + 1
        return right + 1;
    }
}
