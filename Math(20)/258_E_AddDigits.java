/*
Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?

Hint:
1.A naive implementation of the above process is trivial. Could you come up with other methods?
2.What are all the possible results?
3.How do they occur, periodically or randomly?
4.You may find this Wikipedia article useful.
https://en.wikipedia.org/wiki/Digital_root
*/
// Company: Adobe Microsoft
// Tag: Math

// Solution1: 把数字转换为字符串然后每位相加，如果sum不小于10则递归地继续进行计算
public class Solution {
    public int addDigits(int num) {
        int sum = 0;

        String str = String.valueOf(num);
        for (int i = 0; i < str.length(); i++) {
            sum += (str.charAt(i) - '0');
        }

        if (sum < 10) {
            return sum;
        } else {
            return addDigits(sum);
        }
    }
}

// Solution2: Digital root就是当前数减去可以去掉的9的倍数后剩下的
public class Solution {
    public int addDigits(int num) {
        return num - 9 * ((num - 1) / 9);
    }
}
