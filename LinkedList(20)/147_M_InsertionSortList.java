/*
Sort a linked list using insertion sort.
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
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }

        ListNode dummy = new ListNode(-1);

        while (head != null){
            ListNode pre = dummy;
            // 每次循环重置pre，排除所有比当前head小的点后pre即为当前待插入点的前置节点
            while (pre.next != null && pre.next.val < head.val){
                pre = pre.next;
            }
            // 插入当前节点
            ListNode next = head.next;
            head.next = pre.next;
            pre.next = head;

            head = next;
        }

        return dummy.next;
    }
}
