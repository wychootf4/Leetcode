/*
You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order
and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
*/
// Tag: Math, Linked List
// Company: Amazon, Microsoft, Bloomberg, Airbnb, Adobe

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/*
思路：由于链表是逆序的，所以只需要判断每一位上两个链表的节点和是多少，如果有进位就记录下来，将进位carry加到下一位的运算中
由于就是遍历链表，因此时间复杂度为O(n),取两个链表大的数。空间复杂度为O(n)，取两个链表大的数，结果链表最多是长度+1，由于最后的进位
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
            // 这样可以每次只考虑当前位而不需要考虑整体两个数谁长谁短的问题
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
            // 注意当前位需要加入的是个位
            ListNode curr = new ListNode(sum % 10);
            head.next = curr;
            head = head.next; // or head = curr;
        }

        return dummy.next;
    }
}

// Follow-up: What if the two linked lists are not in reversed order?
// 先把两个链表逆序再进行操作即可。时间复杂度不变.