/*
Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
*/
// Tag: Backtracking

public class Solution {
    public List<List<Integer>> permute(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        helper(result, path, num);
        return result;
    }

    public void helper(List<List<Integer>> result, List<Integer> path, int[] num){
        if (path.size() == num.length){
            result.add(new ArrayList<Integer>(path));
            return;  
        }

        for (int i = 0; i < num.length; i++){
            // 由于是排列，每次从头开始，如果遇到相同的字符就跳过
            if (path.contains(num[i])){
                continue;
            }
            path.add(num[i]);
            helper(result, path, num);
            path.remove(path.size() - 1);
        }
    }
}
