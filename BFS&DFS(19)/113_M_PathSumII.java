/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
*/
// Tag: Tree, DFS

/*
分析：
使用递归解决，如果只考虑加入当前节点，会更加简单易理解。递归的base case就是：
1. 当null的时候返回。
2. 当前节点是叶子 并且sum与root的值相同，则增加一个可能的解。
3. 如果没有解，将sum 减掉当前root的值，并且向左树，右树递归即可。
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        if (root == null){
            return result;
        }

        helper(result, path, root, sum);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> path, TreeNode root, int sum){
        // base case
        if (root == null){
            return;
        }

        path.add(root.val);
        sum -= root.val;
        // 如果sum已经减到0且当前节点为叶子节点则证明已经找到一组解，加入结果，否则继续遍历左右子树
        if (sum == 0 && root.left == null && root.right == null){
            result.add(new ArrayList<Integer>(path));
        }else{
            helper(result, path, root.left, sum);
            helper(result, path, root.right, sum);
        }

        // backtracking
        path.remove(path.size() - 1);
    }
}
