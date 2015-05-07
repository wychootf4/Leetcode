/*
Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.
*/
// Tag: Array, BinarySearch

// 如果三个临界点都相等，最坏情况就得每次都比较，然后时间复杂度从O(logn)降到O(n).
public class Solution {
    public int findMin(int[] num) {
        int start = 0;
        int end = num.length - 1;
        int mid;

        while (start + 1 < end){
            mid = start + (end - start) / 2;
            // [0,1,2,3,3]
            if (num[start] < num[end]){
                return num[start];
            }
            if (num[start] < num[mid]){
                start = mid;
            }else if (num[mid] < num[start]){
                end = mid;
            // [3,1,3,3]
            }else if (num[start] == num[mid]){
                start++;
            }
        }

        return Math.min(num[start], num[end]);
    }
}
