/*
Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

Have you thought about this?
Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!

If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.

Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse
of 1000000003 overflows. How should you handle such cases?

For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

Update (2014-11-10):
Test cases had been added to test the overflow behavior.
*/
// Tag: Math

// 如果越界了返回0，先用long来处理，最后再返回int值
public class Solution {
    public int reverse(int x) {
        long result = 0;
        // 每次先结果乘10就把之前的数整体左移一位了，然后空出最后一位，模10能得到当前的最后一位
        while (x != 0){
            result = result * 10 + x % 10;
            // 除10让整个数整体少掉最后一位
            x = x / 10;
        }

        if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE){
            return 0;
        }

        return (int)result;
    }
}
