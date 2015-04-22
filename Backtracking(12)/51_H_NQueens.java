/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack
each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate
a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
*/
// Tag: Backtracking

/*
思路：
1. 建立一个arraylist存储每一行的皇后的列值。
例如：第一行皇后在第三列，第二行皇后在第五列，我们会记录3，5在arraylist中，依次这样推下去。
2. 进入DFS后，首先判断array是不是满，满的话说明8个皇后都放好了，创建一个解并返回。
3. 如果没有满，扫描当前行所有的位置，查找是不是能放一个皇后。如果可以放，继续DFS。不能放的话，就退出就好了。
*/
public class Solution {
    public List<String[]> solveNQueens(int n) {
        // 初始化result存全部的解法
        ArrayList<String[]> result = new ArrayList<String[]>();
        if (n <= 0){
            return result;
        }
        // 用search函数封装并查找所有解法
        search(n, new ArrayList<Integer>(), result);

        return result;
    }

    private void search(int n, ArrayList<Integer> cols, ArrayList<String[]> result){
        // 如果cols的大小等于n证明已经把n个皇后放进去了，将这个解画出来存入result
        if (cols.size() == n){
            result.add(draw(cols));
        }
        // 对于每一行，从第一列开始循环，如果可以放在第col列就将其加入cols的单解中，最后形成的cols是每行queen的纵坐标
        for (int col = 0; col < n; col++){
            if (!isValid(cols, col)){
                continue;
            }
            // 如果Q出现在第col列上合法则将其加入cols单解，在cols中的index就是行坐标
            cols.add(col);
            search(n, cols, result);
            cols.remove(cols.size() - 1);
        }
    }

    private String[] draw(ArrayList<Integer> cols){
        String[] chessboard = new String[cols.size()];
        // i代表行
        for (int i = 0; i < cols.size(); i++){
            chessboard[i] = "";
            // j代表列
            for (int j = 0; j < cols.size(); j++){
                // 如果第j列就是cols中记录的第i行应该放queen的列，画出Q
                if (j == cols.get(i)){
                    chessboard[i] += "Q";
                }else{
                    chessboard[i] += ".";
                }
            }
        }

        return chessboard;
    }

    private boolean isValid(ArrayList<Integer> cols, int col){
        // row是已放好Q的行的总数
        int row = cols.size();
        for (int i = 0; i < row; i++){
            // 如果第i行所对应的列已经放过queen了就不能再放
            if (cols.get(i) == col){
                return false;
            }
            // i - cols.get(i)是包含第i行Q点的那条右斜对角线的斜率，由于i是从0开始循环的
            // 所以row正好是待检查点的横坐标
            if (i - cols.get(i) == row - col){
                return false;
            }
            // 左斜对角线同理
            if (i + cols.get(i) == row + col){
                return false;
            }
        }

        return true;
    }
}
