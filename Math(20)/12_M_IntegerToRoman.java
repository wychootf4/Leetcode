
/*
Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.
*/
// Tag: Math, String

/*
Solution:贪心法每次取尽量大
 */

public class Solution {
    public String intToRoman(int num) {
        if (num <= 0) {
            return "";
        }

        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();

        int digit = 0;
        while (num > 0) {
            // 当前位有几倍
            int time = num / nums[digit];
            num -= nums[digit] * time;
            // 有几倍就向结果加上几倍
            while (time > 0) {
                res.append(symbols[digit]);
                time--;
            }
            digit++;
        }

        return res.toString();
    }
}