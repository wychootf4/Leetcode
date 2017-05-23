/*
Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask
yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible
to gather all the input requirements up front.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char *
argument, please click the reload button  to reset your code definition.

Requirements for atoi:
The function first discards as many whitespace characters as necessary until the first non-whitespace character
is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many
numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and
have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence
exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of
representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
*/
// Tag: Math, String
// Company: Amazon, Microsoft, Bloomberg, Uber

/*
先去掉前后的空位，如果去掉之后已经没有数字了直接返回0.然后检查每一位，如果不是数字直接跳出转换。首位判断是否为正负数，long来保存数据，
最后返回结果时转换为int型。每次循环时候判断结果是否已经越界，如果越界返回最接近的整形大小极值。
时间复杂度：O(n), 空间复杂度O(1)
这里的requirement只是一种情况，即去掉首尾空格后正负符号必须出现在第一位，而且如果含有任何非法字符就不再处理直接返回0.
还可以考虑处理去掉非法字符的情况。
*/


public class Solution {
    public int myAtoi(String str) {
        String newStr = str.trim();
        if (newStr.length() == 0){
            return 0;
        }

        long result = 0;
        int sign = 1;
        for (int i = 0; i < newStr.length(); i++){
            char c = newStr.charAt(i);
            if (i == 0 && c == '+'){
                continue;
            }else if (i == 0 && c == '-'){
                sign = -1;
                continue;
            }

            if (!(c >= '0' && c <= '9')){
                break;
            }

            result = result * 10 + (c - '0');
            // bug: forget to multiply sign
            if (result * sign <= Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }
            if (result * sign >= Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }
        }

        return (int)result * sign;
    }
}
