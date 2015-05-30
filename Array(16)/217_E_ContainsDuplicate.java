/*
Given an array of integers, find if the array contains any duplicates. Your function should return true if any
value appears at least twice in the array, and it should return false if every element is distinct.
*/
// Tag: Array, Hash Table

public class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0){
            return false;
        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++){
            if (!map.containsKey(nums[i])){
                map.put(nums[i], 1);
            }else{
                return true;
            }
        }

        return false;
    }
}
