/*
Related to question Excel Sheet Column Title

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
*/
// Tag: Math
public class Solution {
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }

        int result = 0;
        int i = s.length() - 1;
        int p = 0;
        while (i >= 0){
            char c = s.charAt(i);
            result += (int)(Math.pow(26, p) * (c - 'A' + 1));
            i--;
            p++;
        }

        return result;
    }
}
