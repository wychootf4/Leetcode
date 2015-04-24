/*
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find
the area of largest rectangle in the histogram.

Given a histogram where width of each bar is 1, its height = [2,1,5,6,2,3].

The largest rectangle is found in the third and fourth block, which has area = 10 unit.(5 * 2)

For example,
Given height = [2,1,5,6,2,3],
return 10.
*/
// Tag: Array, Stack

/*
分析：
最初想到用三层循环，外层控制起始index，中层控制终止index，内层控制两个index间的高度，遍历找出区段中高度最低的，但是这样的时间复杂度是O(n^3)。
然后想到可以用一个变量随着外面两层遍历随时更新高度的最小值，这样可以将时间复杂度降到O(n^2)。现在利用一个递增栈可以优化将时间复杂度降到O(n),只用
一层循环，循环条件是每个直方的高度，而相应高度的左边界是由递增栈中的栈顶元素确定，因为栈顶元素最高，再往左已经过不去了。而右边界是随着循环继续向
右遍历，如果比当前的栈顶元素还高就入栈，证明以还可以向右走，直至遇到头一个比栈顶元素低的高度。此时栈顶元素的左右边界均已确定，将其出栈，并计算宽度，
继而计算此高度下能达到的最大面积，维护一个变量记录最大面积。
*/

public class Solution {
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0){
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        // 遍历各个高度，最后多出来一个负高度用于把最后剩余的元素都出栈
        for (int i = 0; i <= height.length; i++){
            // 如果遍历到最后一个高度，则其高度为-1
            int currentHeight = (i == height.length) ? -1 : height[i];
            // 当栈内元素不为空且当前高度小于栈顶元素时，证明栈顶元素所代表的高度已经不能往右走了，右边界得以确定
            while (!stack.isEmpty() && currentHeight <= height[stack.peek()]){
                // 栈顶元素的左右边界都已确定，出栈待计算
                int h = height[stack.pop()];
                // 栈顶元素所代表的高度的宽度计算方法是：如果此时栈顶元素出栈后栈为空证明左边界一直到头，而index从0开始，所以宽度就是i；
                // 如果栈不为空，其右边界为i，i并不能取，因为i所代表的高度比栈顶元素低。左边界为栈顶元素出栈后的新栈顶，且新栈顶也不能被
                // 取到，因为其肯定比老栈顶元素低（递增站的性质，如果高的话不能在栈顶元素的下面出现）。
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, h * w);
            }
            // 如果栈为空或者当前高度大于栈顶元素的高度，则将当前高度的index入栈
            stack.push(i);
        }

        return max;
    }
}
