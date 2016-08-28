/*
Given an integer, write a function to determine if it is a power of two.
*/
// Tag: Math, Bit Manipulation

// Solution1: 如果能被2整除就继续除，直到不能为止。如果是2的幂则最后剩下的商为1
public class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }

        while (n % 2 == 0) {
            n /= 2;
        }

        if (n == 1) {
            return true;
        } else {
            return false;
        }
    }
}

// Solution2: 利用位运算，某数为2的幂的本质是二进制格式有且仅有一位为1，则比其小1的数各位与该数二进制
// 编码正好相反
public class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        // bug1: wrong operator，位运算应在括号内
        return (n & (n - 1)) == 0 ? true : false;
    }
}
