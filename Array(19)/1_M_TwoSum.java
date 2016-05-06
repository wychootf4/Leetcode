/*
Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1
must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/
// Tag: Array, Hash Table

// Reference: http://blog.csdn.net/hustyangju/article/details/44459357
/*
思路1：如果返回的是两个数而不是index，可以先对数组进行排序，然后维护头尾指针，如果头尾和大于target要将尾向前移，如果头尾和小于target
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

/*
思路2：还可以用哈希表保存index和数的对应关系，然后遍历去求，这样时间复杂度是O(n)，空间复杂度是O(n)
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
                // target-nums[i]这个key早出现，index小，所以出现在前面
                result[0] = map.get(target - nums[i]) + 1;
                result[1] = i + 1;
                return result;
            }
            // 如果没找到对应的key就将当前的pair放入哈希表
            map.put(nums[i], i);
        }

        return result;
    }
}
