/*
Given a linked list, remove the nth node from the end of list and return its head.

For example,

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
*/
// Tag: Linked List, Two Pointers

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
Solution: 利用快慢指针，要移除距离表尾的第n个点，就让快指针比慢指针先走n步，然后快慢指针一起向后移动。
这样当快指针到达表尾的时候，慢指针恰好指向要删除结点的前继位置，删除相应点即可，注意加一个dummy点到head的
前面以处理要删除head的情况。
时间复杂度是O(n),空间复杂度是O(1).
 */
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n <= 0 || head == null){
            return null;
        }

        // 加入dummy node防止要删除的点是第一个节点，或者整个链表只有一个结点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 要删掉某一个点要先找到它前面的一个点，所以slow初始在dummy而fast初始在dummy.next
        ListNode slow = dummy;
        ListNode fast = head;

        // 初始slow和fast差一步，再让fast多走出n步，这样能保证slow走到最后是倒数第n个点的前继节点
        for (int i = 0; i < n; i++){
            // 证明n比链表还要长，不成立
            if (fast == null){
                return null;
            }
            fast = fast.next;
        }

        while (fast != null){
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }
}

/*
Solution2: 还有一种解法是two-pass，从尾部删除第n个点，就相当于是从头部删除第L - n + 1个点，因此第一个pass可以得到
链表的长度，然后根据链表的长度删除相对应的点。这个不如Solution1.
 */