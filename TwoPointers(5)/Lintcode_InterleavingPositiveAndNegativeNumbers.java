/*
Given an array with positive and negative integers. Re-range it to interleaving with positive and negative integers.

Example
Given [-1, -2, -3, 4, 5, 6], after re-range, it will be [-1, 5, -2, 4, -3, 6] or any other reasonable answer.

Note
You are not necessary to keep the original order of positive integers or negative integers.

Challenge
Do it in-place and without extra memory.
*/
// Tag: Two Pointers

class Solution {
    /**
     * @param A: An integer array.
     * @return: void
     */
    public void rerange(int[] A) {
        // write your code here
        if (A == null || A.length <= 2){
            return;
        }
        // 先数一下正负数哪个更多
        int posNum = 0;
        int negNum = 0;
        for (int i = 0; i < A.length; i++){
            if (A[i] > 0){
                posNum++;
            }else{
                negNum++;
            }
        }

        // 哪种数多就以其开头
        int negIndex = 0;
        int posIndex = 1;
        if (posNum > negNum){
            negIndex = 1;
            posIndex = 0;
        }

        while (negIndex < A.length && posIndex < A.length){
            // 如果negIndex上出现的数是负数就不用换，继续检查下一个应该是负数的地方，也就是
            // 步长+2的地方
            while (negIndex < A.length && A[negIndex] < 0){
                negIndex += 2;
            }
            // 同理检查posIndex
            while (posIndex < A.length && A[posIndex] > 0){
                posIndex += 2;
            }
            // 执行到这边证明目前negIndex和posIndex所指的元素都是不应该出现的位置，即negIndex
            // 指的是正数，posIndex指的是负数，将两个元素进行交换，然后继续向下检查即可
            if (negIndex < A.length && posIndex < A.length){
                swap(A, negIndex, posIndex);
            }
        }
   }

   private void swap(int[] A, int num1, int num2){
       int temp = A[num1];
       A[num1] = A[num2];
       A[num2] = temp;
   }
}
