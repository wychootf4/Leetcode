/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by
water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of
the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3
*/
// Tag: DFS, BFS

/*
分析：
遍历数组，如果为陆地就BFS操作进行merge，已经访问过的点置为V，是水或者访问过的点就跳过。
*/
public class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }

        int count = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == '1'){
                    count++;
                    merge(grid, i, j);
                }
            }
        }

        return count;
    }

    private void merge(char[][] grid, int i, int j){
        // out of bound
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length){
            return;
        }
        // water or visited
        if (grid[i][j] != '1'){
            return;
        }
        // set to visited
        grid[i][j] = 'V';
        merge(grid, i + 1, j);
        merge(grid, i, j + 1);
        merge(grid, i - 1, j);
        merge(grid, i, j - 1);
    }
}
