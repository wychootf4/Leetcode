/*
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front
before implementing one.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const
char * argument, please click the reload button  to reset your code definition.
*/
// Tag: Math, String

/*
Solution:这里设置了三个变量num,exp和dot标记当前位之前的状态,同时有三种情况需要考虑:
1.出现了e,则前面要有digit,不能有e,后面要有digit
2.出现了.那么是一个小数,前面不能有.和e
3.出现了+-,那么其必须是第一位,或者前一位是e,比如005047e+6
在这里注意只需要检查当前位前面的状态,比如一个小数点后面再有小数点,由于前一个dot已经被置为true,所以再检查时直接返回false
 */
public class Solution {
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        // cut leading spaces and tail spaces
        String sCut = s.trim();
        int len = sCut.length();

        boolean num = false;
        boolean exp = false;
        boolean dot = false;

        for (int i = 0; i < len; i++) {
            char c = sCut.charAt(i);
            //出现了e,case1
            if (c == 'e') {
                if (!num || exp) {
                    return false;
                }
                exp = true;
                // 如果e后面也是数会在那时将num改回true
                num = false;
                //如果出现的是数
            } else if (c >= '0' && c <= '9') {
                num = true;
                //如果出现的是. case2
            } else if (c == '.') {
                if (dot || exp) {
                    return false;
                }
                dot = true;
                // case3
            } else if (c == '+' || c== '-') {
                if (i == 0) {
                    continue;
                }
                if (sCut.charAt(i - 1) != 'e') {
                    return false;
                }
                // invalid character
            } else {
                return false;
            }
        }

        return num;
    }
}