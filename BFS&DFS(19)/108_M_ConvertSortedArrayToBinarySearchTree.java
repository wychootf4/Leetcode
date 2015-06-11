/*
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
*/
// Tag: Tree, DFS

/*
分析：
使用递归解决。
base case: 当索引值超过时，返回null.
递归主体：构造根节点，调用递归构造左右子树。并将左右子树挂在根节点上。
返回值：返回构造的根节点。
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
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null){
            return null;
        }

        return buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int start, int end){
        if (start > end){
            return null;
        }

        TreeNode root = new TreeNode(nums[(start + end) / 2]);
        root.left = buildTree(nums, start, (start + end) / 2 - 1);
        root.right = buildTree(nums, (start + end) / 2 + 1, end);

        return root;
    }
}
