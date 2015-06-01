/*
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the
number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will
stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1
are happy numbers.

Example: 19 is a happy number

1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1
*/
// Tag: Hash Table, Math

//用一个哈希表存储当前的值，如果有重复返回false，若没有则将新的值加入哈希表。如果最后能得到1就是happy number
public class Solution {
    public boolean isHappy(int n) {
        if (n == 0){
            return false;
        }

        Set<Integer> set = new HashSet<Integer>();
        while (n != 1){
            if (set.contains(n)){
                return false;
            }
            set.add(n);
            n = getNextHappy(n);
        }

        return true;
    }
    // 模10取当前最后一位，除10是整体消掉最后一位
    private int getNextHappy(int n){
        int sum = 0;
        while (n != 0){
            sum += (n % 10) * (n % 10);
            n /= 10;
        }

        return sum;
    }
}
