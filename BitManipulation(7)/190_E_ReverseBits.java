/*
Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).

Follow up:
If this function is called many times, how would you optimize it?

Related problem: Reverse Integer
*/
// Tag: Bit Manipulation

/*
分析：
这里处理的是无符号的32位整型数据，用循环处理每一位，取得input的最后一位，将result左移一位给input的最后一位腾出位置，result和input最后一位
或操作，然后input右移一位以便在下一次循环时取得后面的bit。由于先处理的是最后面，这样处理的最后这一位就相应左移到了最前面，从而实现了bit的倒置。
*/

public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        if (n == 0){
            return 0;
        }

        int reversed = 0;
        for (int i = 0; i < 32; i++){
            reversed = (reversed << 1) | (n & 1);
            n = (n >> 1);
        }

        return reversed;
    }
}
