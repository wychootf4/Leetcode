/*
Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/
// Tag: Hash Table, Bit Manipulation

/*
由于数组中除了要求的数以为其他数都是有两个的，那么可以把所有数做异或操作，因为相同的数异或等于0，不同的数异或等于1。异或即为不进位加法。
异或有如下的特点：
a ^ b = c  ->  a ^ c = b, b ^ c = a
a ^ a = 0
a ^ 0 = a
*/
public class Solution {
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }

        int result = nums[0];
        for (int i = 1; i < nums.length; i++){
            result ^= nums[i];
        }

        return result;
    }
}
