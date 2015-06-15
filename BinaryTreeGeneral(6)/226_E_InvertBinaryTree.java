/*
Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9
to
     4
   /   \
  7     2
 / \   / \
9   6 3   1
*/
// Tag: Tree

// Solution1: Recursion
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
    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return null;
        }

        helper(root);
        return root;
    }

    private void helper(TreeNode root){
        if (root == null){
            return;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        if (root.left != null){
            helper(root.left);
        }
        if (root.right != null){
            helper(root.right);
        }
    }
}

// Solution2: Iteration
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
    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (node.left!= null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }

            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }

        return root;
    }
}
