/*
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
*/
// Tag: Array

// 首先用cc中方法，但是这种是O(m + n)空间复杂度
/*
主要思路：
*/
public class Solution {
    public void setZeroes(int[][] matrix) {
        boolean[] rows = new boolean[matrix.length];
        boolean[] cols = new boolean[matrix[0].length];
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j] == 0){
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if (rows[i] == true || cols[j] == true){
                    matrix[i][j] = 0;
                }
            }
        }
    }
}

// 利用两个变量标记首行和首列是否有零，然后遍历小数组，将为零的项标记到首行首列，然后再遍历一遍，
// 将首行首列中为零的行和列设为零，最后如果标记了首行或首列为true，将首行或首列设为零，这种方法
// 空间复杂度为O(1)
public class Solution {
    public void setZeroes(int[][] matrix) {
        boolean firstRowZero = false;
        boolean firstColZero = false;

        for (int i = 0; i < matrix[0].length; i++){
            if (matrix[0][i] == 0){
                firstRowZero = true;
                break;
            }
        }
        for (int j = 0; j < matrix.length; j++){
            if (matrix[j][0] == 0){
                firstColZero = true;
                break;
            }
        }

        for (int i = 1; i < matrix.length; i++){
            for (int j = 1; j < matrix[0].length; j++){
                if (matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++){
            for (int j = 1; j < matrix[0].length; j++){
                if (matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        if (firstRowZero){
            for (int i = 0; i < matrix[0].length; i++){
                matrix[0][i] = 0;
            }
        }
        if (firstColZero){
            for (int j = 0; j < matrix.length; j++){
                matrix[j][0] = 0;
            }
        }
    }
}
