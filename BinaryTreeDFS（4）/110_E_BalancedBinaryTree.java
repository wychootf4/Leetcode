/*
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree
in which the depth of the two subtrees of every node never differ by more than 1.
*/
// Tag: Tree, DFS

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 // 判断左子树和右子树是否是平衡的，即计算左右子树的高度，然后判断，是就返回高度，不是就返回-1
 // 如果不想要返回-1的方法判断，可以自己创建一个返回类型，里面有boolean判断是否是平衡还有int返回最大高度
public class Solution {
    public boolean isBalanced(TreeNode root) {
        return maxDepth(root) != -1;
    }
    // 判断当前点的左右子树高度，如果不符合条件return -1
    public int maxDepth(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        // left或者right = -1 说明当前root的左子树或者右子树不符合条件，直接往外层返回-1
        if (left == -1 || right == -1 || Math.abs(left - right) > 1){
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}
// 时间复杂度是O（n），因为最坏情况下每个结点被遍历一次，一共有n个结点
