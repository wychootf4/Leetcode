/*
Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.
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
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null){
            return null;
        }
        // 先计算两个链表的长度，然后把长的先走长度差值的步数，保持总长度一致。这是因为如果两个链表有交叉点，那么从交叉点到链表尾肯定是
        // 完全相同的，而保持长度一致，如果有交叉点的话两个表头指针一起走就一定能相遇。
        int lenA = getLen(headA);
        int lenB = getLen(headB);
        int count = Math.abs(lenA - lenB);

        if (lenA > lenB){
            while (count > 0){
                headA = headA.next;
                count--;
            }
        }else if (lenA < lenB){
            while (count > 0){
                headB = headB.next;
                count--;
            }
        }

        while (headA != null){
            if (headA == headB){
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }

        return null;
    }

    private int getLen(ListNode head){
        int len = 0;
        while (head != null){
            len++;
            head = head.next;
        }

        return len;
    }
}
