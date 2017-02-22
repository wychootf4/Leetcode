/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.
Example:
Given nums = [2, 7, 11, 15], target = 9,
Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
*/
// Tag: Array, Hash Table
// Company: Linkedin, Uber, Airbnb, Facebook, Amazon, Microsoft, Apple, Yahoo, Dropbox, Bloomberg, Yelp, Adobe

// Reference: http://blog.csdn.net/hustyangju/article/details/44459357


/*
思路1：用Brute Force整个数组循环遍历一遍, 时间复杂度是O(n^2), 空间复杂度是O(1).
*/
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        if (nums == null || nums.length < 2) {
            return result;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }

        return result;
    }
}

/*
思路2：用two pass的HashMap完成，先将所有的(value，index)pair存入HashMap，然后再查找一遍HashMap，时间复杂度和空间复杂度都是O(n).
*/
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        if (nums == null || nums.length < 2) {
            return result;
        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            // bug1: the complement must not be itself
            if (map.containsKey(target - nums[i]) && map.get(target - nums[i]) != i) {
                result[0] = i;
                result[1] = map.get(target - nums[i]);
                return result;
            }
        }

        return result;
    }
}

/*
思路2改进版：还可以用哈希表保存index和数的对应关系，然后遍历去求，这样时间复杂度是O(n)，空间复杂度是O(n)
*/
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        if (nums == null || nums.length < 2){
            return result;
        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++){
            // 如果map中已经有target - nums[i]这个key了说明找到了
            if (map.containsKey(target - nums[i])){
                // 找到target-nums[i]这个key，其早出现，index小，所以出现在前面
                result[0] = map.get(target - nums[i]);
                result[1] = i;
                return result;
            }
            // 如果没找到对应的key就将当前的pair放入哈希表
            map.put(nums[i], i);
        }

        return result;
    }
}

/*
思路3：如果返回的是两个数而不是index，可以先对数组进行排序，然后维护头尾指针，如果头尾和大于target要将尾向前移，如果头尾和小于target
要将头向后移。这样做的时间复杂度是O(nlogn),空间复杂度是O(1)
*/
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];

        if (nums == null || nums.length < 2){
            return result;
        }
        Arrays.sort(nums);

        int start = 0;
        int end = nums.length - 1;

        while (start < end){
            if (nums[start] + nums[end] > target){
                end--;
            }else if (nums[start] + nums[end] < target){
                start++;
            }else{
                result[0] = nums[start];
                result[1] = nums[end];
                return result;
            }
        }

        return result;
    }
}
