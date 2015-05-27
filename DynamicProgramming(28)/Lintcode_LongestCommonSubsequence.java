/*
Given two strings, find the longest common subsequence (LCS).

Your code should return the length of LCS.

Example
For "ABCD" and "EDCA", the LCS is "A" (or D or C), return 1

For "ABCD" and "EACB", the LCS is "AC", return 2

Clarification
What's the definition of Longest Common Subsequence?

    * The longest common subsequence (LCS) problem is to find the longest subsequence common to all sequences
    in a set of sequences (often just two). (Note that a subsequence is different from a substring, for the terms
    of the former need not be consecutive terms of the original sequence.) It is a classic computer science problem,
    the basis of file comparison programs such as diff, and has applications in bioinformatics.

    * https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
*/
// Tag: Dynamic Programming

/*
分析：
state: f[i][j]表示前i个字符配上前j个字符的LCS的长度
function: f[i][j] = f[i-1][j-1] + 1           // a[i] == b[j]
                  = MAX(f[i-1][j], f[i][j-1]) // a[i] != b[j]
intialize: f[i][0] = 0
f[0][j] = 0
answer: f[a.length()][b.length()]
*/
public class Solution {
    /**
     * @param A, B: Two strings.
     * @return: The length of longest common subsequence of A and B.
     */
    public int longestCommonSubsequence(String A, String B) {
        // write your code here
        if (A == null || B == null || A.length() == 0 || B.length() == 0){
            return 0;
        }

        int lenA = A.length();
        int lenB = B.length();
        int[][] sums = new int[lenA + 1][lenB + 1];

        for (int i = 0; i <= lenA; i++){
            for (int j = 0; j <= lenB; j++){
                if (i == 0 || j == 0){
                    sums[i][j] = 0;
                }else{
                    // 由于charAt首位是从0开始的，所以检查时候index要减1.
                    if (A.charAt(i - 1) == B.charAt(j - 1)){
                        sums[i][j] = sums[i - 1][j - 1] + 1;
                    }else{
                        sums[i][j] = Math.max(sums[i - 1][j], sums[i][j - 1]);
                    }
                }
            }
        }

        return sums[lenA][lenB];
    }
}
