/*
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8,
A solution set is:
[1, 7]
[1, 2, 5]
[2, 6]
[1, 1, 6]
*/
// Tag: Array, Backtracking

public class Solution {
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        int sum = 0;
        Arrays.sort(num);
        sum2Helper(result, path, num, target, 0, 0);
        return result;
    }

    public void sum2Helper(List<List<Integer>> result, List<Integer> path, int[] num, int target, int pos, int sum){
        if (sum == target && !result.contains(path)){ // 或者去掉!result.contains(path)
            result.add(new ArrayList<Integer>(path));
            return;
        }

        if (sum < target){
            for (int i = pos; i < num.length; i++){
                // 在这里加判断条件，将三行语句放到判断条件中
                // if (i > 0 && i != pos && num[i] == num[i - 1]){continue;}
                // i != pos 证明已经是回溯到这个状态，因此通过后面可以跳过重复的combination，Unique
                path.add(num[i]);
                sum2Helper(result, path, num, target, i + 1, sum + num[i]);
                path.remove(path.size() - 1);
            }
        }
    }
}
