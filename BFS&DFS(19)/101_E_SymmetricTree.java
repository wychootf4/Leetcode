/*
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.
*/
// Tag: Tree, DFS

// Solution1: Recursion
// 根必须同时为空或者同时不为空，同时不为空时比较根的值以及左根左孩子与右根右孩子，左根右孩子与右根左孩子
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
    public boolean isSymmetric(TreeNode root) {
        if (root == null){
            return true;
        }

        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode r1, TreeNode r2){
        if (r1 == null && r2 == null){
            return true;
        }
        if (r1 == null || r2 == null){
            return false;
        }

        return r1.val == r2.val && isMirror(r1.left, r2.right) && isMirror(r1.right, r2.left);
    }
}

// Solution2: Iteration
// 维护两个栈，两个根同时不为空则入栈，然后左根左孩子与右根右孩子同时不为空入栈，左根右孩子与右根左孩子同时不为空入栈
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
    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)){
            return true;
        }
        if (root.left == null || root.right == null){
            return false;
        }

        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();
        s1.push(root.left);
        s2.push(root.right);

        while (!s1.isEmpty() && !s2.isEmpty()){
            TreeNode r1 = s1.pop();
            TreeNode r2 = s2.pop();

            if (r1.val != r2.val){
                return false;
            }

            if (r1.left != null && r2.right != null){
                s1.push(r1.left);
                s2.push(r2.right);
            }else if (!(r1.left == null && r2.right == null)){
                return false;
            }

            if (r1.right != null && r2.left != null){
                s1.push(r1.right);
                s2.push(r2.left);
            }else if (!(r1.right == null && r2.left == null)){
                return false;
            }
        }

        return true;
    }
}
