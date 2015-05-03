/*
Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/
// Tag: Bit Manipulation

public class Solution {
    public int singleNumber(int[] A) {
        int result = 0;

        for (int i = 0; i < 32; i++) { // 循环检查result的每一位，因为int型有32位，所以循环32次
            int count = 0;
            for (int j = 0; j < A.length; j++) {
                /* >>是右移操作，二进制码整体右移指定位数，右移后最左边空出的位置填充符号位，正数补0。
                 * A[j] >> i 表示将当前待检查的result位挪到最后一位，然后和1做与操作，如果为1说明A[j]在这一位有值，随之count+1
                 * 内层for循环整个循环结束说明所有数的第i位已经都检查完了，则接下来对result中的第i位进行处理
                 */
                count += ((A[j] >> i) & 1); //统计当前result第i位上1出现的次数
            }
            // 将count即统计当前result第i位上1出现的次数模3，这样做是因为数组中一共有3n + 1个数，3n个数中一共有n组相同的数，
            // 每组中相同的数有三个，则每组三个数的和在第i位上要么是0，要么是3，都为3的倍数。
            // 因此这n组数相加，结果中第i位的和也必为3的倍数（3n），此时再把只出现一次的数加到result中，则此时的result在第i位上
            // 要么为3n+1，要么为3n。如果为1则说明这个只出现一次的数在第i位上有值。
            result |= ((count % 3) << i);   //取余后把result当前第i位的结果利用左移放回result的正确位置，左移后空位补0
        }
        return result;
    }
}

// 空间复杂度没有额外开销为O(1),时间复杂度为32 * O(N)
