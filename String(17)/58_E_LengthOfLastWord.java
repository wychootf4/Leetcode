/*
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

For example,
Given s = "Hello World",
return 5.
*/
// Tag: String

//逆序检查，如果当前长度为0，空格就continue，非空格长度加1；如果当前长度不为0，空格就break，非空格长度加1.
public class Solution {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }

        int length = 0;
        for (int i = s.length() - 1; i >= 0; i--){
            if (length == 0){
                if (s.charAt(i) == ' '){
                    continue;
                }else{
                    length++;
                }
            }else{
                if (s.charAt(i) == ' '){
                    break;
                }else{
                    length++;
                }
            }
        }

        return length;
    }
}
