/*
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
*/
// Tag: Tree, Array, DFS

/*
分析:
先根据先序遍历找到树的根，然后找到根在中序遍历中的位置，再递归求根的左子树和右子树。
例子：

Pre:     4 2 1 3 6 5 7

Inorder: 1 2 3 4 5 6 7
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0 || preorder.length != inorder                .length){
            return null;
        }

        return helper(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode helper(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd){
        // base case
        if (preStart > preEnd){
            return null;
        }

        // 根为preorder的第一个元素
        TreeNode root = new TreeNode(preorder[preStart]);
        // 根据根找到其在inorder中的位置
        int position = findPosition(inorder, inStart, inEnd, preorder[preStart]);
        // 递归求出根的左右子树
        int leftNum = position - inStart;
        root.left = helper(preorder, inorder, preStart + 1, preStart + leftNum, inStart, position - 1);
        root.right = helper(preorder, inorder, preStart + leftNum + 1, preEnd, position + 1, inEnd);

        return root;
    }

    private int findPosition(int[] inorder, int start, int end, int key){
        for (int i = start; i <= end; i++){
            if (inorder[i] == key){
                return i;
            }
        }

        return -1;
    }
}
