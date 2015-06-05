/*
Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/
// Tag: Array

/*
分析：
跟1类似，注意每次留出边来给下一行计算，还要注意判断top = bottom的情况，比如n = 3时最里面的9
*/
public class Solution {
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        if (n <= 0){
            return result;
        }

        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        int counter = 1;

        while (top <= bottom){
            // 走到最里面心的时候
            if (top == bottom){
                result[top][top] = counter++;
                break;
            }
            // line top
            for (int i = left; i < right; i++){
                result[top][i] = counter++;
            }
            // line right
            for (int i = top; i < bottom; i++){
                result[i][right] = counter++;
            }
            // line bottom
            for (int i = right; i > left; i--){
                result[bottom][i] = counter++;
            }
            // line left
            for (int i = bottom; i > top; i--){
                result[i][left] = counter++;
            }

            left++;
            right--;
            top++;
            bottom--;
        }

        return result;
    }
}
