/*
Given two words (beginWord and endWord), and a dictionary, find the length of shortest transformation sequence
from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
*/
// Tag: BFS

public class Solution {
    public int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
        if (wordDict == null || wordDict.size() == 0){
            return 0;
        }

        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        wordDict.remove(beginWord);
        int length = 1;

        while (!queue.isEmpty()){
            int size = queue.size();
            // i代表遍历当前层的所有元素
            for (int i = 0; i < size; i++){
                // 取出当前层的首个元素
                String current = queue.poll();
                // 取出后将其各位替换成从a到z的任一字母，
                for (char c = 'a'; c <= 'z'; c++){
                    for (int j = 0; j < current.length(); j++){
                        // 当前元素的待替换位与欲替换的字符相同则跳过，减少计算量
                        if (c == current.charAt(j)){
                            continue;
                        }
                        String tmp = replace(current, j, c);
                        // 如果替换后为目标则返回结果，
                        if (tmp.equals(endWord)){
                            return length + 1;
                        }
                        // 并且如果替换后为字典中的单词则将其入队列，并在字典中抹去，避免重复替换
                        if (wordDict.contains(tmp)){
                            queue.offer(tmp);
                            wordDict.remove(tmp);
                        }
                    }
                }
            }
            length++;
        }

        return 0;
    }

    private String replace(String current, int index, char c){
        char[] chars = current.toCharArray();
        chars[index] = c;

        return new String(chars);
    }
}
