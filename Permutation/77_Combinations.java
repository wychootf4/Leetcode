/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/

public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        combineHelper(result, path, n, k, 1); // 让pos从1开始以便取到初始i = 1
        return result;
    }

    public void combineHelper(List<List<Integer>> result, List<Integer> path, int n, int k, int pos){
        // 如果经过外层递归后path长度满足要取出的数字个数k则将结果输出
        if (path.size() == k){
            result.add(new ArrayList<Integer>(path));
            return;
        }
        // 循环检查从1到n
        for (int i = pos; i < n + 1; i++){
            path.add(i);
            combineHelper(result, path, n, k, i + 1);
            path.remove(path.size() - 1);
        }
    }

}
