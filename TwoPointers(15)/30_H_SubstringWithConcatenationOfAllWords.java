/*
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting
indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening
characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).
*/
// Tag: Hash Table, Two Pointers, String
public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<Integer>();
        if (s == null || s.length() == 0 || words == null){
            return result;
        }
        // 新建两个哈希表，toFind定义待查找的word，found定义已经找到的word
        Map<String, Integer> toFind = new HashMap<String, Integer>();
        Map<String, Integer> found = new HashMap<String, Integer>();
        int dictLen = words.length;
        int wordLen = words[0].length();

        // 首先将toFind初始化，统计字典中某个单词需要被找到的次数
        for (String word : words){
            if (!toFind.containsKey(word)){
                toFind.put(word, 1);
            }else{
                toFind.put(word, toFind.get(word) + 1);
            }
        }

        // 从字符串的头部开始检索，i代表当前的检索头，注意循环结束时的检索头应该是倒数出字典中单词个数*单词长度个字符的
        for (int i = 0; i <= s.length() - dictLen * wordLen; i++){
            // 每次检索时重置found
            found.clear();
            // j代表当前检索到某个单词
            int j;
            for (j = 0; j < dictLen; j++){
                // 每次检索查找word长的子串是否在found中，一共查找dictLen，即单词个数次
                // index表示的是以当前检索位i开头，已经检查完前j个字段，开始从新一个字段检查
                int index = i + j * wordLen;
                String sub = s.substring(index, index + wordLen);
                // 如果当前字段根本不是有效的word直接break
                if (!toFind.containsKey(sub)){
                    break;
                }

                if (!found.containsKey(sub)){
                    found.put(sub, 1);
                }else{
                    found.put(sub, found.get(sub) + 1);
                }
                // 如果某个word在字符串中重复出现，已经超过需要被检索到的次数，证明当前的i肯定不满足条件，break
                if (found.get(sub) > toFind.get(sub)){
                    break;
                }
            }
            // 如果字典中需要被检索到的word都被正确检索到了，则在内层循环中不会break，j也就最终能达到字典长度
            // 另外注意这里循环退出时j = dictLen - 1符合条件，然后会j++,因此最后全局变量j = dictLen
            if (j == dictLen){
                result.add(i);
            }
        }

        return result;
    }
}
