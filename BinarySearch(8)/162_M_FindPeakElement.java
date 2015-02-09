/*
A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

Note:
Your solution should be in logarithmic complexity.
*/
// Tag: Array, Binary Search

public class Solution {
    public int findPeakElement(int[] num) {
        if (num.length <= 1){
            return 0;
        }

        int start = 0;
        int end = num.length - 1;
        int mid;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (num[mid] > num[mid - 1] && num[mid] > num[mid + 1]){
                return mid;
            }else if (num[mid] > num[mid - 1]){
                start = mid;
            }else if (num[mid] > num[mid + 1]){
                end = mid;
            }else{
                start = mid;
            }
        }

        if (num[start] > num[end]){
            return start;
        }

        return end;
    }
}
