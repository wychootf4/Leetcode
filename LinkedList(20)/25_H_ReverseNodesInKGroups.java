/*
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
*/
// Tag: Linked List

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // 分0组或者1组
        if (k == 0 || k == 1){
            return head;
        }
        // 先遍历一遍链表计算出链表的长度，看看需要翻转几组
        ListNode curr = head;
        int length = 0;
        while (curr != null){
            curr = curr.next;
            length++;
        }
        int group = length / k;
        if (group == 0){
            return head;
        }
        // 初始化一系列指针并让curr指回head
        ListNode preNode = null;
        ListNode nextNode = null;
        ListNode preTail = null;
        ListNode currTail = null;
        ListNode currHead = null;
        curr = head;
        // 对于每组待翻转的元素
        for (int i = 0; i < group; i++){
            // 初始化每组node的pre为null指向每组元素的最前面
            preNode = null;
            // 处理每组元素中每个元素
            for (int j = 0; j < k; j++){
                nextNode = curr.next;
                curr.next = preNode;
                preNode = curr;
                // 如果处理的是这组元素的头就保存为currTail，如果是尾就保存为currHead
                if (j == 0){
                    currTail = curr;
                }
                if (j == (k - 1)){
                    currHead = curr;
                }
                curr = nextNode;
            }
            // 处理完每组元素后判定如果preTail为空证明这是第一组元素，将head指向currHead；否则将currHead接上preTail
            if (preTail == null){
                head = currHead;
            }else{
                preTail.next = currHead;
            }
            preTail = currTail;
        }
        // 处理完所有元素后链表可能还有剩余，而此时的curr正好指向剩余元素的头一个，在最后一次循环时curr = nextNode
        preTail.next = curr;

        return head;
    }
}
