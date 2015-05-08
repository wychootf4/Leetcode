/*
Reverse a singly linked list.

click to show more hints.

Hint:
A linked list can be reversed either iteratively or recursively. Could you implement both?
*/
// Tag: Linked List

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null){
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = head;
        ListNode pre = dummy;

        while (head != null){
            ListNode next = head.next;
            if (pre == dummy){
                head.next = null;
            }else{
                head.next = pre;
            }
            pre = head;
            head = next;
        }

        return pre;
    }
}
