/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
*/
// Tag: Dynamic Programming

/*
分析：
state：f[i]表示前i个字符最少需要切几刀
function：f[i] = min{f[j] + 1}, (j < i && j + 1 ~ i是回文串)
initialize：f[i] = i - 1(f[0] = -1，因为前1个字符需要切0刀)
result：f[s.length()]
如果直接这样做，i和j两个变量已经使时间复杂度达到n^2,如果判断回文串是n，整体会达到n^3,这里可以对判断回文串进行优化，使之降到O（1）时间，
预处理用空间换时间。
*/
public class Solution {
    public int minCut(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }

        int[] cut = new int[s.length() + 1];
        cut[0] = -1;
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];

        for (int i = 1; i <= s.length(); i++){
            //初始化每一位，因为如果有i个字符最多要切i - 1刀
            cut[i] = i - 1;

            for (int j = 0; j < i; j++){
                isPalindrome[j][i - 1] = false;
                // 遍历到第i个字符，看看前面的某一个字符j到i - 1是否为回文，并更新切数
                // i - 1 - j = 2代表只有三个字符的情况
                if (s.charAt(j) == s.charAt(i - 1) && (i - 1 - j <= 2 || isPalindrome[j + 1][i - 2])){
                    isPalindrome[j][i - 1] = true;
                    cut[i] = Math.min(cut[i], cut[j] + 1);
                }
            }
        }

        return cut[s.length()];
    }
}
