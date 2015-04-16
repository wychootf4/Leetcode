1.递归是把问题的规模逐步变小
动态规划是从小规模的问题计算到大规模的问题

2.排列的复杂度与n！有关
组合的复杂度与2^n有关
DP的复杂度与n^k有关

3.一共有两种动态规划，一种是top-down自顶向下，另一种是bottom-up自底向上，比较推荐自顶向下的方法
（1）top-down
// 状态定义
f[i][j]表示从0，0出发，到达i，j的最短路径是什么

// 初始化
f[0][0] = A[0][0];

// 递推求解
// 这里可以用滚动数组的思路对i取模，实现O（n）空间复杂度
for (int i = 1; i < n; i++){
    for (int j = 1; j <= i; j++){
        f[i][j] = Integer.MAX_VALUE;
        if (i - 1, j存在){
            f[i][j] = min(f[i][j], f[i - 1][j]);
        }
        if (i - 1, j - 1存在){
            f[i][j] = min(f[i - 1][j], f[i - 1][j - 1]);
        }
        f[i][j] = f[i][j] + A[i][j];
    }
}

// 答案
min(f[n - 1][0], f[n - 1][1], f[n - 1][2]...)

（2）bottom-up
A[][]

// 状态定义
f[i][j]表示从i，j出发走到最后一层的最小路径长度

// 初始化，终点先有值
for (int i = 0; i < n; i++){
    f[n - 1][i] = A[n - 1][i];
}

// 循环递推求解
for (int i = n - 2; i >= 0; i--){
    for (int j = 0; j <= i; j++){
        f[i][j] = Math.min(f[i + 1][j], f[i + 1][j + 1]) + A[i][j];
    }
}

// 求结果：起点

4.动态规划的4点要素
（1）状态State
灵感，创造力，存储小规模问题的结果
（2）方程Function
状态之间的联系，怎么通过小的状态，来计算大的状态
（3）初始化Initialization
最极限的小状态是什么，起点
（4）答案Answer
最大的那个状态是什么，终点

5.如何想到使用DP
（1）One of the following three
    a. Maximum/Minimum
    b. Yes/No
    c. Count all possible solutions
（2）Can not sort/swap

6.DP的四种类型
（1）Matrix DP
state: f[x][y]表示我从起点走到x,y
function: 研究走到x,y这个点之前的一步
initialize：起点
answer：终点
（2）Sequence DP
state: f[i]表示前i个位置/数字/字母，以第i个为...
function: f[i] = f[j]...j是i之前的一个位置
initialize: f[0]...
answer: f[n - 1]...
（3）Two Sequences DP
state: f[i][j]代表第一个sequence的前i个数字/字符 配上第二个sequence的前j个...
function: f[i][j] = 研究第i个和第j个的匹配关系
initialize: f[i][0]和f[0][i]
answer: f[s1.length()][s2.length()]
（4）Backpack DP
0-1背包问题
n个整数a[1..n],装m的背包
state: f[i][j] “前i”个数,取出一些能否组成和为j
function: f[i][j] = f[i-1][j - a[i]] or f[i-1][j]
intialize: f[X][0] = true; f[0][1..m] = false
answer: 能够使得f[n][X]最大的X(0<=X<=m)
在用背包问题解决的时候不一定能比搜索快，比如背包里装的物品呈2的指数级增长时
背包问题能够比搜索提高效率，例如：
1 2 3 能够组成 1 2 3 4 5 6
1 2 4 能够组成 1 2 3 4 5 6 7
用搜索时第一种情况如果要取得3可以是1+2或者3，用背包不管具体方案只要能取得3即可，没有重复运算

7.记忆化搜索本质上也是动态规划，实现动态规划的两种方式是记忆化搜索和循环
