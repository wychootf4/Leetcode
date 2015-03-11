/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Follow up:
Can you solve it without using extra space?
*/
// Tag: Linked List, Two Pointers

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

// Reference: http://www.cnblogs.com/hiddenfox/p/3408931.html
// 思路：假设从head到环的开始点距离为a，环的起始点到两个指针第一次在环中相遇的距离为b，从第一次相遇点到环的起始位置距离为c。
// 第一次相遇时slow走过的距离为a+b，fast走过的距离为a+b+c+b = a+c+2b，而fast的速度为slow的二倍，就有fast走过的距离也是slow的二倍。
// 因此a+c+2b = 2（a+b），可以得到a=c。所以如果此时slow重新回到head处，fast从第一次环中相遇处一起匀速走，就能在环的开始处相遇。
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null){
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (true) {
            if (fast == null || fast.next == null) {
                return null;    //遇到null了，说明不存在环
            }
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                break;    //第一次在环中相遇
            }
        }

        slow = head;    //slow从头开始走，fast从第一次环中相遇处开始走
        while (slow != fast) {    //二者相遇在环的起始点，则退出
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
