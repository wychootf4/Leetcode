/*
You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order
and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
*/
// Tag: Math, Linked List

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null){
            return null;
        }
        // 设立一个dummy node便于处理头节点，并且最后返回dummy node的next即可
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        int carry = 0;
        // 如果carry == 1证明当前位需要补上进位
        while (l1 != null || l2 != null || carry == 1){
            int sum = carry;
            if (l1 != null){
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null){
                sum += l2.val;
                l2 = l2.next;
            }
            // 将当前位加入链表
            carry = sum / 10;
            ListNode curr = new ListNode(sum % 10);
            head.next = curr;
            head = head.next;
        }

        return dummy.next;
    }
}
