/*
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.
*/
// Tag: Bit Manipulation

// Solution1: 九章上的递归做法，非常简洁。
public class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        if (m == n){
            return n;
        }
        if (n == m + 1){
            return m & n;
        }

        return rangeBitwiseAnd(m / 2, n / 2) << 1;
    }
}

// Solution2: 自己的想法，还没有实现。先取所有数的某一位做与运算，然后将运算结果或到左移i位后相应的位置
