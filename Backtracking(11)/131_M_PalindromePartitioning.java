/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

  [
    ["aa","b"],
    ["a","a","b"]
  ]
*/
// Tag: Backtracking

// 最后将判断回文从for loop改为while loop通过了，但是肯定有更好的方法，以后再看，应该DP好一点
public class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        List<String> path = new ArrayList<String>();
        partitionHelper(result, path, s, 0);
        return result;
    }

    public void partitionHelper(List<List<String>> result, List<String> path, String s, int pos){
        // 如果已经判定到string的最后并且合法，则输出，具体说明见93题
        if (pos == s.length()){
            result.add(new ArrayList<String>(path));
            return;
        }
        for (int i = pos; i < s.length(); i++){
            // 判断当前子串是不是回文
            if (!isPalindrome(s.substring(pos, i + 1))){
                continue;
            }
            path.add(s.substring(pos, i + 1));
            partitionHelper(result,path, s, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public boolean isPalindrome(String str){
        // 刚开始用for循环还判断了string长度为1的情况，其实只需要排除while中不符合的情况即可，其他情况都return true
        int start = 0;
        int end = str.length() - 1;

        while (start < end){
            if (str.charAt(start) != str.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
