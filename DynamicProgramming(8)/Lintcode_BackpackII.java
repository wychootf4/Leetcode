/*
Given n items with size A[i] and value V[i], and a backpack with size m. What's the maximum value can you put into
the backpack?

Note
You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.

Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 9.
*/
// Tag: Dynamic Programming

/*
分析：
n个整数a[1..n],所对应的价值V[1...n],装容积为m的背包
state: f[i]代表容量为i的背包能取得的最大value
function: f[j] = f[j - a[i]] + V[i]  // 放第i个物品，j代表还剩余能装的容积
               = f[j]                // 不放第i个物品，即a[i] > j，装不下了
intialize: none
answer: f[m]
*/

public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int V[]) {
        // write your code here
        int[] value = new int[m + 1];

        for (int i = 0; i < A.length; i++){
            for (int j = m; j >= A[i]; j--){
                value[j] = Math.max(value[j], value[j - A[i]] + V[i]);
            }
        }

        return value[m];
    }
}
