/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and
return its area.
*/
// Tag: Array, Hash Table, Stack, Dynamic Programming

import java.util.Stack;

/*
Solution: 维护一个栈,只要是递增就将相应index入栈,遇到短板则弹栈更新最大面积,直到栈顶元素比短板还要小.然后继续向前
遍历.注意栈中一直都是递增的
 */
public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length < 1) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        if (rows == 0 || cols == 0) {
            return 0;
        }
        int[][] height = new int[rows][cols];
        int maxArea = 0;

        // 计算高度
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 如果是第一行
                if (i == 0) {
                    height[i][j] = (matrix[i][j] == '1') ? 1 : 0;
                    // 否则为上一行累计的高度再计算
                } else {
                    height[i][j] += ((matrix[i][j] == '1') ? height[i - 1][j] + 1 : 0);
                }

                // 如果当前行已经检查完,则计算更新最大面积
                if (j == cols - 1) {
                    int curMax = getCurMax(height[i]);
                    maxArea = Math.max(maxArea, curMax);
                }
            }
        }
        return maxArea;
    }

    public int getCurMax(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();

        int index = 0;
        int max = 0;
        while (index <= height.length) {
            // 如果递增则持续入栈
            if (stack.empty() || (index < height.length && height[index] >= height[stack.peek()])) {
                stack.push(index);
                index++;
            } else {
                int h = height[stack.pop()];
                // 栈为空证明前面是递减的,h为前面的短板,宽度可以为最大
                int w = stack.empty() ? index : index - stack.peek() - 1;
                max = Math.max(max, h * w);
            }
        }
        return max;
    }
}