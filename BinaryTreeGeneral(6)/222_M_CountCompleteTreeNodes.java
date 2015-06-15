/*
Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the
last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
*/
// Tag: Tree, Binary Search

/*
分析：
首先得到最左路和最右路的高度，如果高度相等直接返回2^h - 1,否则递归去求左子树和右子树的节点个数，时间复杂度是O(h^2)
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
    public int countNodes(TreeNode root) {
        if (root == null){
            return 0;
        }

        int left = getLeftHeight(root);
        int right = getRightHeight(root);

        if (left == right){
            return (2 << left) - 1;
        }else{
            return countNodes(root.left) + countNodes(root.right) + 1;
        }
    }

    private int getLeftHeight(TreeNode node){
        if (node == null){
            return 0;
        }
        int height = 0;
        while (node.left != null){
            height++;
            node = node.left;
        }
        return height;
    }

    private int getRightHeight(TreeNode node){
        if (node == null){
            return 0;
        }
        int height = 0;
        while (node.right != null){
            height++;
            node = node.right;
        }
        return height;
    }
}
