/*
Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself
can be changed.
*/
// Tag: Linked List

/*
分析：注意处理好待翻转的两个元素与其前后的两个元素间的指针关系即可，用一个pre指针保存待翻的两个元素中前一个元素的上一个节点是什么。
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
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // pre记录第一个待翻元素的前一个元素是什么
        ListNode pre = dummy;

        while (pre.next != null && pre.next.next != null){
            // 用next记录第二个待翻元素的后一个元素是什么
            ListNode next = pre.next.next.next;
            // 用tmp保存第一个待翻元素
            ListNode tmp = pre.next;
            // pre的next是第二个待翻元素
            pre.next = pre.next.next;
            // 第二个待翻元素的next是第一个待翻元素
            pre.next.next = tmp;
            // 第一个待翻元素的next是第二个待翻元素的next
            tmp.next = next;

            // 将pre移到翻转后的第二个元素位置
            pre = tmp;
        }

        return dummy.next;
    }
}
