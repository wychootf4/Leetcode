/*
There are two sorted arrays A and B of size m and n respectively.
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
*/
// Tag: Divide and Conquer, Array, Binary Search

/*
本题相当于从两个数组中找到第k大的元素，而k就是正好一半的位置，又需要分奇偶进行判断，然后递归解决.
由于题目要求用log时间解决，所以可以想到需要用二分法降低规模，要找到第k个大的元素，每次就要扔到k/2个元素。
每次递归中将A和B数组各取前k/2元素，比较A[k/2]和B[k/2]的大小，如果前者小则A中0到k/2的这一半必然不会被取到，
A的起始下标可以置为k/2+1，即把A中的前k/2个元素扔掉，反之亦然。
 */

public class Solution {
    public double findMedianSortedArrays(int A[], int B[]) {
        int len = A.length + B.length;
        // 根据两个数组中有奇数还是偶数个来决定
        if (len % 2 == 0){
            return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)) / 2.0;
        }else{
            return findKth(A, 0, B, 0, len / 2 + 1);
        }
    }

    public int findKth(int[] A, int startA, int[] B, int startB, int k){
        // 退出条件1：A的起始点位置已经大于数组长度，说明数组A的所有元素都被扔掉了，直接从B中取
        if (startA >= A.length){
            return B[startB + k - 1];
        }
        // 退出条件2：B的起始点位置已经大于数组长度，说明数组B的所有元素都被扔掉了，直接从A中取
        if (startB >= B.length){
            return A[startA + k - 1];
        }
        // 退出条件3：k = 1已经不能再分了
        if (k == 1){
            return Math.min(A[startA], B[startB]);
        }
        // 如果当前数组长度已经不支持取到k/2个数了，则置成无穷大，在比较时候就不会被取到，确定不能被扔
        int keyA = startA + k / 2 - 1 < A.length ? A[startA + k / 2 - 1] : Integer.MAX_VALUE;
        int keyB = startB + k / 2 - 1 < B.length ? B[startB + k / 2 - 1] : Integer.MAX_VALUE;
        // 对于key小的数组，抛掉其前k/2个元素，因为他们肯定不会被取到
        if (keyA < keyB){
            return findKth(A, startA + k / 2, B, startB, k - k / 2); // 这里用k - k / 2处理k为奇数的情况
        }else{
            return findKth(A, startA, B, startB + k / 2, k - k / 2);
        }
    }
}
