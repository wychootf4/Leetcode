/*
Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
*/
// Tag: Hash Table

// 用一个哈希表存储在检查某个行，列或者块时某个元素是否已经出现过。分别检查行，列和块，检查块的时候注意坐标横除竖模
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length < 9 || board[0].length < 9){
            return false;
        }

        Set<Character> set = new HashSet<Character>();
        int rows = board.length;
        int cols = board[0].length;

        // examine rows
        for (int i = 0; i < rows; i++){
            set.clear();
            for (int j = 0; j < cols; j++){
                char c = board[i][j];
                // 注意不要忘了前提是不能为空
                if (c != '.'){
                    if (set.contains(c)){
                        return false;
                    }
                    set.add(c);
                }
            }
        }

        // examine cols
        for (int i = 0; i < cols; i++){
            set.clear();
            for (int j = 0; j < rows; j++){
                // 注意坐标不要颠倒
                char c = board[j][i];
                if (c != '.'){
                    if (set.contains(c)){
                        return false;
                    }
                    set.add(c);
                }
            }
        }

        // examine block
        // 注意这里循环步长为3
        for (int i = 0; i < rows; i += 3){
            for (int j = 0; j < cols; j += 3){
                set.clear();
                for (int k = 0; k < 9; k++){
                    char c = board[i + k / 3][j + k % 3];
                    if (c != '.'){
                        if (set.contains(c)){
                            return false;
                        }
                    set.add(c);
                    }
                }
            }
        }

        return true;
    }
}
