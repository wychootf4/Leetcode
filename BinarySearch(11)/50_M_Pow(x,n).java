/*
Implement pow(x, n).
*/
// Tag: Math, Binary Search

/*
分析：
刚开始用累乘的方式做，但是TLE，同时没有考虑到负数次幂的情况。然后考虑用二分法，判断是否为负数次幂，然后递归的方式二分去做，如果不够二分的部分
单独乘起来。
*/

public class Solution {
    public double myPow(double x, int n) {
        if (n == 0){
            return 1;
        }
        if (n == 1){
            return x;
        }

        boolean isNegative = false;
        if (n < 0){
            isNegative = true;
            n *= -1;
        }

        int main = n / 2;
        int remain = n - main * 2;
        double t1 = myPow(x, main);
        double t2 = myPow(x, remain);
        if (isNegative){
            return 1 / (t1 * t1 * t2);
        }else{
            return t1 * t1 * t2;
        }
    }
}
