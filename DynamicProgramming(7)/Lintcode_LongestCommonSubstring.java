/*
Given two strings, find the longest common substring.

Return the length of it.

Note
The characters in substring should occur continiously in original string. This is different with subsequnce.

Example
Given A=“ABCD”, B=“CBCE”, return  2
*/
// Tag: Dynamic Programming

/*
state: f[i][j]表示前i个字符配上前j个字符的LCS的长度 (一定以第i个和第j个结尾的LCS)
function: f[i][j] = f[i-1][j-1] + 1 // a[i] == b[j]
                  = 0               // a[i] != b[j]
intialize: f[i][0] = 0, f[0][j] = 0
answer: MAX(f[0..a.length()][0..b.length()])
*/
public class Solution {
    /**
     * @param A, B: Two string.
     * @return: the length of the longest common substring.
     */
    public int longestCommonSubstring(String A, String B) {
        // write your code here
        if (A == null || B == null || A.length() == 0 || B.length() == 0){
            return 0;
        }

        int lenA = A.length();
        int lenB = B.length();
        int[][] sums = new int[lenA + 1][lenB + 1];
        int max = 0;

        for (int i = 0; i <= lenA; i++){
            for (int j = 0; j <= lenB; j++){
                if (i == 0 || j == 0){
                    sums[i][j] = 0;
                }else{
                    if (A.charAt(i - 1) == B.charAt(j - 1)){
                        sums[i][j] = sums[i - 1][j - 1] + 1;
                    }else{
                        sums[i][j] = 0;
                    }
                }
                max = Math.max(max, sums[i][j]);
            }
        }

        return max;
    }
}
