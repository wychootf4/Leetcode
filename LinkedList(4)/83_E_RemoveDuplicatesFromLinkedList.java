/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
*/
// Tag: Linked List

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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null){
            return null;
        }

        // 用node控制向后遍历，扫到该遍历的点，最后返回head就是处理后的链表
        ListNode node = head;
        while (node.next != null){
            // 如果当前点的值与next指针所指的值相同，则next指针向后指一位
            if (node.val == node.next.val){
                node.next = node.next.next;
            // 如果不同的话dummy点向后指一位继续遍历
            }else{
                node = node.next;
            }
        }

        return head;
    }
}
