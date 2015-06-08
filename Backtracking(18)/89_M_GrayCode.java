/*
The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
*/
//Tag: Backtracking

/*
分析：
我们可以使用递归来做。规律是：
一部分是n-1位格雷码，再加上 1<<(n-1)和n-1位格雷码的逆序的和。也就是说n位的格雷码有一半是n-1位格雷码只是前面多加一个1，另一半
是将前n-1位格雷码逆序，然后最前面加上1.
*/
public class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<Integer>();
        // 0位的格雷码
        if (n == 0){
            result.add(0);
            return result;
        }

        // n位的格雷码有一半为n-1位格雷码
        result = grayCode(n - 1);
        // 然后将这一半格雷码逆序输出并最前面加1
        for (int i = result.size() - 1; i >= 0; i--){
            int num = result.get(i);
            num += 1 << (n - 1);
            result.add(num);
        }

        return result;
    }
}
