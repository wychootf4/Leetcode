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
// Reference: http://www.cnblogs.com/yuzhangcmu/p/4105529.html
/*
Solution1:利用递归实现,case1: p的第二个字符不是*,则判断p和s的第一个是否匹配(字符相同或p为.),若匹配则继续;
case2:p的第二个字符是*,此时有两种情况,case2.1:*代表第一个字符出现0次,则检查s与p跳过前两位是否匹配; case2.2:
*代表第一个字符出现1次或多次,则遍历第一个字符出现1,2,3..次的情况,直至不匹配出现
 */
public class Solution {
    public boolean isMatch(String s, String p) {
        // base case
        if (p.length() == 0) {
            return s.length() == 0;
        }

        // special case length = 1
        if (p.length() == 1) {
            if (s.length() != p.length()) {
                return false;
            }
            return p.charAt(0) == '.' || p.charAt(0) == s.charAt(0);
        }

        // second char of pattern != '*'
        if (p.length() == 1 || p.charAt(1) != '*') {
            // not matching if s is empty, or first char of p is neither . nor same as char of s
            if (s.length() < 1 || (p.charAt(0) != '.' && p.charAt(0) != s.charAt(0))) {
                return false;
            }
            // otherwise keep matching remain part
            return isMatch(s.substring(1), p.substring(1));
            // second char of p is '*'
        } else {
            int sLen = s.length();
            // index = -1 means * stands for 0 element
            // index >= 0 means * stands for 1 or more preceding elements
            int index = -1;
            // 如果当前位不匹配则跳出循环,返回false
            // 如果当前位匹配则枚举*匹配0,1,2...位的情况
            /*
            例如s为aaaab,p为a*b,除了遍历第一个字符时以外,每次循环条件都要求当前index所指的s的char要与p的第一个字符匹配.
            即在保证当前s的char与p的第一个字符匹配的前提下,去找s中第一个与p中的b所匹配的位置.
             */
            while (index < s.length() && (index < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(index))) {
                if (isMatch(s.substring(index + 1), p.substring(2))) {
                    return true;
                }
                index++;
            }
            return false;
        }
    }
}
// Time Compexity is O(2 ^ n). Suppose s is aaaaaaaaaaa, p is a*a*a*, n is length of s. Since each
// a in s have two choices, matching current a* or the next one.

/*
Solution2: DP
 */