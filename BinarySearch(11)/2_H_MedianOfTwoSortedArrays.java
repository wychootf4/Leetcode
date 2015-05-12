/*
There are two sorted arrays A and B of size m and n respectively.
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
*/
// Tag: Divide and Conquer, Array, Binary Search

public class Solution {
    public double findMedianSortedArrays(int A[], int B[]) {
        int len = A.length + B.length;
        if (len % 2 == 0){
            return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)) / 2.0;
        }else{
            return findKth(A, 0, B, 0, len / 2 + 1);
        }
    }

    public int findKth(int[] A, int startA, int[] B, int startB, int k){
        // 起始点的位置大于数组的长度，从另外一个数组取值，即已经把A或者B里的元素扔光了
        if (startA >= A.length){
            return B[startB + k - 1];
        }
        if (startB >= B.length){
            return A[startA + k - 1];
        }

        if (k == 1){
            return Math.min(A[startA], B[startB]);
        }
        // 如果当前数组长度已经不支持取到k/2个数了，则置成无穷大，在比较时候就不会被取到
        int keyA = startA + k / 2 - 1 < A.length ? A[startA + k / 2 - 1] : Integer.MAX_VALUE;
        int keyB = startB + k / 2 - 1 < B.length ? B[startB + k / 2 - 1] : Integer.MAX_VALUE;
        // 对于key小的数组，抛掉k/2个元素，因为他们肯定不会被取到
        if (keyA < keyB){
            return findKth(A, startA + k / 2, B, startB, k - k / 2); // 这里用k - k / 2处理k为奇数的情况
        }else{
            return findKth(A, startA, B, startB + k / 2, k - k / 2);
        }
    }
}
