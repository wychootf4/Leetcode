/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
*/
// Tag: Stack

/*
分析：
维护一个栈，如果是数就入栈。新建一个运算符串，如果当前元素和运算符串匹配则从栈中弹出两个元素参与运算，然后将结果入栈。
*/
public class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0){
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();
        String operators = "+-*/";

        for (String token : tokens){
            if (!operators.contains(token)){
                stack.push(Integer.valueOf(token));
            }else{
                int a = stack.pop();
                int b = stack.pop();
                int index = operators.indexOf(token);
                switch(index){
                    case 0 :
                        stack.push(b + a);
                        break;
                    case 1 :
                        stack.push(b - a);
                        break;
                    case 2 :
                        stack.push(b * a);
                        break;
                    case 3 :
                        stack.push(b / a);
                        break;
                }
            }
        }

        return stack.pop();
    }
}
