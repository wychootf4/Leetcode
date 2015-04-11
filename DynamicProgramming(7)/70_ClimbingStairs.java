/*
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
*/
// Tag: Dynamic Programming

/*
分析：
sums[i - 1]代表从之前的位置跳到第i个楼梯有多少种方案，初始化sums[0]跳到第一个楼梯只有一种，sums[1]跳到第二个楼梯共有两种，
后面状态转移方程，跳到第i个楼梯要么是从下面的一个，要么是从下面的两个跳上来的。所以跳到第i个楼梯的方案总数就是跳到第i - 1个楼梯的方案数
加上跳到第i - 2个楼梯的方案数。这里就可以用O（n）时间复杂度解决，另外一次最多只有三个数参与运算，用模3的数组或者三个临时变量都可以使空间
复杂度降为O（1）
*/

public class Solution {
    public int climbStairs(int n) {
        if (n < 2){
            return n;
        }

        int[] sums = new int[3];
        sums[0] = 1;
        sums[1] = 2;

        for (int i = 2; i < n; i++){
            sums[i % 3] = sums[(i - 1) % 3] + sums[(i - 2) % 3];
        }

        return sums[(n - 1) % 3];
    }
}
