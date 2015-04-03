/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which
minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
*/
// Tag: Array, Dynamic Programming

/*
分析：
state：f[x][y]从起点走到x，y的最短路径
function：f[x][y] = min(f[x - 1][y], f[x][y - 1]) + A[x][y]
initialize：f[0][0] = A[0][0], f[i][0] = sum(0,0 -> i,0), f[0][i] = sum(0,0 -> 0,i)
answer: f[m - 1][n - 1]
*/
public class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int[][] sums = new int[m][n];
        sums[0][0] = grid[0][0];

        for (int i = 1; i < m; i++){
            sums[i][0] = sums[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++){
            sums[0][i] = sums[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                sums[i][j] = Math.min(sums[i - 1][j], sums[i][j - 1]) + grid[i][j];
            }
        }

        return sums[m - 1][n - 1];
    }
}
