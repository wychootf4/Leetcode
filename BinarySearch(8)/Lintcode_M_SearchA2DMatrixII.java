/*
Write an efficient algorithm that searches for a value in an m x n matrix,
return the occurrence of it.

This matrix has the following properties:

    * Integers in each row are sorted from left to right.

    * Integers in each column are sorted from up to bottom.

    * No duplicate integers in each row or column.

Example
Consider the following matrix:

[

    [1, 3, 5, 7],

    [2, 4, 7, 8],

    [3, 5, 9, 10]

]

Given target = 3, return 2.

Challenge Expand
O(m+n) time and O(1) extra space
*/

// 思路应该是从左下角开始找，小于target向右走，大于target向上走

public class Solution {
    /**
    * @param matrix: A list of lists of integers
    * @param: A number you want to search in the matrix
    * @return: An integer indicate the occurrence of target in the given matrix
    */
    public int searchMatrix(ArrayList<ArrayList<Integer>> matrix, int target) {
        // write your code
        if (matrix == null || matrix.size() == 0){
            return 0;
        }

        int rows = matrix.size() - 1;
        int cols = 0;
        int counter = 0;

        while (rows >= 0 && cols <= matrix.get(0).size() - 1){
            if (matrix.get(rows).get(cols) > target){
                rows--;
            }else if (matrix.get(rows).get(cols) < target){
                cols++;
            }else{
                counter++;
                rows--;
                cols++;
            }
        }
        return counter;
    }
}
