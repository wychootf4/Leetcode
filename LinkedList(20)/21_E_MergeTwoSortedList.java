/*
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together
the nodes of the first two lists.
Example
Given 1->3->8->11->15->null, 2->null , return 1->2->3->8->11->15->null
*/
// Tag: Linked List
// Company: Amazon, Linkedin, Apple, Microsoft

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
/*
Solution: 由于不知道第一个点是哪个链表的，所以使用dummy点，这个技巧适用于所有可能会处理头结点的链表问题。
然后依次比较l1和l2的当前点，小的接上，而最后如果l1或者l2有剩余的直接接到最后面。
时间复杂度是O(n),空间复杂度是O(1).
 */
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 由于不知道头一个是l1还是l2节点，所以用dummy node
        ListNode dummy = new ListNode(0);
        ListNode lastNode = dummy;

        while (l1 != null && l2 != null){
            // 哪个小就取哪个点，然后把lastNode也向前移
            if (l1.val < l2.val){
                lastNode.next = l1;
                l1 = l1.next;
            }else{
                lastNode.next = l2;
                l2 = l2.next;
            }
            lastNode = lastNode.next;
        }

        // 如果l1或者l2还有剩，直接接到最后面
        if (l1 != null){
            lastNode.next = l1;
        }
        if (l2 != null){
            lastNode.next = l2;
        }

        return dummy.next;
    }
}
