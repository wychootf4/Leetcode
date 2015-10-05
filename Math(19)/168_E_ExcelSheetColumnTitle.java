/*
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB
*/
// Tag: Math

// 注意是从1开始对应A，不是0
// Solution1: Iteration
public class Solution {
    public String convertToTitle(int n) {
        if (n <= 0){
            return null;
        }

        StringBuilder sb = new StringBuilder();
        while (n > 0){
            n--;
            char c = (char)(n % 26 + 'A');
            sb.insert(0, c);
            n /= 26;
        }

        return sb.toString();
    }
}

// 记住做数运算时除法是大的，取模只是末尾一位
// Solution2: Recursion
public class Solution {
    public String convertToTitle(int n) {
        if (n == 0){
            return "";
        }

        return convertToTitle((n - 1) / 26) + (char)((n - 1) % 26 + 'A');
    }
}
