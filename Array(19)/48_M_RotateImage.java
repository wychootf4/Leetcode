/*
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?
*/
// Tag: Array

/*
分析：
网上的答案不太好记忆，这里采用规定上下左右边界的办法，上和左为0点，从外到内每次循环处理一圈，往内圈走边长减2.
*/
public class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return;
        }

        int n = matrix.length;
        int top = 0;
        int down = n - 1;
        int left = 0;
        int right = n - 1;
        // 至少边长为2才需要翻转
        while (n > 1){
            // 由于每次翻转时候都是从四个角开始，一条边上有两个角，所以实际每条边就是翻转n - 1次。并且翻转时候按顺时针顺序处理。
            for (int i = 0; i < n - 1; i++){
                int temp = matrix[top][left + i];
                matrix[top][left + i] = matrix[down - i][left];
                matrix[down - i][left] = matrix[down][right - i];
                matrix[down][right - i] = matrix[top + i][right];
                matrix[top + i][right] = temp;
            }

            top++;
            down--;
            left++;
            right--;
            n -= 2;
        }
    }
}
