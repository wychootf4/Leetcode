/*
Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.
*/
// Tag: Math

/*
思路：
最原始的算法就是先算出来阶乘的结果然后数末尾0的个数，但是如果input大了很容易溢出或者超时，由于做阶乘时候末尾想出现0则必须是出现2和5
的对，而2出现的次数远远超过5出现的次数，所以只需要统计5出现的次数即可。但是这里需要注意的是25，125...这些数会产生额外需要统计的5的个数
所以每次统计后将input除5，统计下一层额外的5的个数，直到商为0。
*/
public class Solution {
    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0){
            int k = n / 5;
            count += k;
            n = k;
        }

        return count;
    }
}
