/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
*/
// Tag: DFS, Linked List

// 递归的方法做，找到中点，左边的为左子树，右边的为右子树，时间复杂度为O（nlogn）。每次的取中点时间复杂度为O（n），共取logn次。但是这里
// 可以省去找中点的过程，使得整体的时间复杂度降到O（n）
/**
* Definition for singly-linked list.
* public class ListNode {
*     int val;
*     ListNode next;
*     ListNode(int x) { val = x; next = null; }
* }
*/
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
    private ListNode current;

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null){
            return null;
        }

        current = head;
        int size = getListLength(head);

        return sortedListToBSTHelper(size);
    }

    private int getListLength(ListNode head){
        int size = 0;

        while (head != null){
            size++;
            head = head.next;
        }

        return size;
    }

    public TreeNode sortedListToBSTHelper(int size){
        if (size <= 0){
            return null;
        }

        // 递归到最左最下，root是当前的current，然后一层一层向上返回
        TreeNode left = sortedListToBSTHelper(size / 2);
        TreeNode root = new TreeNode(current.val);
        current = current.next;
        // 右子树剩下的点是总的节点数size - 根节点 - 左子树节点的个数
        TreeNode right = sortedListToBSTHelper(size - 1 - size / 2);

        root.left = left;
        root.right = right;

        return root;
    }
}
