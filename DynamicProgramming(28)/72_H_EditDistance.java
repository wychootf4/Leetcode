/*
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2.
(each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
*/
// Tag: Dynamic Programming, String

/*
分析：
state: f[i][j]a的前i个字符“配上”b的前j个字符 最少要用几次编辑使得他们相等
function:f[i][j] = MIN(f[i-1][j-1], f[i-1][j]+1, f[i][j-1]+1) // a[i] == b [j]
                 = MIN(f[i-1][j], f[i][j-1], f[i-1][j-1]) + 1 // a[i] != b[j]
intialize: f[i][0] = i, f[0][j] = j
answer: f[a.length()][b.length()]
*/
public class Solution {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null){
            return 0;
        }

        int len1 = word1.length();
        int len2 = word2.length();
        int[][] sums = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++){
            sums[i][0] = i;
        }
        for (int i = 0; i <= len2; i++){
            sums[0][i] = i;
        }

        for (int i = 1; i <= len1; i++){
            for (int j = 1; j <= len2; j++){
                // 如果最后一位是相同的就对比前面的位
                if (word1.charAt(i - 1) == word2.charAt(j - 1)){
                    sums[i][j] = sums[i - 1][j - 1];
                }else{
                // 如果最后一位不同，或者直接替换最后一位，或者删掉其中一个的最后一位
                    sums[i][j] = Math.min(sums[i - 1][j - 1], Math.min(sums[i - 1][j], sums[i][j - 1])) + 1;
                }
            }
        }

        return sums[len1][len2];
    }
}
