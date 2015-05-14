/*
Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

For example, Given s = “eceba”,

T is "ece" which its length is 3.
*/
// Tag: Hash Table, Two Pointers, String

public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }

        int head = 0;
        int tail = 0;
        int max = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();

        while (head < s.length()){
            // 如果哈希表中已有某个字符则该字符次数+1，如果没有且map仍能容纳则加入
            while (head < s.length()){
                char c = s.charAt(head);
                if (map.containsKey(c)){
                    map.put(c, map.get(c) + 1);
                    head++;
                }else if (!map.containsKey(c) && map.size() < 2){
                    map.put(c, 1);
                    head++;
                // 都不符合证明head不能再向前走，形成一个有效的窗口，跳出head这个循环
                }else{
                    break;
                }
            }
            // 更新当前的最长子串
            max = Math.max(max, head - tail);
            // 随着tail增加从map中删除当前的字符，直到删除掉一个字符为止，然后进入下一次循环head继续向前走
            while (tail < head && map.size() == 2){
                char ch = s.charAt(tail);
                map.put(ch, map.get(ch) - 1);
                if (map.get(ch) == 0){
                    map.remove(ch);
                }
                tail++;
            }
        }

        return max;
    }
}

// Follow-up: with at most k distinct characters
public class Solution {
    /**
     * @param s : A string
     * @return : The length of the longest substring
     *           that contains at most k distinct characters.
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        if (s == null || s.length() == 0 || k == 0){
            return 0;
        }

        int head = 0;
        int tail = 0;
        int max = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();

        while (head < s.length()){
            while (head < s.length()){
                char c = s.charAt(head);
                if (map.containsKey(c)){
                    map.put(c, map.get(c) + 1);
                    head++;
                }else if (!map.containsKey(c) && map.size() < k){
                    map.put(c, 1);
                    head++;
                }else{
                    break;
                }
            }

            max = Math.max(max, head - tail);

            while (tail < head && map.size() == k){
                char ch = s.charAt(tail);
                map.put(ch, map.get(ch) - 1);
                if (map.get(ch) == 0){
                    map.remove(ch);
                }
                tail++;
            }
        }

        return max;
    }
}
