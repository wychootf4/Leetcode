/*
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array A = [1,1,2],

Your function should return length = 2, and A is now [1,2].
*/

public class Solution {
    public int removeDuplicates(int[] A) {
        if (A == null || A.length == 0){
            return 0;
        }
        // slow指向不重复的元素的最后一位
        int slow = 0;
        // fast向前扫描，如果fast找到第一个不等于slow的元素，将这个元素挪到slow + 1位置，同时
        // ++slow已经把slow向前移了一位，这样slow仍然指向最后一个不重复的元素
        for (int fast  = 0; fast < A.length; fast++){
            if (A[fast] != A[slow]){
                A[++slow] = A[fast];
            }
        }
        // slow指向最后一个不重复的元素，但是返回的是数组长度，则应该角标加1
        return slow + 1;
    }
}
