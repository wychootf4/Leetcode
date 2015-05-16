/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
*/
// Tag: Stack, String

public class Solution {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0){
            return false;
        }

        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()){
            // 如果左的就入栈，注意contains要把char转换成string
            if ("({[".contains(String.valueOf(c))){
                stack.push(c);
            // 如果不是左的且栈不为空且能配对就弹栈
            }else if (!stack.isEmpty() && isValid(stack.peek(), c)){
                stack.pop();
            // 前两种都不符合就返回false
            }else{
                return false;
            }
        }
        // 如果最后stack不为空就是true，否则为false
        return stack.isEmpty();
    }

    private boolean isValid(char c1, char c2){
        return (c1 == '(' && c2 == ')') || (c1 == '{' && c2 == '}') || (c1 == '[' && c2 == ']');
    }
}
