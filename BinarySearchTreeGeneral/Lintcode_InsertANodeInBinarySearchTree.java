/*
Given a binary search tree  and a new tree node, insert the node into the tree. You should keep the tree still be a valid binary search tree.

Example
Given binary search tree as follow:

          2
         / \
        1   4
           /
          3

after Insert node 6, the tree should be:

          2
         / \
        1   4
           / \
          3   6

Challenge
Do it without recursion
*/

每次将待插入的点与当前根进行比较，大的往右边走，小的往左边走,最后找到空的位置插入元素
public class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;
    TreeNode(int x){
        this.val = x;
    }
}

public class Solution {
    public void insertNode(TreeNode root, int target){
        if (root == null){
            root = new TreeNode(target);
        }else{
            if (root.val > target){
                root.left = insertNode(root.left, target);
            }else{
                root.right = insertNode(root.right, target);
            }
        }
    }
}
