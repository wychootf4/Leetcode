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
// Company: Google, Uber, Airbnb, Facebook, Twitter

/*
题意解析:注意这里*代表前面的一个字符出现0次或多次,比如.*表示.出现0次或多次,多个.可以匹配任一字符.
也就是说，.是单独出现的，而*是与前一个字符一起出现的，c*可以是空，c，cc，ccc。。。
.*也可以是空，.,..,...
*/
// Reference: http://www.cnblogs.com/yuzhangcmu/p/4105529.html
/*
Solution1:利用递归实现,case1: p的第二个字符不是*,则判断p和s的第一个字符是否匹配(字符相同或p为.),若匹配则继续;
case2:p的第二个字符是*,此时有两种情况,case2.1:*代表第一个字符出现0次,则检查s的字符与p中跳过前两位的字符是否匹配; case2.2:
*代表第一个字符出现1次或多次,则遍历第一个字符出现1,2,3..次的情况,直至不匹配出现
 */
public class Solution {
    public boolean isMatch(String s, String p) {
        // base case
        if (p.length() == 0) {
            return s.length() == 0;
        }

        // special case，p串只有一个字符待匹配
        // case 1: 如果p串的第二个字符不是*
        if (p.length() == 1 || p.charAt(1) != '*') {
            // s串为空匹配不成
            if (s.length() < 1) {
                return false;
            }
            // p串的第一个字符跟s的匹配不上
            if ((p.charAt(0) != s.charAt(0)) && (p.charAt(0) != '.')) {
                return false;
            } else {
                // 第一个字符匹配上了则接着匹配余下来的部分
                return isMatch(s.substring(1), p.substring(1));
            }
        }

        // case 2: 如果p串的第二个字符是*
        else {
            //case 2.1: *代表0的情况，p直接从第三个字符开始匹配
            if (isMatch(s, p.substring(2))) {
                return true;
            }

            //case 2.2: a char & '*' can stand for 1 or more preceding element,
            //so try every sub string
            int i = 0;
            // s串从头开始和p串第一个字符匹配，由于p串第二个字符是*，则s串需要一直匹配到不等于p串中第一个字符为止。
            // 然后递归判断，s的下一个字符是不是和p串的第三个字符，也就是p串中新的字符匹配。
            while (i < s.length() && (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.')){
                if (isMatch(s.substring(i + 1), p.substring(2))) {
                    return true;
                }
                i++;
            }
            return false;
        }
    }
}
// Time Compexity is O(2 ^ n). Suppose s is aaaaaaaaaaa, p is a*a*a*, n is length of s. Since each
// a in s have two choices, matching current a* or the next one.

/*
Solution2: DP
http://www.cnblogs.com/yuzhangcmu/p/4105529.html
 */