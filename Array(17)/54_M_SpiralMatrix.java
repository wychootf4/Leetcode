/*
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
*/
// Tag: Array

/*
分析：
用left，right，top，down记录方向，将变动的坐标作为循环条件然后避免碰撞即可。
*/
public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return result;
        }

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right){
            // line top
            for (int i = left; i <= right; i++){
                result.add(matrix[top][i]);
            }
            // line right
            for (int i = top + 1; i <= bottom; i++){
                result.add(matrix[i][right]);
            }
            // line bottom
            if (top != bottom){
                for (int i = right - 1; i >= left; i--){
                    result.add(matrix[bottom][i]);
                }
            }
            // line left
            if (left != right){
                for (int i = bottom - 1; i >= top + 1; i--){
                    result.add(matrix[i][left]);
                }
            }

            left++;
            right--;
            top++;
            bottom--;
        }

        return result;
    }
}
