/*
Given a string, determine if a permutation of the string could form a palindrome.

For example,
"code" -> False, "aab" -> True, "carerac" -> True.

Hint:
Consider the palindromes of odd vs even length. What difference do you notice?
Count the frequency of each character.
If each character occurs even number of times, then it must be a palindrome. How about character
which occurs odd number of times?
*/
// Company: Google, Uber
// Tag: Hash Table

/*
思路：不需要将字符统计然后permutation判断是否为palindrome，根据观察得知，可以先统计每个字符出现的次数，
而奇数个的字符只能有一个或者没有才能满足条件
*/
public class Solution {
    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        // bug:最初想的是只能有一个字符出现一次，其实是只能有一个字符出现奇数次，比如abbba
        int oddCounter = 0;
        for (int i : map.values()) {
            if (i % 2 != 0) {
                oddCounter++;
            }
            if (oddCounter > 1) {
                return false;
            }
        }

        return true;
    }
}

//时间和空间复杂度都是O(n)
//还可以利用hashset，某个字符出现奇数次加入，偶数次移除，最后统计hashset是不是大于1
