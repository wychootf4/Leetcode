/*
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
*/
// Tag: Linked List

/**
* Definition for singly-linked list.
* class ListNode {
*     int val;
*     ListNode next;
*     ListNode(int x) {
*         val = x;
*         next = null;
*     }
* }
*/
// 思路是找到中点，后面的翻转，然后根据单双合并
public class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null){
            return;
        }

        ListNode mid = findMiddle(head);
        ListNode tail = reverse(mid.next);
        mid.next = null;

        merge(head, tail);
    }

    private ListNode findMiddle(ListNode head){
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private ListNode reverse(ListNode head){
        ListNode newHead = null;

        while (head != null){
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }

        return newHead;
    }

    private void merge(ListNode head1, ListNode head2){
        int index = 0;
        ListNode dummy = new ListNode(0);

        while (head1 != null && head2 != null){
            if (index % 2 == 0){
                dummy.next = head1;
                head1 = head1.next;
            }else{
                dummy.next = head2;
                head2 = head2.next;
            }
            index++;
            dummy = dummy.next;
        }

        if (head1 != null){
            dummy.next = head1;
        }else{
            dummy.next = head2;
        }
    }
}
