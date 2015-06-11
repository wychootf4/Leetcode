/*
Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
*/
// Tag: BFS

/*
分析：
对二维数组整体index为0至i*j-1，从四个边开始做BFS，如果从边开始BFS时有O则标为B，前后左右BFS入栈，如果还有O还标B，注意index的边界
判断。最后遍历数组，将O置为X，将B置为O即可。
*/
public class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0){
            return;
        }

        int rows = board.length;
        int cols = board[0].length;
        // top and bottom side
        for (int i = 0; i < cols; i++){
            bfs(board, 0, i);
            bfs(board, rows - 1, i);
        }
        // left and right side
        for (int i = 0; i < rows; i++){
            bfs(board, i, 0);
            bfs(board, i, cols - 1);
        }

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (board[i][j] == 'O'){
                    board[i][j] = 'X';
                }else if (board[i][j] == 'B'){
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void bfs(char[][] board, int row, int col){
        int rows = board.length;
        int cols = board[0].length;

        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(row * cols + col);

        while (!q.isEmpty()){
            int index = q.poll();
            // out of bound
            if (index < 0 || index >= rows * cols){
                continue;
            }
            int i = index / cols;
            int j = index % cols;
            if (board[i][j] != 'O'){
                continue;
            }
            // 如果当前元素为O则置为B，然后前后左右BFS入栈
            board[i][j] = 'B';
            q.offer(index + 1);
            q.offer(index - 1);
            q.offer(index + cols);
            q.offer(index - cols);
        }
    }
}
