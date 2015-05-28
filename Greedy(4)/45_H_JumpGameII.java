/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to
the last index.)
*/
// Tag: Array, Greedy

// Solution1: DP(TBL)
// 如果需要输出具体方案，另外新建一个数组存储步骤，再逆序打印
public class Solution {
    public int jump(int[] A) {
        int[] steps = new int[A.length];
        // 输出具体方案step1：List<Integer> path = new ArrayList<Integer>();

        steps[0] = 0;
        // 往前走每一步时循环看i后面的是否有可达的，如果可达就更新当前位的步数。如果已经更新了就可以直接跳出二层循环，
        // 因为后面的步数肯定是得从当前允许更新的位置向后跳才能达到，不可能总步数比当前解更优
        for (int i = 1; i < A.length; i++) {
            steps[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (steps[j] != Integer.MAX_VALUE && j + A[j] >= i) {
                    steps[i] = steps[j] + 1;
                    // 输出具体方案step2：pre[i] = j; 表明第i个点的最优解是从j跳过来的，对轨迹进行记录
                    break;
                }
            }
        }

        /*
        输出具体方案step3：
        int i = A.length - 1;
        while (i != 0){
            path.add(0, i);
            i = pre[i];
        }
        path.add(0, 0);
        */

        return steps[A.length - 1];
    }
}

// Solution2: Greedy
/*
分析：维护两个指针用贪心法，start和end每次循环去扫边界，如果最右能够到达更远的位置，将start置为原end的后面一位，end置为新的更远位置。
一直向后扫直到end到达数组末尾。
从左往右扫描，维护一个覆盖区间，每扫过一个元素，就重新计算覆盖区间的边界。比如，开始时区间[start, end], 遍历A数组的过程中，
不断计算A[i]+i最大值（即从i坐标开始最大的覆盖坐标），并设置这个最大覆盖坐标为新的end边界。而新的start边界则为原end+1。不断循环，直到end>n.
*/

public class Solution {
    public int jump(int[] A) {
        if (A == null || A.length == 0){
            return -1;
        }

        int start = 0;
        int end = 0;
        int jumps = 0;

        while (end < A.length - 1){
            int rightmost = end;
            jumps++;

            for (int i = start; i <= end; i++){
                if (A[i] + i > rightmost){
                    rightmost = A[i] + i;
                }
            }

            start = end + 1;
            end = rightmost;
        }

        return jumps;
    }
}
