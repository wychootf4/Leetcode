/*
Given a string which contains only letters. Sort it by lower case first and upper case second.

Example
For "abAcD", a reasonable answer is "acbAD"

Note
It's not necessary to keep the original order of lower-case letters and upper case letters.

Challenge
Do it in one-pass and in-place.
*/
// Tag: String, Two Pointers, Sort

public class Solution {
    /**
     *@param chars: The letter array you should sort by Case
     *@return: void
     */
    public void sortLetters(char[] chars) {
        //write your code here
        if (chars == null || chars.length == 0){
            return;
        }

        int start = 0;
        int end = chars.length - 1;

        // 先跳过头尾不需要交换的元素
        while (start < chars.length && 'a' <= chars[start] && chars[start] <= 'z'){
            start++;
        }
        while (end >= 0 && 'A' <= chars[end] && chars[end] <= 'Z'){
            end--;
        }

        while (start < end){
            // 交换元素
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            // 找到下一次需要交换的位置
            while (start < chars.length && 'a' <= chars[start] && chars[start] <= 'z'){
                start++;
            }
            while (end >= 0 && 'A' <= chars[end] && chars[end] <= 'Z'){
                end--;
            }
        }
    }
}
