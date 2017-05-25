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


/*
Solution1: 先把这个数给reverse，然后再比较是否一样，如果是一样的就是palindrome.注意负数的情况.
时间复杂度O(n),空间复杂度O(1).
 */
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

/*
Solution2: 将数转换为String，然后判断回文串，时间复杂度O(n), 空间复杂度O(1),但是这样就用了extra space
 */
public class Solution {
    public boolean isPalindrome(int x) {
        // bug: misunderstanding, negative integers here can not be palindrome.
        if (x < 0) {
            return false;
        }

        String str = String.valueOf(x);

        int start = 0;
        if (str.charAt(0) == '-' || str.charAt(0) == '+') {
            start = 1;
        }
        int end = str.length() - 1;

        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }
}