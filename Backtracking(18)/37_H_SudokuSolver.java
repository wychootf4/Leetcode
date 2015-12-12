/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.
*/
// Tag: Backtracking, Hash Table

/*
Solution:利用DFS和backtracking解决,判定DFS的推出条件是y越界,继续解下一行;x越界,数独解完,退出.DFS的主体部分
把9个可能的值遍历一次,如果valid则继续DFS,否则回溯.
 */

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            return;
        }
        solve(board);
    }

    public boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 找到空的点处理
                if (board[i][j] != '.') {
                    continue;
                }
                // 从1到9依次填入
                for (int k = 1; k <= 9; k++) {
                    board[i][j] = (char)(k + '0');
                    // 如果填入后有效且能解决数独返回true
                    if (isValid(board, i, j) && solve(board)) {
                        return true;
                    }
                    // 否则backtracking清空当前点
                    board[i][j] = '.';
                }
                return false;
            }
        }
        return true;
    }

    public boolean isValid(char[][] board, int row, int col) {
        Set<Character> contained = new HashSet<Character>();
        //判断当前行
        for (int j = 0; j < 9; j++) {
            if (contained.contains(board[row][j])) {
                return false;
            }
            if (board[row][j] > '0' && board[row][j] <= '9') {
                contained.add(board[row][j]);
            }
        }

        contained.clear();
        //判断当前列
        for (int i = 0; i < 9; i++) {
            if (contained.contains(board[i][col])) {
                return false;
            }
            if (board[i][col] > '0' && board[i][col] <= '9') {
                contained.add(board[i][col]);
            }
        }

        contained.clear();
        //判断当前block
        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 3; n++) {
                // 通过当前点的坐标找到整个block中点在数独里的坐标
                int x = row / 3 * 3 + m;
                int y = col / 3 * 3 + n;
                if (contained.contains(board[x][y])) {
                    return false;
                }
                if (board[x][y] > '0' && board[x][y] <= '9') {
                    contained.add(board[x][y]);
                }
            }
        }
        return true;
    }
}