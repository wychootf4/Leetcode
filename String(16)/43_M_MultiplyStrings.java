/*
Given two numbers represented as strings, return multiplication of the numbers as a string.

Note: The numbers can be arbitrarily large and are non-negative.
*/
// Tag: Math, String

/*
分析：
新建一个数组其长度预留出足够的长度，两层循环分别计算各位的数值，新建的数组存储各位的数值，carry是进位。
*/
public class Solution {
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null){
            return null;
        }

        int len1 = num1.length();
        int len2 = num2.length();
        int len3 = len1 + len2;
        int[] num3 = new int[len3];
        int dig1, dig2, carry, product;
        // dig1是乘法时下面的数，低位都乘完了再处理更高的一位
        for (dig1 = len1 - 1; dig1 >= 0; dig1--){
            // 每次处理更高一位的时候将进位归零
            carry = 0;
            // dig2是乘法时上面的数
            for (dig2 = len2 - 1; dig2 >= 0; dig2--){
                // 两个乘数各取其中一位做乘法时包括从低位的进位，之前该位已经做好的结果，以及当前两个数的乘积
                product = carry + num3[dig1 + dig2 + 1] + Character.getNumericValue(num1.charAt(dig1)) *
                    Character.getNumericValue(num2.charAt(dig2));
                // 更新当前位最新的值
                num3[dig1 + dig2 + 1] = product % 10;
                // carry是马上要向更高位的进位，由于都是全局变量，下一次循环时候会直接参与运算即可
                carry = product / 10;
            }
            // 内层循环结束时会多--一次，所以此时下面的index是更高一位，所以正好保存前面一次运算的进位
            num3[dig1 + dig2 + 1] = carry;
        }

        StringBuilder sb = new StringBuilder();
        int dig3 = 0;
        // 把前面多余的零都排除，但是要注意的是最后一位除非两个乘数中有0，否则肯定不会是0。因此需要少检查一位，以避免出错。
        while (dig3 < len3 - 1 && num3[dig3] == 0){
            dig3++;
        }

        while (dig3 < len3){
            sb.append(num3[dig3++]);
        }

        return sb.toString();
    }
}
