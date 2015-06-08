/*
Count the number of prime numbers less than a non-negative number, n.

Hint:
Let's start with a isPrime function. To determine if a number is prime, we need to check if it is not divisible
by any number less than n. The runtime complexity of isPrime function would be O(n) and hence counting the total
prime numbers up to n would be O(n2). Could we do better?
As we know the number must not be divisible by any number > n / 2, we can immediately cut the total iterations
half by dividing only up to n / 2. Could we still do better?
*/
// Tag: Hash Table, Math

// 用筛选法，先列出所有数，然后从2开始，依次剔除2的倍数，然后再剔除下一个可检查的最小数的倍数，依次类推，直到最小数的平方大于序列中的最大数。
public class Solution {
    public int countPrimes(int n) {
        if (n <= 2){
            return 0;
        }
        // 注意由于这里index的值对应的就是某个数是否包含在序列中，所以初始化长度要多1。
        boolean[] isPrime = new boolean[n];
        // 先默认都为true
        for (int i = 2; i < n; i++){
            isPrime[i] = true;
        }

        // 然后剔除所有不符合条件的
        for (int i = 2; i <= Math.sqrt(n - 1); i++){
            if (isPrime[i]){
                for (int j = i + i; j < n; j += i){
                    isPrime[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++){
            if (isPrime[i]){
                count++;
            }
        }

        return count;
    }
}
