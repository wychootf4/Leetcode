/*
Write a function to delete a node (except the tail) in a singly linked list, given only
access to that node.

Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3,
the linked list should become 1 -> 2 -> 4 after calling your function.
*/
// Tag: Linked list

// Solution1: 一个一个向前复制,剩下最后一个元素时候正好是在循环条件之外，单独处理，此时node是为
// 倒数第二个元素
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void deleteNode(ListNode node) {
        if (node == null) {
            return;
        }

        while (node.next.next != null) {
            node.val = node.next.val;
            node = node.next;
        }
        node.val = node.next.val;
        node.next = null;
    }
}

// Solution2: 直接把当前点的值复制成下一个点的值，然后将指针指向后继的后继
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void deleteNode(ListNode node) {
        if (node == null) {
            return;
        }

        node.val = node.next.val;
        node.next = node.next.next;
    }
}
