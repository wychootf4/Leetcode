/*
The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
*/
// Tag: Backtracking, Math

/*
分析：
一开始考虑用DFS遍历所有排列然后到第k个时输出，但是会超时。这里考虑用数学的方法来算，
1. 以某一数字开头的排列有(n-1)! 个。
例如： 123， 132， 以1开头的是 2！个
2. 所以第一位数字就可以用 （k-1） / (n-1)!  来确定 .这里K-1的原因是，序列号我们应从0开始计算，否则在边界时无法计算。
3. 第二位数字。假设前面取余后为m，则第二位数字是 第 m/(n-2)! 个未使用的数字。
4. 不断重复2，3，取余并且对(n-k)!进行除法，直至计算完毕
使用链表来记录未使用的数字，每用掉一个，将它从链表中移除即可。
*/
public class Solution {
    public String getPermutation(int n, int k) {
        //用链表记录还没用过的数字，这样就可以保持index是及时更新的
        LinkedList<Character> digit = new LinkedList<Character>();
        //初始化链表
        for (char i = '1'; i <= '0' + n; i++){
            digit.add(i);
        }

        //k初始从0开始，这样才能保证边界条件的运算成立
        k--;
        StringBuilder sb = new StringBuilder();

        // n!
        int sum = 1;
        for (int i = 1; i <= n; i++){
            sum *= i;
        }

        for (int i = n; i >= 1; i--){
            // n个数字以某个数字开头的有(n - 1)!种 = 2，即n! / n = (n - 1)!
            sum /= i;
            // 每个数字开头都有sum种，用k除sum可以求得是以第几个数字开头
            int digitIndex = k / sum;
            // 然后对k取模就知道是在该digitIndex下的第几个
            k %= sum;

            sb.append(digit.get(digitIndex));
            // remove出现过的字符
            digit.remove(digitIndex);
        }

        return sb.toString();
    }
}
