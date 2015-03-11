/*
Reverse a linked list.

Example
For linked list 1->2->3, the reversed linked list is 3->2->1

Challenge
Reverse it in-place and in one-pass
*/

/**
* Definition for ListNode.
* public class ListNode {
*     int val;
*     ListNode next;
*     ListNode(int val) {
*         this.val = val;
*         this.next = null;
*     }
* }
*/

// 维护prev，curr和next三个指针
public class Solution {
    /**
    * @param head: The head of linked list.
    * @return: The new head of reversed linked list.
    */
    public ListNode reverse(ListNode head) {

        if (head == null || head.next == null){
            return head;
        }

        // 初始prev指在curr也就是头指针的前面，curr指向头指针
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null){
            // 利用next存储curr的next指针
            ListNode next = curr.next;
            // curr的next指针指向prev，也就是反转
            curr.next = prev;
            // 然后prev和curr顺序向后移一位，curr.next也就同时向后移了
            // 最后prev指向原链表的最后一个元素，curr指向最后一个元素的后面，此时跳出循环，直接返回prev即可
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
