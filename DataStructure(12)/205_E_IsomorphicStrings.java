/*
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.
*/
// Tag: Hash Table

// 用两个哈希表保存相同位字符的对应关系
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null){
            return false;
        }

        Map<Character, Character> map1 = new HashMap<Character, Character>();
        Map<Character, Character> map2 = new HashMap<Character, Character>();

        for (int i = 0; i < s.length(); i++){
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (map1.containsKey(c1) && (map1.get(c1) != c2)){
                return false;
            }
            if (map2.containsKey(c2) && (map2.get(c2) != c1)){
                return false;
            }
            map1.put(c1, c2);
            map2.put(c2, c1);
        }

        return true;
    }
}
