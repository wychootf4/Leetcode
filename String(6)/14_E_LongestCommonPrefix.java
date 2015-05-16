/*
Write a function to find the longest common prefix string amongst an array of strings.
*/
// Tag: String

/*
分析：
以第一个字符串为基准，遍历后面的字符串，设立一个指针j如果满足条件则j++，作为LCP的尾
*/
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++){
            int j = 0;
            // 每次新的循环时j都会清零，重新计算与当前字符串的LCP
            while (j < strs[i].length() && j < prefix.length() && strs[i].charAt(j) == prefix.charAt(j)){
                j++;
            }
            if (j == 0){
                return "";
            }

            prefix = prefix.substring(0, j);
        }

        return prefix;
    }
}
