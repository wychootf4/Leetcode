/*
Write an efficient algorithm that searches for a value in an m x n matrix.
This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.

*/
// Tag: Array, Binary Search

// 主要思路是将二维数组的矩阵转化为一维数组
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int start = 0;
        int end = rows * cols - 1;
        int mid;
        // 因为有可能出现只有一行，一列或者只有一个元素的情况，所以这里循环条件改为start <= end
        while (start <= end){
            mid = start + (end - start) / 2;
            // 找到mid点，cols相当于每行元素的个数，除能得到行数，取模能得到列数
            int digit = matrix[mid / cols][mid % cols];
            if (digit == target){
                return true;
            }else if (digit < target){
                // 如果此时start和end不避开mid则可能死循环，比如只有1 x n的矩阵
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return false;
    }
}
