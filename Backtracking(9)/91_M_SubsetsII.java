/*
Given a collection of integers that might contain duplicates, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/
// Tag: Array, Backtracking

public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        Arrays.sort(num);
        subsetsWithDupHelper(result, path, num, 0);
        return result;
    }

    public void subsetsWithDupHelper(List<List<Integer>> result, List<Integer> path, int[] num, int pos){
        result.add(new ArrayList<Integer>(path));

        for (int i = pos; i < num.length; i++){
            // 通过if判断条件跳过重复字符：
            // (1) i > 0：避免输入pos小于0
            // (2) i != pos && num[i] == num[i - 1]：如果有重复的才跳过，i = pos表示取第一个重复的相同字符
            if (i > 0 && i != pos && num[i] == num[i - 1]){
                continue;
            }
            path.add(num[i]);
            subsetsWithDupHelper(result, path, num, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
