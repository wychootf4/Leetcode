/*
Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate(填入) each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
*/
// Tag: Tree, DFS

/*
分析：
维护两个指针，一个指针指向每层最左的节点，另一个遍历连接下层的节点，下层连接完成后第一个指针指向当前的左孩子
*/
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
 public class Solution {
     public void connect(TreeLinkNode root) {
         if (root == null){
             return;
         }

         TreeLinkNode leftMost = root;
         // 遍历每层
         while (leftMost != null && leftMost.left != null){
             // 连接下一层
             TreeLinkNode curr = leftMost;
             while (curr != null){
                 curr.left.next = curr.right;
                 curr.right.next = (curr.next == null) ? null : curr.next.left;
                 // 继续连接下一个点对应的下层节点
                 curr = curr.next;
             }
             // 最左节点指向当前的左孩子
             leftMost = leftMost.left;
         }
     }
 }
