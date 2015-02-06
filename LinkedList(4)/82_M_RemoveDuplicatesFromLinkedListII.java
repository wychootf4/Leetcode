/*
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers
from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
*/
// Tag: Linked List

// 当头不能确定的时候用dummy node
// 主要思路：将dummy node加到最前面，然后用next和next.next比较，如果相同则将next的值存下来，将next指向next.next，即原来的next点
// 被删掉。通过内层循环可以把重复的点都删掉

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
        // 如果head为空直接返回空，如果head的next为空则只有一个点head，也直接返回head点
        if (head == null || head.next == null){
            return head;
        }
        // 不能确定头部是否也要删除，所以设立dummy node
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        // 初始的head是dummy点，所以一直检查next和next.next是否为空，不为空则继续遍历，头是不需要检查的
        while (head.next != null && head.next.next != null){
            // 如果next和next.next的值相同，将next的值存下来
            if (head.next.val == head.next.next.val){
                int val = head.next.val;
                // 当该删的点都删去了，head已经指向最初next.next点时，此时head.next还没有被检查过，需要看是否为空还有是否
                // 与head的值相同，如果相同则接着删，如果不相同跳出内层循环
                // 由于需要和前面的值比较，而前面的值如果相同已经被删掉了，所以要提前保存为val供比较用
                while (head.next != null && head.next.val == val){
                    head.next = head.next.next;
                }
            // 如果next和next.next的值不相同则head后移继续向后遍历
            }else{
                head = head.next;
            }
        }

        return dummy.next;
    }
}
