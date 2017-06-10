/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
*/
// Tag: Stack, String
// Company: Google, Airbnb, Facebook, Twitter, Zenefits, Amazon, Microsoft, Bloomberg

/*
Solution: 利用一个stack，遍历字符串，如果当前字符是左半边就入栈，而如果是右半边，就和栈顶比较；如果当前字符与栈顶
匹配则弹栈，证明匹配上，否则非法。最后如果栈不为空则证明有左半边没有被匹配上，也非法。
时间复杂度为O(n),空间复杂度为O(1).
 */
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
