/*
Given a binary tree, return the level order traversal of its nodes' values. (ie,
from left to right, level by level).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.


OJ's Binary Tree Serialization:
The serialization of a binary tree follows a level order traversal, where '#' signifies
a path terminator where no node exists below.

Here's an example:
   1
  / \
 2   3
    /
   4
    \
     5
The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
*/
// Tag: Tree, BFS

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 虽然是用了双重循环但是整体时间复杂度是O(n)，因为所有结点都要进栈出栈一次
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (root == null){
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()){
            // 存每层的结点
            List<Integer> level = new ArrayList<Integer>();
            // 当前层结点的个数就是当前queue的长度,这里要提前把当前层的size记录一下，否则for循环里
            // 加的就不是本层的结点而是所有结点了。这样while循环到下次时更新的size是新一层的长度
            int size = queue.size();
            for (int i = 0; i < size; i++){
                TreeNode head = queue.poll();
                level.add(head.val);
                if (head.left != null){
                    queue.offer(head.left);
                }
                if (head.right != null){
                    queue.offer(head.right);
                }
            }
            result.add(level);
        }
        return result;
    }
}
