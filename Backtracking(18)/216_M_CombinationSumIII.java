/*
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can
be used and each combination should be a unique set of numbers.

Ensure that numbers within the set are sorted in ascending order.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]

*/
// Tag: Array, Backtracking
public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        helper(result, path, k, n, 1);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> path, int k, int sum, int pos){
        if (sum == 0 && path.size() == k){
            result.add(new ArrayList<Integer>(path));
        }
        // 如果当前元素比剩余的sum要大或者已经超过k个数了则break，否则递归的去做
        for (int i = pos; i <= 9; i++){
            if (sum < i || path.size() > k){
                break;
            }
            path.add(i);
            helper(result, path, k, sum - i, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
