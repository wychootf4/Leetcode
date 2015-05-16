/*
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
*/
// Tag: String

/*
分析：
用递归的做法，递归每次减少一位，并且如果当前数和后面的数不重复则加入计数和数本身；如果和后面的数重复则不进行操作，计数+1
*/
public class Solution {
    public String countAndSay(int n) {
        if (n == 0){
            return null;
        }
        if (n == 1){
            return "1";
        }

        StringBuilder sb = new StringBuilder();
        String s = countAndSay(n - 1);
        int len = s.length();
        int count = 0;

        for (int i = 0; i < len; i++){
            count++;
            if (i == len - 1 || (i < len - 1 && s.charAt(i) != s.charAt(i + 1))){
                sb.append(count);
                sb.append(s.charAt(i));
                count = 0;
            }
        }

        return sb.toString();
    }
}
