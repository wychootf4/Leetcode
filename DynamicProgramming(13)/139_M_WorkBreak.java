/*
Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence
of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
*/
// Tag: Dynamic Programming

// Solution1: 简单DP，但是判断后半段是否为字典的单词时可能会TLE
/*
state: f[i]表示前i个字符能否被完美切分 function: f[i] = OR{f[j]}, j < i, j+1 ~ i是一个词典中的单词
function: f[i] = OR{f[j]}, j < i, j+1 ~ i是一个词典中的单词
intialize: f[0] = true
answer: f[s.length()] 注意:切分位置的枚举->单词长度枚举 O(NL), N: 字符串长度, L: 最长的单词的长度
*/
public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null) {
            return false;
        }

        int len = s.length();
        if (len == 0) {
            return true;
        }

        boolean[] D = new boolean[len + 1];

        // initiate the DP. 注意，这里设置为true是不得已，因为当我们划分字串为左边为0，右边为n的时候，
        // 而右边的n是一个字典string,那么左边必然要设置为true，才能使结果为true。所以空字符串我们需要
        // 认为true
        D[0] = true;

        // D[i] 表示i长度的字符串能否被word break.
        for (int i = 1; i <= len; i++) {
        	// 把子串划分为2部分，分别讨论, j 表示左边的字符串的长度
        	// 成立的条件是：左边可以break, 而右边是一个字典单词
        	D[i] = false;
        	for (int j = 0; j < i; j++) {
        		if (D[j] && dict.contains(s.substring(j, i))) {
        			// 只要找到任意一个符合条件，我们就可以BREAK; 表示我们检查的
        			// 这一个子串符合题意
        			D[i] = true;
        			break;
        		}
        	}
        }

        return D[len];
    }
}

// Solution2: 优化后的DP，先将是否为字典单词的结果保存下来
public class Solution {
    // 取得最长单词长度
    private int getMaxLength(Set<String> dict) {
        int maxLength = 0;
        for (String word : dict) {
            maxLength = Math.max(maxLength, word.length());
        }
        return maxLength;
    }

    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0) {
            return false;
        }
        // 取得最大的单词长度
        int maxLength = getMaxLength(dict);
        boolean[] canSegment = new boolean[s.length() + 1];
        // 长度为0的串可以分割
        canSegment[0] = true;
        // 将待检查的串分为两部分，i之前的某位置j，0-j和j- i
        for (int i = 1; i <= s.length(); i++) {
            canSegment[i] = false;
            // j代表前面一段的长度，比最大单词长度还长就没必要检查了
            for (int j = 1; j <= maxLength && j <= i; j++) {
                if (!canSegment[i - j]) {
                    continue;
                }
                // 如果后面一段是单词记录长度为i的字符串是可以分的
                String word = s.substring(i - j, i);
                if (dict.contains(word)) {
                    canSegment[i] = true;
                    break;
                }
            }
        }

        return canSegment[s.length()];
    }
}
