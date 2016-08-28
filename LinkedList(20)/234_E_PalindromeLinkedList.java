/*
Given a singly linked list, determine if it is a palindrome.

Follow up:
Could you do it in O(n) time and O(1) space?
*/
// Tag: Linked List, Two Pointers

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 /*
 思路：首先找到中心点，然后将中心点之后的链表倒序，之后比较两部分链表的元素
 时间复杂度是n，空间复杂度是1
 */
public class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode middle = findMiddle(head);
        middle.next = reverse(middle.next);
        return check(head, middle.next);
    }
    // 找到的中心点若为偶数则为靠前的一个
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
    // reverse的基本操作，设前置节点，直接最后head指向末尾的null，返回prev
    private ListNode reverse(ListNode head) {
        ListNode prev = null;

        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }

        return prev;
    }

    private boolean check(ListNode p1, ListNode p2) {
        while (p1 != null && p2 != null && p1.val == p2.val) {
            p1 = p1.next;
            p2 = p2.next;
        }
        // 保证比较两段链表最后一个元素的前提下，若是偶数个元素则比较最后元素，若是奇数个元素
        // 由于中间点在第一段，因此此时p2所对应的也应该是null
        return p2 == null;
    }
}
