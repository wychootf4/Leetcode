/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
*/
//Tag: Array, Backtracking

/*
分析：
用dfs的方法就可以，时间复杂度是O(mnk),m和n是数组的维度，k是单词的长度。空间复杂度是O(k).
*/
public class Solution {
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        if (board == null || rows == 0 || cols == 0 || rows * cols < word.length()){
            return false;
        }
        // 从数组每个元素出发dfs遍历
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (dfs(board, word, i, j, 0)){
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int index){
        // 如果index比单词长度长了说明已经能够递归到单词长度都可以
        int len = word.length();
        if (index >= len){
            return true;
        }
        // 判断有没有越界
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length){
            return false;
        }
        // 当前的元素不能匹配word的相应字符
        if (board[i][j] != word.charAt(index)){
            return false;
        }
        // 可以匹配，就暂时将此元素置为#，代表已经遍历过
        char temp = board[i][j];
        board[i][j] = '#';
        // 从当前元素向四个方向dfs遍历
        boolean result = dfs(board, word, i + 1, j, index + 1) ||
                         dfs(board, word, i, j + 1, index + 1) ||
                         dfs(board, word, i - 1, j, index + 1) ||
                         dfs(board, word, i, j - 1, index + 1);
        // backtracking
        board[i][j] = temp;
        return result;
    }
}
