/*
Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array A = [1,1,1,2,2,3],

Your function should return length = 5, and A is now [1,1,2,2,3].
*/
// Tag: Array, Two Pointers

public class Solution {
    public int removeDuplicates(int[] A) {
        if (A == null || A.length == 0){
            return 0;
        }

        if (A.length <= 2){
            return A.length;
        }
        // 从初始fast就在第三位，slow在第二位
        // slow始终指向当前最后一个合法位
        int slow = 1;
        int fast = 2;
        // 由于A.length比fast能达到的最后一个index还大1，这样可以多循环一次，避免1122中最后一个2取不到
        while (fast < A.length){
            // 如果fast的值等于slow和slow前一个的值，fast接着往前找不相同的值
            if (A[fast] == A[slow] && A[fast] == A[slow - 1]){
                fast++;
            // 如果fast的值不等于slow或者slow前一个的值，拿出slow后面一个位置放fast此时指向的值，然后fast++
            // 比如在11222中初始s指向第二个1，f指向第一个2，此时首先slow指向第一个2，赋值到自身。然后f指向
            // 第二个2，这时s指向第一个2，将第二个2的值赋给第一个2，然后f指向第三个2。此时f=4，A.length = 5
            // 还能再循环一次，此时首先s指向第二个2，然后将第三个2的值赋给第二个2。
            }else{
                A[++slow] = A[fast++];
            }
        }
        return slow + 1;
    }
}
