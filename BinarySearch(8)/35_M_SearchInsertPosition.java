/*
Given a sorted array and a target value, return the index if the target is found.
If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
*/
// Tag: Array, Binary Search

// 基本思路是如果能找到就返回，否则找到最后一个比target小的index，然后index + 1。如果target比所有的数都小
// 则返回0
public class Solution {
    public int searchInsert(int[] A, int target) {
        int start = 0;
        int end = A.length - 1;
        int mid;

        if (target < A[0]){
            return 0;
        }

        while (start + 1 < end){
            mid = start + (end - start) / 2;
            // 由于此时题目定义是无重复元素数组，所以如果找到直接返回mid即可
            if (A[mid] == target){
                return mid;
            }else if (A[mid] < target){
                start = mid;
            }else{
                end = mid;
            }
        }
        // 由于找的是最后一个比target小的数，所以先判断end,而且只判断A[] < target的情况
        if (A[end] == target){
            return end;
        }
        // target比end大则放到end后面
        if (A[end] < target){
            return end + 1;
        }
        if (A[start] == target){
            return start;
        }
        return start + 1;
    }
}
