/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].
*/
// Tag: Backtracking
// http://www.cnblogs.com/yuzhangcmu/p/4141085.html

public class Solution {
    public List<List<Integer>> permuteUnique(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0){
            return result;
        }

        List<Integer> path = new ArrayList<Integer>();
        Arrays.sort(num);
        boolean[] visited = new boolean[num.length];

        helper(result, path, visited, num);
        return result;
    }

    public void helper(List<List<Integer>> result, List<Integer> path, boolean[] visited, int[] num){
        if (path.size() == num.length){
            result.add(new ArrayList<Integer>(path));
            return;
        }

        for (int i = 0; i < num.length; i++){
            // 如果之前已经访问过，或者之前的一个被访问过且和当前的值一样则跳过，只能连续的选。
            // 例如1，2，2，4，4，4，5中的三个4，如果不跳过会有三个重复的结果出现，在这里只能4，44，444这样选
            // 为此要先将num排序
            if (visited[i] || i != 0 && visited[i - 1] && num[i] == num[i - 1]){
                continue;
            }

            visited[i] = true;
            path.add(num[i]);
            helper(result, path, visited, num);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }
}
