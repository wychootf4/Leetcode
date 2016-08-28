/*
Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?
*/
// Tag: Hash Table, Sort

/*
思路: 如果不考虑input为unicode字符的话默认其均为小写字母，可以用简单解法，利用数组记录26个字母出现的次数。
若考虑到Unicode的情况，则利用HashMap存储每个字符对应出现的次数，第二遍扫描时候进行判断。
*/
public class Solution {
    public boolean isAnagram(String s, String t) {
        if (t == null || s == null) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        char[] tChar = t.toCharArray();
        for (int i = 0; i < tChar.length; i++) {
            if (map.containsKey(tChar[i])) {
                map.put(tChar[i], map.get(tChar[i]) + 1);
            } else {
                map.put(tChar[i], 1);
            }
        }

        char[] sChar = s.toCharArray();
        for (int i = 0; i < sChar.length; i++) {
            if (map.containsKey(sChar[i])) {
                map.put(sChar[i], map.get(sChar[i]) - 1);
                if (map.get(sChar[i]) == 0) {
                    map.remove(sChar[i]);
                }
            } else {
                return false;
            }
        }

        if (map.size() != 0) {
            return false;
        }

        return true;
    }
}
