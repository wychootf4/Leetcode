/*
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
*/
// Tag: Tree, Array, DFS

/*
分析：
使用递归的思想，先找到根节点（它就是post order最后一个），然后再在inorder中找到它，以确定左子树的node个数。
然后分别确定左子树右子树的左右边界
例子：
{4， 5， 2， 7， 8， 1， 3}这树的
inorder: 7 5 8 | 4 | 1 2 3
post: 7 8 5 | 1 3 2 | 4
以上我们可以看到左右子树的划分关系。
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length == 0 || postorder.length == 0 || inorder.length != postorder             .length){
            return null;
        }

        return helper(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode helper(int[] inorder, int[] postorder, int inStart, int inEnd, int postStart, int postEnd){
        if (inStart > inEnd){
            return null;
        }

        TreeNode root = new TreeNode(postorder[postEnd]);
        int position = findPosition(inorder, inStart, inEnd, postorder[postEnd]);
        int leftNum = position - inStart;
        root.left = helper(inorder, postorder, inStart, position - 1, postStart, postStart + leftNum - 1);
        root.right = helper(inorder, postorder, position + 1, inEnd, postStart + leftNum, postEnd - 1);

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
