/*
Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.
*/
// Tag: Math, Binary Search

/*
分析：
可以通过二分法优化算法，通过移位操作一直让除数翻倍，直到其大于被除数。除法运算的实质就是被除数 = 除数 * 商 + 余数，商实质上就是除数出现的次数。
通过循环我们每次让商达到小于被除数的最大值，同时累加除数出现的次数，从被除数中减去当前出现的最大值，最后返回商即可。
*/
public class Solution {
    public int divide(int dividend, int divisor) {
        boolean negative = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);

        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        long ans = 0;

        while (a >= b){
            // 注意使用long数据类型
            long shift = 0;
            while ((b << shift) <= a){
                shift++;
            }
            // b出现的肯定是2的倍数，直接用移位操作就能算出；而移0位的时候shift = 1，因此实际shift操作要-1
            ans += 1L << (shift - 1L);
            a -= b << (shift - 1L);
        }

        ans = negative ? -ans : ans;
        // 注意处理越界的情况，Integer的范围是-2147483648~2147483647
        if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE){
            return Integer.MAX_VALUE;
        }

        return (int)ans;
    }
}
