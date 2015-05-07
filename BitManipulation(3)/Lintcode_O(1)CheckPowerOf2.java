/*
Using O(1) time to check whether an integer n is a power of 2.

Example
For n=4, return true;

For n=5, return false;

Challenge
O(1) time
*/
// Tag: Bit Manipulation

/*
分析：
一个数如果是2的幂，二进制表示的位数中只有1个数为1。而一个正数如果只有一位为1，那么将其减1后各个位与原数都是相反的，和原数做与操作结果为0。
*/
class Solution {
    /*
     * @param n: An integer
     * @return: True or false
     */
    public boolean checkPowerOf2(int n) {
        // write your code here
        if (n <= 0){
            return false;
        }
        // 这里将n放在前面运行时间能减半，不知道为何
        boolean result = (n & (n - 1)) == 0 ? true : false;

        return result;
    }
};
