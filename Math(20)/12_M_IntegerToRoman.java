
/*
Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.
*/
// Tag: Math, String

/*
Solution1:贪心法每次取尽量大，从nums中的第一档位开始依次做除法，每个档位能取到几次就把对应的字典中的罗马数字加入结果几次，直到
input为0为止。
时间复杂度: O(n^2), 空间复杂度O(n).
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

/*
Solution1.1: 可以优化到时间复杂度为O(n),空间复杂度为O(n). 变为while loop检查nums中每一位，input比当前位小时候再开始
处理nums中的下一位。
 */
public class Solution {
    public String intToRoman(int num) {
        if (num <= 0) {
            return "";
        }

        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();
        int i = 0;

        while (i < nums.length) {
            if (num >= nums[i]) {
                res.append(symbols[i]);
                num -= nums[i];
            } else {
                i++;
            }
        }

        return res.toString();
    }
}
