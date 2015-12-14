/*
Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
*/
// Tag: Math, String

// 将每位运算的结果加到stringbuffer中，判断最后的carry是否为1.
public class Solution {
    public String addBinary(String a, String b) {
        if (a == null || b == null){
            return null;
        }
        if (a.length() == 0){
            return b;
        }
        if (b.length() == 0){
            return a;
        }

        StringBuilder sb = new StringBuilder();
        int pa = a.length() - 1;
        int pb = b.length() - 1;
        int carry = 0;
        int sum = 0;

        while (pa >= 0 || pb >= 0){
            sum = carry;
            if (pa >= 0){
                sum += (a.charAt(pa) - '0');
            }
            if (pb >= 0){
                sum += (b.charAt(pb) - '0');
            }

            char c = sum % 2 == 1 ? '1' : '0';
            sb.insert(0, c);
            carry = sum / 2;
            pa--;
            pb--;
        }

        if (carry == 1){
            sb.insert(0, '1');
        }

        return sb.toString();
    }
}
