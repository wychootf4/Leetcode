/*
Given a set of distinct integers, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/
// 模板原题，注释参照模板
// Tag: Array, Backtracking, Bit Manipulation

public class Solution {
    public List<List<Integer>> subsets(int[] S) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        Arrays.sort(S);
        subsetsHelper(result, path, S, 0);
        return result;
    }

    public void subsetsHelper(List<List<Integer>> result, List<Integer> path, int[] S, int pos){
        result.add(new ArrayList<Integer>(path)); // 每次初始化path避免有重复空集出现

        for (int i = pos; i < S.length; i++){
            path.add(S[i]);
            subsetsHelper(result, path, S, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
