/*
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?
*/
// Tag: Linked List, Two Pointers

// 如果简单解法的话就用哈希表，如果发现有重复的元素的话就是有环，但是此解法需要使用额外空间
// 这里用快慢指针解决，如果有环的话快指针最终会追上慢指针，两个点会重合

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
public class Solution {
    public boolean hasCycle(ListNode head) {
        // 注意判断只有一个节点的情况
        if (head == null || head.next == null){
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != slow){
            if (fast.next == null || fast.next.next == null){
                return false;
            }

            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }
}
