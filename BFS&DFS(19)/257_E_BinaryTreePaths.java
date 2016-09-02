/*
Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]
*/
// Company: Google Apple Facebook
// Tag: Tree, DFS

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 /*
 思路：利用DFS，递归退出条件为当前root为null，证明到达了叶子节点的下面一层，return
 而如果当前root的左右孩子都为null则为叶子节点，可以将path加入result中
 如果前两个条件都不满足继续divide and conquer
 */
public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<String>();
        if (root == null) {
            return result;
        }
        // bug1: missing Strong.valueOf, wrong type; should have initial value with root.val
        String path = String.valueOf(root.val);

        helper(root, path, result);

        return result;
    }

    private void helper(TreeNode root, String path, List<String> result) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            result.add(path);
            return;
        }

        if (root.left != null) {
          // 注意这里直接将path的变化加到参数中传到下一层，这样从下一层return时自然
          // 就backtracking了，逻辑性更清楚
          // bug2: wrong parameter, should passing root.left.val, not root.val
            helper(root.left, path + "->" + root.left.val, result);
        }
        if (root.right != null) {
            helper(root.right, path + "->" + root.right.val, result);
        }
    }
}
