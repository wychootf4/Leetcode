/*
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7,
A solution set is:
[7]
[2, 2, 3]
*/

public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        int sum = 0;
        Arrays.sort(candidates);
        sumHelper(result, path, candidates, target, 0, 0);
        return result;
    }

    public void sumHelper(List<List<Integer>> result, List<Integer> path, int[] candidates, int target, int pos, int sum){
        if (sum == target){
            // new保证不会有多余的空集被加进去
            result.add(new ArrayList<Integer>(path));
        }
        // 判断条件保证不会无限求和下去寻找target
        if (sum < target){
            for (int i = pos; i < candidates.length; i++){
                path.add(candidates[i]);
                sumHelper(result, path, candidates, target, i, sum + candidates[i]);
                path.remove(path.size() - 1);
            }
        }
    }

}
