/*
Write a program to check whether a given number is an ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8
are ugly while 14 is not ugly since it includes another prime factor 7.

Note that 1 is typically treated as an ugly number.
*/
// Tag: Math

/*
思路：递归判断，如果数可以被2,3,5整除则继续判断，否则返回false
*/
public class Solution {
    public boolean isUgly(int num) {
      // bug1: missing edge case: num = 0
        if (num == 0) {
            return false;
        }
        // num = 1代表num的所有prime factor都是2,3,5，可以被整除
        if (num == 1) {
            return true;
        }

        if (num % 2 == 0) {
            num /= 2;
            return isUgly(num);
        }

        if (num % 3 == 0) {
            num /= 3;
            return isUgly(num);
        }

        if (num % 5 == 0) {
            num /= 5;
            return isUgly(num);
        }

        return false;
    }
}
