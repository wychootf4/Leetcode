/*
Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
*/
// Tag: Array, Binary Search

// Time complexity: O(logn)
public class Solution {
    public int[] searchRange(int[] A, int target) {
        int start, end, mid;
        int[] range = new int[2];
        // 得到lower bound
        start = 0;
        end = A.length - 1;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target){
                end = mid;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (A[start] == target) {
            range[0] = start;
        } else if (A[end] == target) {
            range[0] = end;
        } else {
            range[0] = range[1] = -1;
            return range;
        }
        // 得到upper bound
        start = 0;
        end = A.length - 1;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target) {
                start = mid;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (A[end] == target) {
            range[1] = end;
        } else if (A[start] == target) {
            range[1] = start;
        } else {
            range[0] = range[1] = -1;
            return range;
        }

        return range;
    }
}
