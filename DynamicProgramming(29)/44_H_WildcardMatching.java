/*
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
*/
// Tag: Dynamic Programming, Backtracking, Greedy, String

// Reference: http://www.cnblogs.com/yuzhangcmu/p/4116153.html
/*
Solution1: DP
dp[i][j]表示p从0到i的子串能否与s从0到j的子串匹配
1.如果p.charAt(i) == s.charAt(i) || p.charAt(i) == '?'
dp[i][j] = dp[i - 1][j - 1]
2.如果p.charAt(i) == '*', 有dp[i][j] = dp[i-1][j]||dp[i-1][j-1]||...||dp[i-1][0]
另有dp[i][j-1] = dp[i-1][j-1]||dp[i-1][j-2]...||dp[i-1][0]
可以最终化简为dp[i][j] = dp[i-1][j] || dp[i][j-1]
 */
// Time Complexity: O(mn)
public class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        boolean flag = false;

        for (int i = 0; i <= s.length(); i++) {
            flag = false;
            for (int j = 0; j <= p.length(); j++) {
                // both is empty
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                    flag = true;
                    continue;
                }

                // p is empty, s is not, it is false;
                if (j == 0) {
                    dp[i][j] = false;
                    continue;
                }

                // s is empty, p is not
                if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && p.charAt(j - 1) == '*';
                    // both not empty
                } else {
                    dp[i][j] = (isMatch(s.charAt(i - 1), p.charAt(j - 1)) && dp[i - 1][j - 1]) ||
                            (p.charAt(j - 1) == '*' && (dp[i - 1][j] || dp[i][j - 1]));
                }

                if (dp[i][j]) {
                    flag = true;
                }

                //greedy. 在此可以退出因为*可以匹配余下的所有字符串
                if (dp[i][j] && p.charAt(j - 1) == '*' && j == p.length()) {
                    return true;
                }
            }
            // 对于s的前i位处理完后如果已经有不匹配的了直接返回false
            if (!flag) {
                return false;
            }
        }
        return dp[s.length()][p.length()];
    }

    public boolean isMatch(char s, char p) {
        return (s == p || p == '?');
    }
}

/*
Solution2:用两个指针分别指向字符串,如果匹配则两个指针一起前进;如果匹配到*,在s中依次匹配.
 */