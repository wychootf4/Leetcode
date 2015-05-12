/*
Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the
Hamming weight).

For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function
should return 3.
*/
// Tag: Bit Manipulation

public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        if (n == 0){
            return 0;
        }

        int count = 0;
        for(int i = 0; i < 32; i++){
            count += (n & 1);
            n = (n >> 1);
        }

        return count;
    }
}
