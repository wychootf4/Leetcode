/*
Given a string and an offset, rotate string by offset. (rotate from left to right)

Example
Given "abcdefg"

for offset=0, return "abcdefg"

for offset=1, return "gabcdef"

for offset=2, return "fgabcde"

for offset=3, return "efgabcd"

...
*/

public class Solution {
    /*
    * param A: A string
    * param offset: Rotate string with offset.
    * return: Rotated string.
    */
    public char[] rotateString(char[] A, int offset) {
        // wirte your code here
        if (A == null || A.length == 0){
            return A;
        }
        // offset实际就是把整体形成一个环，然后向右移动offset位，原来最后的跑到前面来
        // 用三步翻转法处理前面的分部时，实际处理的是字段长度-offset长度，由于是数组，所以再-1，由于offset有可能超过字段
        // 长度，所以取模
        reverse(A, 0, A.length - offset % A.length - 1);
        reverse(A, A.length - offset % A.length, A.length - 1);
        reverse(A, 0, A.length - 1);

        return A;
    }

    public void reverse(char[] A, int start, int end){
        for (int i = start, j = end; i < j; i++, j--){
            char temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }
    }
};
