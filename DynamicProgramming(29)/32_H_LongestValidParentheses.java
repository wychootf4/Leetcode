/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed)
parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
*/
// Tag: Dynamic Programming, String

/*
Solution: 遍历字符串,遇到(加入栈,遇到右括号如下处理:1如果当前栈为空则证明没有(能与其匹配,重置accLen;2.若栈不为空,
取出一个(,此为能与当前的)最近的(,计算curLen.若此时取完后栈为空,accLen应该累加上curLen,将curLen置为accLen
*/

public class Solution {
    public int longestValidParentheses(String s) {
        if (s.length() == 0 || s == null) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();
        int maxLen = 0;
        int accumulatedLen = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    accumulatedLen = 0;
                } else {
                    int matchedPos = stack.pop();
                    int matchedLen = i - matchedPos + 1;

                    if (stack.isEmpty()) {
                        // 如果已经没有其他(可供配对了，累加当前的距离
                        accumulatedLen += matchedLen;
                        matchedLen = accumulatedLen;
                    } else {
                        // 类似(()()这种，可能栈内有很多未用的(,而后面连续的()已经配对，这时找到当前最后的)与最近的未配对(之间
                        // 距离即为最大距离
                        matchedLen = i - stack.peek();
                    }
                    maxLen = Math.max(maxLen, matchedLen);
                }
            }
        }
        return maxLen;
    }
}