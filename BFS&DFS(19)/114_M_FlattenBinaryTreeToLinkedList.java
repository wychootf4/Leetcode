/*
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6

Hints:
If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order
traversal.
*/
// Tag: Tree, DFS

/*
分析：
实际上就是preorder traversal的过程，使用递归解决，根据left是否为空，先连接left tree, 然后再连接右子树。使用一个tail来记录链的结尾。
在递归之前，先将root.left,root.right保存下来。
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
    public void flatten(TreeNode root) {
        dfs(root);
    }

    private TreeNode dfs(TreeNode root){
        // base case
        if (root == null){
            return null;
        }

        // 保存根的左右孩子
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 初始化根节点
        root.left = null;
        root.right = null;

        // 用一个tail保存链尾
        TreeNode tail = root;

        // 连接左子树
        if (left != null){
            tail.right = left;
            tail = dfs(left);
        }
        // 连接右子树
        if (right != null){
            tail.right = right;
            tail = dfs(right);
        }

        return tail;
    }
}
