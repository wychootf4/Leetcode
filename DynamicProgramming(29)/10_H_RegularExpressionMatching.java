/*
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
*/
// Tag: Dynamic Programming, Backtracking, String

/*
题意解析:注意这里*代表前面的一个字符出现0次或多次,比如.*表示.出现0次或多次,多个.可以匹配任一字符.
*/
// Solution1: 利用递归实现,真正匹配的条件为两个字符相同或是字符与'.'匹配. 如果当前p字符不是*,判断是否匹配,如果是*则
// 枚举所有情况.
public class Solution {
    public boolean isMatch(String s, String p) {
        // base case
        if (p.length() == 0) {
            return s.length() == 0;
        }

        //
        if (p.length() == 1) {
            if (s.length() < 1) {
                return false;
                // first not match
            } else {
                return matchChar(s.charAt(0), p.charAt(0));
            }
        }
    }


}