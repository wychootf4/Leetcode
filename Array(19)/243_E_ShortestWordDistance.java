/*
Given a list of words and two words word1 and word2, return the shortest distance between
these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/
// Company: Linkedin
// Tag: Array

/*
思路：设置两个指针，扫一遍字符串：
  1.如果当前字符串匹配第一个单词，且已经找到某个index的第二个单词，则比较。由于先检查word1，所以index2肯定
  小于index1
  2.同理检查第二个单词是否有匹配的word1
  由于是One pass，因此时间复杂度是n，空间复杂度是1
*/
public class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int index1 = -1;
        int index2 = -1;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < words.length; i++) {
            String str = words[i];
            if (word1.equals(str)) {
                index1 = i;
                if (index2 != -1) {
                    minDistance = Math.min(minDistance, index1 - index2);
                }
            } else if (word2.equals(str)) {
                index2 = i;
                if (index1 != -1) {
                    minDistance = Math.min(minDistance, index2 - index1);
                }
            }
        }

        return minDistance;
    }
}
