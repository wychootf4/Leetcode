/*
Given a linked list, remove the nth node from the end of list and return its head.

For example,

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
*/
// Tag: Linked List, Two Pointers

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n <= 0){
            return null;
        }

        // 加入dummy node防止要删除的点是第一个节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 要删掉某一个点要先找到它前面的一个点，所以slow初始在dummy而fast初始在dummy.next
        ListNode slow = dummy;
        ListNode fast = head;

        // 初始slow和fast差一步，再让fast多走出n步，这样能保证slow走到最后是倒数第n个点的前继节点
        for (int i = 0; i < n; i++){
            if (fast == null){
                return null;
            }
            fast = fast.next;
        }

        while (fast != null){
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }
}
