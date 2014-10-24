/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
*/
// Tag: Array, Binary Search

// 思路是想象数组分为两段，然后判断mid在哪一段上,case1是较大数值子数组，case2是较小数值子数组；以题目例子，case1是4567，
// case2是012。注意此时的A[mid]不是逻辑上数值为接近最中间的那个，只是为了通过二分缩小查找范围，while loop循环两次之后肯定
// 每次都是case1。
// case1: /
// case2:  /
public class Solution {
    public int search(int[] A, int target) {
        int start = 0;
        int end = A.length - 1;
        int mid;

        while (start + 1 < end){
            mid = start + (end - start) / 2;
            // mid在case1,因为A[start]是大数子数组中最小的一个，但是也比case2的所有数值都大
            if (A[start] < A[mid]){
                if (A[start] <= target && target <= A[mid]){
                    end = mid;
                }else{
                    start = mid;
                }
            // 同理mid在case2
            }else{
                if (A[mid] <= target && target <= A[end]){
                    start = mid;
                }else{
                    end = mid;
                }
            }
        }

        if (A[start] == target){
            return start;
        }
        if (A[end] == target){
            return end;
        }
        return -1;
    }
}

// 自己写的方法，指定pivot为数组中最小值，然后将数组排序并求出index，最后将index位移pivot位，可以通过。
// 问题在于求数组中最小值时需要遍历数组，时间复杂度是O(n)。
/*
public class Solution {
    public int search(int[] A, int target) {
        int min = Integer.MAX_VALUE;
        int[] container = new int[1];

        for (int i = 0; i < A.length; i++){
            if (A[i] < min){
                min = A[i];
                container[0] = i;
            }
        }

        int pivot = container[0];
        Arrays.sort(A);

        int start = 0;
        int end = A.length - 1;
        int mid;

        while (start + 1 < end){
            mid = start + (end - start) / 2;
            if (A[mid] == target){
                end = mid;
            }else if (A[mid] < target){
                start = mid;
            }else{
                end = mid;
            }
        }

        if (A[end] == target){
            return (end + pivot) % A.length;
        }
        if (A[start] == target){
            return (start + pivot) % A.length;
        }
        return -1;
    }
}
*/
