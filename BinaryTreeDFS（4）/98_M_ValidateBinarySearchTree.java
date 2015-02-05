/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.


OJ's Binary Tree Serialization:
The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.

Here's an example:
   1
  / \
 2   3
    /
   4
    \
     5
The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
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
 // http://www.cnblogs.com/yuzhangcmu/p/4177047.html 里面有四种解法
public class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null){
            return true;
        }
        return validBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean validBST(TreeNode root, long min, long max){
        // 如果根或者叶子结点的left或right为空，则此子树是BST，返回true
        if (root == null){
            return true;
        }
        // 对于当前根，如果其是父节点的左孩子，其最大值不应该超过父节点的值，即如果root.left.val >= root.val
        // 则证明当前左孩子这一枝与父节点比较不是BST，返回false给父节点这层的最后返回值：validBST(root.left,
        // min, root.val)，右孩子同理
        if (root.val <= min || root.val >= max){
            return false;
        }
        // 递归判断当前根的左右孩子，左孩子最大值不超过根，右孩子最小值不小于根
        return validBST(root.left, min, root.val) && validBST(root.right, root.val, max);
    }
}
