/*
Given an array of integers and an integer k, find out whether there there are two distinct indices i and j in
the array such that nums[i] = nums[j] and the difference between i and j is at most k.
*/

// 用一个哈希表存储某个值和其出现过的最新index，如果哈希表中已存在则比较index差是否小于等于k，否则更新该值在map中的index值
public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0){
            return false;
        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++){
            if (!map.containsKey(nums[i])){
                map.put(nums[i], i);
            }else if (i - map.get(nums[i]) <= k){
                return true;
            }else{
                map.put(nums[i], i);
            }
        }

        return false;
    }
}
