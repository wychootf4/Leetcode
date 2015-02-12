/*
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root
node down to the farthest leaf node.
*/
// Tag: Tree, DFS

// Recursion: Divide & Conquer
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 // 分治的思想就是把大问题拆成小问题，这里要求整棵树的最大深度，就看和左右子树的关系，即左右子树最大深度+1
public class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
}

// Recursion: Traverse
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
    int max;
    public int maxDepth(TreeNode root) {
        max = 0;
        traverse(root, 1);
        return max;
    }

    public void traverse(TreeNode root, int depth){
        if (root == null){
            return;
        }
        if (depth > max){
            max = depth;
        }
        traverse(root.left, depth + 1);
        traverse(root.right, depth + 1);
    }
}
