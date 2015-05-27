/*
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on
the row below.

For example, given the following triangle
[
    [2],
   [3,4],
  [6,5,7],
 [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the
triangle.
*/
// Tag: Array, Dynamic Programming

// Solution1:朴素解法是把所有路径走一遍，时间复杂度为O（2^n），n是总行数。每个节点面临两种选择。
void dfs1(int x, int y, int sum){
    if (x == n - 1){
        if (sum < best){
            best = sum;
        }
    }

    dfs1(x, y + 1, sum[x + 1][y]);
    dfs1(x + 1, y + 1, sum[x + 1][y + 1]);
}

初始条件：best = POSITIVE_INFINITY; dfs1(0, 0, sum[0][0]);
return best;

// Solution2: 时间复杂度仍然是O（2^n）
int dfs2(int x, int y){
    if (x == n - 1){
        return triangle[x][y];
    }

    return Math.min(dfs2(x + 1, y), dfs2(x + 1, y + 1)) + triangle[x][y];
}

return dfs2(0,0)
// 自底向上bottom up的动态规划，先算出最小一目了然的问题，然后倒退回初始点
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0){
            return 0;
        }

        int n = triangle.size();
        int[][] sum = new int[n][n];
        // 首先给最底下的一层赋值
        for (int i = 0; i < n; i++){
            sum[n - 1][i] = triangle.get(n - 1).get(i);
        }
        // 然后自底向上记录每个位置的最优解
        for (int i = n - 2; i >= 0; i--){
            for (int j = 0; j <= i; j++){
                sum[i][j] = Math.min(sum[i + 1][j], sum[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }

        return sum[0][0];
    }
}

// Solution2是可以继续优化的，所谓优化就是去掉重复运算，在每次进行计算前先判定，如果当前的坐标位已经算过了就跳过，也叫做记忆化搜索
// 由于这里可以把某个点的最优记录下来，所以每个点最多只用计算一次，所以时间复杂度就从O（2^n）降到了O（n^2）
// 这也是自顶向下的动态规划
public class Solution {
    private int n;
    private int[][] minSum;
    private List<List<Integer>> triangle;

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0){
            return 0;
        }

        // 将全局变量和已有的参数相关联
        this.n = triangle.size();
        this.triangle = triangle;
        this.minSum = new int[n][n];
        // 给minSum数组赋初值
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                minSum[i][j] = Integer.MAX_VALUE;
            }
        }
        // 自底向上进行搜索
        return search(0, 0);
    }

    private int search(int x, int y){
        // 如果已经到了最下层返回0
        if (x >= n){
            return 0;
        }
        // 如果minSum的当前点不为初值，证明之前已经访问过且已经求出最小，直接返回，不用进行重复计算
        if (minSum[x][y] != Integer.MAX_VALUE){
            return minSum[x][y];
        }

        minSum[x][y] = Math.min(search(x + 1, y), search(x + 1, y + 1)) + triangle.get(x).get(y);

        return minSum[x][y];
    }
}
