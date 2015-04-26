/*
Given 2*n + 2 numbers, every numbers occurs twice except two, find them.

Example
Given [1,2,2,3,4,4,5,3] return 1 and 5

Challenge
O(n) time, O(1) extra space.
*/
// Tag: Greedy

/*
分析：
a和b是不同的数，所以a ^ b != 0，因此a和b至少有一位不同，一个是0，一个是1。
想求a和b可以将2n + 2拆成2n' + 1和2n'' + 1。
*/

public class Solution {
    /**
     * @param A : An integer array
     * @return : Two integers
     */
    public List<Integer> singleNumberIII(int[] A) {
        // write your code here
        List<Integer> result = new ArrayList<Integer>();
        if (A == null || A.length == 0){
            return result;
        }

        // 第一步先求出所有数做异或的结果
        int res = 0;
        for (int i : A){
            res ^= i;
        }
        // 找出右边第一位是1的，只有那两个不同的数做异或才会导致这一位为1。
        res = res & ~(res - 1);
        int xorA = 0;
        int xorB = 0;
        // 某一个数i与res做与操作不等于0说明这个数i在那一位也为1，则分到xorA组中做异或，反之分到xorB。
        for (int i : A){
            if ((res & i) != 0){
                xorA ^= i;
            }else{
                xorB ^= i;
            }
        }

        result.add(xorA);
        result.add(xorB);

        return result;
    }
}
