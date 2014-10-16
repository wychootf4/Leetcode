/*
Given two sorted integer arrays A and B, merge B into A as one sorted array.

Note:
You may assume that A has enough space (size that is greater or equal to m + n)
to hold additional elements from B. The number of elements initialized in A and B
are m and n respectively.
*/

public class Solution {
    public void merge(int A[], int m, int B[], int n) {
        int index = m + n; // index是新A数组中总的元素个数，新A数组下标从0到m + n - 1
        // 这样保证原A中元素肯定能先都被放进去
        while (m > 0 && n > 0){
            if (A[m - 1] > B[n - 1]){
                A[--index] = A[--m];
            }else{
                A[--index] = B[--n];
            }
        }
        // 原A的元素都放完但是B还有元素没放完，倒序都放进去
        while (n > 0){
            A[--index] = B[--n];
        }
    }
}
