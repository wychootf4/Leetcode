/*
Given an integer array, find a subarray where the sum of numbers is zero. Your code should return the index of
the first number and the index of the last number.

Example
Given [-3, 1, 2, -3, 4], return [0, 2] or [1, 3].

Note
There is at least one subarray that it's sum equals to zero.
*/
// Tag: Subarray, Hash Table

/*
分析：
要求某个区间sum[i,j] = 0可以通过sum[j]-sum[i - 1] = 0求得，即sum[j] = sum[i - 1]。可以通过哈希表存入sum为key，index为value；
遍历数组，如果当前sum能在map中找到说明当前的index j之前已经有相应的i-1满足条件。
*/
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number
     *          and the index of the last number
     */
    public ArrayList<Integer> subarraySum(int[] nums) {
        // write your code here
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (nums == null || nums.length == 0){
            return result;
        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        // index-1的值是0，便于计算起始位置，否则没法减第一个元素的前面
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            // 如果map中已经存了当前sum的key证明找到了匹配的对
            if (map.containsKey(sum)){
                result.add(map.get(sum) + 1);
                result.add(i);
                break;
            }
            map.put(sum, i);
        }

        return result;
    }
}
