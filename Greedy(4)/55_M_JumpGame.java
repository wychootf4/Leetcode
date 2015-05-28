/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.
*/
// Tag: Array, Greedy

// Solution1: DP
// 但是现在TLE
// 优化的点1：既然某点可达，肯定前面的点全部是可达的。这个比较好理解。因为你到达i点肯定要经过前面的一个点，这个依次推知可知前面所有的点可达。
// 所以我们不需要数组来记录结果，只要默认i点前全部可达就行了。
// 优化点2：如果某点不可达了，直接返回false。不需要再往后计算。
public class Solution {
    public boolean canJump(int[] A) {
        if (A == null || A.length == 0) {
            return false;
        }

        int len = A.length;

        for (int i = 1; i < len; i++) {
            boolean can = false;
            for (int j = 0; j < i; j++) {
                // j can arrive and can jump to i.
                if (j + A[j] >= i) {
                    // 说明i是可达的，置标记位
                    can = true;
                    break;
                }
            }

            // 优化:如果某一步已经到不了了，后面的也不必再计算了.
            if (!can) {
                return false;
            }
        }

        return true;
    }
}

// Solution2: Greedy
public class Solution {
    /**
     * @param A: A list of integers
     * @return: The boolean answer
     */
    public boolean canJump(int[] A) {
        // wirte your code here
        if (A == null || A.length == 0){
            return false;
        }
        // 能到达的最右的位置
        int rightMost = 0;

        for (int i = 0; i < A.length; i++){
            // 每次循环最右指针取本身（不能再往前走）或者从当前位置能向前跳的最大位置
            rightMost = Math.max(rightMost, i + A[i]);
            // 如果最右能到达末尾返回true
            if (rightMost == A.length - 1){
                return true;
            }
            // 证明走到当前位置i时就是最右位置，不能再向前跳
            if (i == rightMost){
                return false;
            }
        }

        return true;
    }
}
