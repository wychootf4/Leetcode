/*
Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the
same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned
into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1
*/
// Tag: Tree

/*
通过观察可以发现当前节点p.left = parent.right, p.right = parent,即当前节点的左孩子是其右sibling，而右孩子是父节点。
解法类似于链表的逆向
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
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null){
            return null;
        }

        TreeNode curr = root;
        // 由于curr的新left是parent.right，新right是parent，所以需要记录下来再替换
        TreeNode parent = null;
        TreeNode parentRight = null;
        while (curr != null){
            TreeNode left = curr.left;
            curr.left = parentRight;
            parentRight = curr.right;
            curr.right = parent;
            parent = curr;
            curr = left;
        }

        return parent;
    }
}
