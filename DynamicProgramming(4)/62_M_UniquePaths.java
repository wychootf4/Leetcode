/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Note: m and n will be at most 100.
*/
// Tag: Array, Dynamic Programming

/*
分析：
state：f(x,y)表示从起点走到f(x,y)的所有方案总数
function: 只能从左边或者上面走到当前位置，所以状态转移方程为
          f(x,y) = f(x - 1, y) + f(x, y - 1)
initialize:为了运算方便，经常将头一行和头一列都初始化。第一行和第一列初始肯定都为1，
            因为只能往右和往下走才能走到，只有一种走法
            f(0, 0) = 1, f(0, i) = 1, f(i, 0) = 1
answer: f(m - 1, n - 1)
*/

public class Solution {
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0){
            return 0;
        }

        int[][] sum = new int[m][n];

        for (int i = 0; i < m; i++){
            sum[i][0] = 1;
        }
        for (int i = 0; i < n; i++){
            sum[0][i] = 1;
        }

        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1];
            }
        }

        return sum[m - 1][n - 1];
    }
}
