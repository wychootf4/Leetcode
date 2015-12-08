/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node
down to the nearest leaf node.
*/
// Tag: Tree, DFS

// Solution1: 我是用BFS做的
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
    public int minDepth(TreeNode root) {
        if (root == null){
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int counter = 1;

        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                TreeNode head = queue.poll();
                // 如果当前层某个结点为叶节点，则返回counter
                if (head.left == null && head.right == null){
                    return counter;
                }
                if (head.left != null){
                    queue.offer(head.left);
                }
                if (head.right != null){
                    queue.offer(head.right);
                }
            }
            counter++;
        }
        
        return counter;
    }
}

// Solution 2: DFS
public class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getMin(root);
    }

    public int getMin(TreeNode root){
        // 如果从上层递归下来，root没有左孩子或者右孩子，则将左孩子或者右孩子的值置为最大整数保证不会被取到
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        // 如果当前点为叶节点，返回1个depth
        if (root.left == null && root.right == null) {
            return 1;
        }
        // 返回1个depth后，还要加上当前的root，所以加1
        return Math.min(getMin(root.left), getMin(root.right)) + 1;
    }
}
