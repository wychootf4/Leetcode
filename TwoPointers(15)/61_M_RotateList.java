/*
Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
*/
// Tag: Linked List, Two Pointers

/*
分析：
首先计算链表的长度，然后将k取模，从dummy node开始head向后先走k步，tail初始指向dummy。这样head实际少走了一步，而head和tail一起向后走，
直到head到达队尾。此时tail指向的是新的队尾，tail的next是新的表头，head是老表头的pre。
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0){
            return head;
        }

        int len = getLen(head);
        k = k % len;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        ListNode tail = dummy;

        for (int i = 0; i < k; i++){
            head = head.next;
        }

        while (head.next != null){
            head = head.next;
            tail = tail.next;
        }

        head.next = dummy.next;
        dummy.next = tail.next;
        tail.next = null;

        return dummy.next;
    }

    private int getLen(ListNode head){
        int len = 0;
        while (head != null){
            head = head.next;
            len++;
        }

        return len;
    }
}
