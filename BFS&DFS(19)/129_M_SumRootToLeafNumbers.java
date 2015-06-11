/*
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.
*/
// Tag: Tree, DFS

/*
分析：
使用递归完成，将当前节点的左右子树path加起来。
如果为叶子节点返回当前path的值，否则递归求其左右子树path的和返回。用pre保存截止上一层的和，那么到了当前节点的和就是当前节点的值加上
pre * 10
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
    public int sumNumbers(TreeNode root) {
        if (root == null){
            return 0;
        }
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int prev){
        if (root == null){
            return 0;
        }

        int curr = root.val + prev * 10;
        if (root.left == null && root.right == null){
            return curr;
        }

        return dfs(root.left, curr) + dfs(root.right, curr);
    }
}
