/*
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Clarification:
What constitutes a word?
-A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
-Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
-Reduce them to a single space in the reversed string.
*/
// Tag: String

/*
思路：三步翻转法可以做，这里标准答案用的思路是倒序遍历字符串，记录每个word的起始位置，当遍历到一个word的开始位置
时append
*/
public class Solution {
    public String reverseWords(String s) {
        StringBuilder reversed = new StringBuilder();
        int end = s.length(); // 尾指针，由于substring的性质，这里直接指到word末尾的实际位置+1
        // i是头指针
        for (int start = s.length() - 1; start >= 0; start--){
            // 如果当前位是空格，尾指针往前跳到头指针处
            if (s.charAt(start) == ' '){
                end = start;
            // 否则如果头指针走到最前面或者再往前一位是空格，证明此时要将word输出了，而且因为是逆序遍历的，因此
            // 最后一个word是出现在结果的第一位，符合题目要求
            }else if (start == 0 || s.charAt(start - 1) == ' '){
                // 如果此时reversed长度不为0说明前面有word被输出，加空格隔开
                if (reversed.length() != 0){
                    reversed.append(' ');
                }
                // 将此word加入buffer，继续向下遍历
                reversed.append(s.substring(start, end));
            }
        }
        return reversed.toString();
    }
}
