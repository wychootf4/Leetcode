/*
Calculate the a^n % b where a, b and n are all 32bit integers.

Example
For 2^31 % 3 = 2

For 100^1000 % 1000 = 0

Challenge
O(logn)
*/

// 思路：用到的主要思路是a^n = (a^(n / 2)) ^ 2

/*实际上这题应该是suppose n > 0的。

我们利用 取模运算的乘法法则： http://baike.baidu.com/view/4887065.htm

(a * b) % p = (a % p * b % p) % p （3）

将 a^n % b 分解为 (a^(n/2) * a^(n/2) * (a)) %b = ((a^(n/2) * a^(n/2))%b * (a)%b) %b = ((a^(n/2)%b * a^(n/2)%b)%b * (a)%b) %b

实现如下：

注意2个base case: n = 0 n = 1都要特别处理。因为n = 1时，会分解出一个pow(a, b, 1)，这个会不断循环调用。
*/
class Solution {
    /*
     * @param a, b, n: 32bit integers
     * @return: An integer
     */
    public int fastPower(int a, int b, int n) {
        // write your code here
        if (n == 1){
            return a % b;
        }
        if (n == 0){
            return 1 % b;
        }

        long product = fastPower(a, b, n / 2);
        // %b防止溢出
        product = product * product % b;
        // 执行取余操作
        if (n % 2 == 1){
            product = product * a % b;
        }

        return (int)product;
    }
};
