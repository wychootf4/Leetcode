/*
Binary search is a famous question in algorithm. For a given sorted array (ascending order)
and a target number, find the first index of this number in O(log n) time complexity.
If the target number does not exist in the array, return -1.
Example If the array is [1, 2, 3, 3, 4, 5, 10], for given target 3, return 2.
*/
// 当要求用O(logn)完成时可以考虑binary search

// 用while loop显得比recursion好一些，另外recursion容易造成错误，适当情况再用
public class Solution{
    public int binarySearch(int[] nums, int target){
        if (nums.length == 0){
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;
        int mid;
        /*
         这里用(start < end)的话容易造成死循环，比如start = 1，end = 2，进入循环后mid = 1，而此时如果
         nums[mid] < target，则start = mid = 1，会进入死循环
         用(start + 1 < end)的话while循环结束后start和end是邻接点或是重合点
        */
        while (start + 1 < end){
            // 这样计算mid可以防止start + end大于int范围溢出
            mid = start + (end - start) / 2;
            if (nums[mid] == target){
                /*
                 为什么不直接return mid？因为题目要求返回数组中第一个等于target的元素，如果mid前还有等于target
                 的元素那么直接return的结果就是错的。将mid赋给end是为了保证while循环结束后在进行比较时如果mid前
                 有元素符合条件可以先返回其index, end = mid至少可以保证找到的index最末也是在mid位置
                */
                end = mid;
            } else if (nums[mid] < target) {
                // 为什么不用start = mid + 1?对于某些题不适用
                start = mid;
            } else if (nums[mid] > target) {
                end = mid;
            }
        }
        /*
         因为start和end是一个点或是邻接点，则如果target在数组中存在必然是两点之一
         由于是要找到第一次出现的index，所以先判断start
        */
        if (nums[start] == target){
            return start;
        }
        if (nums[end] == target){
            return end;
        }

        return -1;
    }
}

/*
拓展1：
Find the First Bad Version
The code base version is an integer and start from 0 to n. One day,
someone commit a bad version in the code case, so it caused itself and
the following versions are all failed in the unittests. You can determine
whether a version is bad by the following interface:
boolean isBadVersion(int version); Find the first bad version.
好的都是0，坏的都是1，找到出现的第一个1，用模板找出现的第一个1.
*/

/*
Find a peak
There is an array which we can assume the numbers in adjcent positions are different.
and A[0] < A[1] && A[A.length - 2] > A[A.length - 1]. We consider a position P is
a peak if A[P] > A[P-1] && A[P] > A[P+1]. Find a peak in this array.
第一句两个判断条件确保肯定有peak，然后mid与start比较，如果不大于start，则左侧必定有peak，因为是刚开始是
增函数，如果mid小于start则是在某点开始减了。该点就是peak
*/
