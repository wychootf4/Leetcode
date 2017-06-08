/*
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.
0:space 1:null 2:abc 3:def 4:ghi 5:jkl 6:mno 7:pqrs 8:tuv 9:wxyz
Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.
*/
// Tag: String, Backtracking
// Company: Amazon, Dropbox, Google, Uber, Facebook

public class Solution {
    public List<String> letterCombinations(String digits) {
        // 创建二维char数组，按照从0到9的按键顺序初始化，则用char[index][]就可以取到相应数字按键对应的字母
        char[][] map = {{},{},{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}};
        List<String> result = new ArrayList<String>();
        StringBuilder path = new StringBuilder();
        letterHelper(result, map, digits, path);
        return result;
    }

    public void letterHelper(List<String> result, char[][] map, String digits, StringBuilder path){
        // 如果path的长度已经等于给定String的长度了则输出并返回上层递归调用处
        if (path.length() == digits.length()){
            result.add(path.toString());
            return;
        }
        // index表示当前应该取哪个数字里面的字母了，这里取到的数字应该是digits中对应上一外层递归的后面一个字符
        // 比如给定digits是23，由于最开始path.length()长度为0，则会取到digits.charAt(0)对应的数字
        // 由于for循环中path.append()操作，path.length()长度加1，此时进入内层递归后则会取到charAt(1)对应的数字
        // bug: 写成了digits.getNumericValue
        int index = Character.getNumericValue(digits.charAt(path.length()));
        // for循环遍历当前对应数字中所有的字母
        for (int i = 0; i < map[index].length; i++){
            // 将当前对应数字中的第i个字母(char)append到path中
            // bug2: 写成了path.add
            path.append(map[index][i]);
            // 进入内层递归遍历检查digits中当前数字的后面一个数字
            letterHelper(result, map, digits, path);
            // 回溯，删除掉path中最后一个字母，for循环继续检查当前对应数字的下一个字母
            // bug3: wrong method name, removeCharAt
            path.deleteCharAt(path.length() - 1);
        }
    }

}
