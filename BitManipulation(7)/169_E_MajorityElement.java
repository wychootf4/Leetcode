/*
Given an array of size n, find the majority element. The majority element is the element that appears more than
⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.
*/
// Tag: Divide and Conquer, Array, Bit Manipulation

public class Solution {
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0){
            return -1;
        }
        // 循环更新candidates和candidates的count；如果count为0证明原来的candidate已经失效了，更新。如果当前元素与candidate相同
        // 则count+1，如果不同则count-1，剩到最后count>0的candidate就是majority element
        int candidates = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++){
            if (count == 0){
                candidates = nums[i];
                count++;
            }else if (candidates == nums[i]){
                count++;
            }else{
                count--;
            }
        }

        return candidates;
    }
}
