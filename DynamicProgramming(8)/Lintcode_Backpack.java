/*
Given n items with size A[i], an integer m denotes the size of a backpack. How full you can fill this backpack?

Note
You can not divide any item into small pieces.

Example
If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select 2, 3 and 5, so that the max size
we can fill this backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the
backpack.

You function should return the max size we can fill in the given backpack.
*/
// Tag: Dynamic Programming

/*
分析：
n个整数a[1..n],装容积为m的背包
state: f[i] 容量为i的背包能取得的最大容积
function: f[j] = f[j - a[i]] + a[i]  // 放第i个物品，j代表还剩余能装的容积
               = f[j]                // 不放第i个物品，即a[i] > j，装不下了
intialize: none
answer: f[m]
*/

public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        // write your code here
        int[] value = new int[m+1];
        // i代表第几件物品
        for (int i = 0; i < A.length; i++) {
            // j代表所剩能装的容积
            for (int j = m; j >= A[i]; j--) {
                value[j] = Math.max(value[j], value[j - A[i]] + A[i]);
            }
        }

        return value[m];
    }
}
