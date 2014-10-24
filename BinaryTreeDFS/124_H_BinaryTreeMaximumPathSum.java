/*
Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
*/
// Tag: Tree, DFS

// 思路：从根开始想，此path有可能完全在左子树或右子树中，还有可能越过根。
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int maxPathSum(TreeNode root) {
        ResultType result = helper(root);
        return result.maxPath;
    }
    // 用一个ResultType封装两个要返回的参数
    private class ResultType{
        int singlePath, maxPath;
        ResultType(int singlePath, int maxPath){
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    }

    public ResultType helper(TreeNode root){
        if (root == null){
            return new ResultType(0, Integer.MIN_VALUE);
        }
        // Divide,先按照左右两边往下分
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        // Conquer，对于某个子树，最大单边可能是左单边或者右单边加根，也可能是0，因为有可能单边中有
        // 结点是负数，还不如不取单边
        int singlePath = Math.max(left.singlePath, right.singlePath) + root.val;
        singlePath = Math.max(singlePath, 0);
        // maxPath可能取左边最大，右边最大，也可能越过根
        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath, left.singlePath + right.singlePath + root.val);

        return new ResultType(singlePath, maxPath);
    }
}
