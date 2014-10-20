/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"
*/
// Tag: Backtracking, String

public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        StringBuilder path = new StringBuilder();
        gpHelper(result, path, n, n);
        return result;
    }

    public void gpHelper(List<String> result, StringBuilder path, int left, int right){
        // 左右括号都为0，证明该加的括号都被加上了，如何控制不合法的组合？看下面
        if (left == 0 && right == 0){
            result.add(path.toString());
            return;
        }
        /* 三个判断条件分别对两种情况起作用，比如n = 2时：
        (1) left> right: 若进行到helper(1,0)情况，此情况肯定是（1，1）的内层递归，也就是helper（1，0）时path中
        最后一个string是")"。因为每次递归调用都是从左括号开始加起，不可能出现helper（2，0）的情况，即右括号比左括号多两个。
        由于括号规定必须是（）配对，不可能出现)(,而当进行到helper(1,0)时，如果不加上left > right判断跳出，则向下递归跳入
        内层helper(0,0)时会判定满足条件，加入solution set中。但是此时path中最后一个string必然是"("，没有右括号
        与之配对，是不合法的。
        (2) left < 0 || right < 0): 判断已经没有左括号或者右括号可以加了，跳出不合法的递归
        */
        if (left > right || left < 0 || right < 0){
            return;
        }

        gpHelper(result, path.append("("), left - 1, right);
        // remove掉进入内层递归时加到参数path上的括号，回溯
        path.deleteCharAt(path.length() - 1);
        gpHelper(result, path.append(")"), left, right - 1);
        path.deleteCharAt(path.length() - 1);
    }
}
