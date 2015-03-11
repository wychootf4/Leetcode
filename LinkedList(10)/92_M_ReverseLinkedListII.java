/*
Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m >= n || head == null){
            return head;
        }

        // 由于不能确定头的位置所以加入dummy node
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        // 首先找到M点的前继节点
        for (int i = 1; i < m; i++){
            if (head == null){
                return head;
            }
            head = head.next;
        }

        // 维护M的前继节点和M点不动，n和postN向后移动，for循环交换中间的节点
        ListNode prevM = head;
        ListNode mNode = head.next;
        ListNode nNode = mNode;
        ListNode postN = nNode.next;
        for (int i = m; i < n; i++){
            if (postN == null){
                return null;
            }
            ListNode next = postN.next;
            postN.next = nNode;
            nNode = postN;
            postN = next;
        }

        // 最后接上两边的点
        prevM.next = nNode;
        mNode.next = postN;

        // 不管谁是head都是dummy的next节点
        return dummy.next;
    }
}
