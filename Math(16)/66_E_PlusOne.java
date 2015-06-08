/*
Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.
*/
// Tag: Array, Math

// 可以先算出来加1之后的数值，然后如果最前面一位需要进位的话先存上1，再依次存入新数组
public class Solution {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        int sum = 0;
        // 这里如果carry不大于0了证明前面都不会改变了，不用继续循环计算。
        for (int i = digits.length - 1; i >= 0 && carry > 0; i--){
            sum = digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;
        }
        // 如果最后一位不进位，直接返回更新过的digits数组
        if (carry == 0){
            return digits;
        }
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        for (int i = 1; i < result.length; i++){
            result[i] = digits[i - 1];
        }

        return result;
    }
}
