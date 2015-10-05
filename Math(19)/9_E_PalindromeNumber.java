/*
Determine whether an integer is a palindrome. Do this without extra space.

Some hints:
Could negative integers be palindromes? (ie, -1)

If you are thinking of converting the integer to string, note the restriction of using extra space.

You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know
that the reversed integer might overflow. How would you handle such case?

There is a more generic way of solving this problem.
*/
// Tag: Math

// 先把这个数给reverse，然后再比较是否一样，如果是一样的就是palindrome.注意负数的情况
public class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0){
            return false;
        }

        return x == reverse(x);
    }

    private int reverse(int x){
        long result = 0;
        while (x != 0){
            result = result * 10 + x % 10;
            x = x / 10;
        }

        return (int)result;
    }
}
