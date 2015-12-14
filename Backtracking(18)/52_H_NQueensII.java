/*
Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.
*/
//Tag: Backtracking

/*
Solution:还是dfs的思路,如果当前位置有效就放一个queen, 如果放满了就将解的个数加1
 */
public class Solution {
    public static int sum;
    public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }

        sum = 0;
        int[] usedColumns = new int[n];
        placeQueen(usedColumns, 0, n);
        return sum;
    }

    public void placeQueen(int[] usedColumns, int row, int n) {
        // 放满了
        if (row == n) {
            sum++;
            return;
        }
        // 遍历当前行的各个列
        for (int i = 0; i < n; i++) {
            // 如果第row行的第i列有效,放入queen
            if (isValid(usedColumns, row, i)) {
                usedColumns[row] = i;
                // 继续放下一行
                placeQueen(usedColumns, row + 1, n);
            }
        }
    }

    public boolean isValid(int[] usedColumns, int row, int col) {
        //遍历之前已经放好的行
        for (int i = 0; i < row; i++) {
            //如果某行已经在该列放入queen则无效
            if (usedColumns[i] == col) {
                return false;
            }
            //如果行坐标差与列坐标差相等,即相除斜率为1,证明对角线处有重复,无效
            if ((row - i) == Math.abs(col - usedColumns[i])) {
                return false;
            }
        }

        return true;
    }
}