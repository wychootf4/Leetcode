/*
Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000,
and there exists one unique longest palindromic substring.
*/
// Tag: String
// Company: Amazon, Microsoft, Bloomberg

/*
分析：
另有DP的解法，这里用的是遍历一遍，记录以某个字符为中心所能取得的最大长度，持续更新。需要注意的是由于回文有奇数中心和偶数中心
两种，需要两种情况都考虑到，即aba型回文和abba型回文。
时间复杂度O(n^2),空间复杂度O(1)
*/
public class Solution {
    public String longestPalindrome(String s) {
        String result = null;
        if (s == null || s.length() == 0){
            return result;
        }

        int max = 0;

        for (int i = 0; i < s.length(); i++){
            String s1 = getLongest(s, i, i);
            String s2 = getLongest(s, i, i + 1);

            if (s1.length() > max){
                max = s1.length();
                result = s1;
            }
            if (s2.length() > max){
                max = s2.length();
                result = s2;
            }
        }

        return result;
    }

    private String getLongest(String s, int left, int right){
        while (left >= 0 && right < s.length()){
            if (s.charAt(left) != s.charAt(right)){
                break;
            }
            left--;
            right++;
        }

        return s.substring(left + 1, right);
    }
}

/*
思路2：DP方法，不推荐，据说有的公司会拒掉DP的解法，详见：
http://www.cnblogs.com/yuzhangcmu/p/4189068.html
 */
