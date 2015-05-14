/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
*/
// Tag: Two Pointers, String

public class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0 || s.length() == 1){
            return true;
        }

        int start = 0;
        int end = s.length() - 1;
        while (start < end){
            // 另有Character.isLetterOrDigit可以直接判断
            if (!isValid(s.charAt(start))){
                start++;
                continue;
            }
            if (!isValid(s.charAt(end))){
                end--;
                continue;
            }
            // 要忽略大小写
            if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))){
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    private boolean isValid(char c){
        if ('a' <= c && c <= 'z'){
            return true;
        }
        if ('A' <= c && c <= 'Z'){
            return true;
        }
        if ('0' <= c && c <= '9'){
            return true;
        }

        return false;
    }
}
