/*
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than
or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
*/
// Tag: Linked List, Two Pointers

// 题目刚开始没看明白，说的是从左向右遍历，所有比x小的点都在大于等于x点的左边，不改变在原序列中的顺序。如例题中122是小于3的，435是大于等于
// 3的，此顺序与原序列顺序一致

/**
* Definition for singly-linked list.
* public class ListNode {
*     int val;
*     ListNode next;
*     ListNode(int x) {
*         val = x;
*         next = null;
*     }
* }
*/
public class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null){
            return head;
        }

        // 用两个链表分别记录比x小和比x大的结点
        // 用leftdummy和rightdummy记录两个子链表的头位置，用left和right记录相应的尾位置
        ListNode leftDummy = new ListNode(0);
        ListNode rightDummy = new ListNode(0);
        ListNode left = leftDummy;
        ListNode right = rightDummy;

        while (head != null){
            if (head.val < x){
                // 当前尾指针的next赋值为head，然后把尾指针挪到末尾
                left.next = head;
                left = head;
            }else{
                right.next = head;
                right = head;
            }
            // head向后挪一位
            head = head.next;
        }

        // 避免最后right后面指向错误信息，置为null
        right.next = null;
        left.next = rightDummy.next;

        return leftDummy.next;
    }
}
