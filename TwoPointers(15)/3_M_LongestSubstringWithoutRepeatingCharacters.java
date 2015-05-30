/*
Given a string, find the length of the longest substring without repeating characters. For example, the longest
substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring
is "b", with the length of 1.
*/
// Tag: Hash Table, Two Pointers, String

/*
思路：
此题刚开始理解错误以为是求无重复的最大子串，忽视了是重复的字符，即子串必须所有的字符不同。那么利用哈希表存储某个字母及其最后出现的index的对应
关系，并且维护两个指针，头指针指向无重复子串的头，尾指针指向尾；尾指针一直向后扫，如果发现了重复字符，并且该重复字符的出现位置是在头指针之后，则
将头指针挪到该字符上一次出现位置的后面，过程中持续更新最大子串的长度。
*/

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        // 维护一个合法的(start, end)区间计算最大子串的长度
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int start = 0;
        for (int end = 0; end < s.length(); end++){
            char c = s.charAt(end);
            // 发现重复元素且该元素是在统计区间内，即index在start的后面
            if (map.containsKey(c) && map.get(c) >= start){
                // 将区间的start置为重复元素上一次出现位置的后面，消除不合法
                start = map.get(c) + 1;
            }
            // 每次更新某个字符最后出现的index
            map.put(c, end);
            max = Math.max(max, end - start + 1);
        }

        return max;
    }
}
