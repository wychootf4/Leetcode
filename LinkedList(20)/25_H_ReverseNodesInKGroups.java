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
/*
Solution: 先遍历一遍链表，根据链表的长度计算有几组需要翻转，维护一系列的指针，依次处理各组链表，最后如果还有不足一组的
结点则接到最后面。

处理各组链表时维护当前组链表的currHead和currTail指针，然后使用prev，curr和next指针依次处理组中的每个元素。
处理完当前组中的所有元素后将prevTail指针更新成currTail所指的结点。

另外需要判断如果prevTail指针为null则说明是第一组元素，将表头head置为currHead指针所指的结点，否则就将prevTail.next置为
currHead。
时间复杂度是O(n^2),空间复杂度是O(1).
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
        // 不足1组
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
                // 处理完当前元素后将curr指针向后移一位
                curr = nextNode;
            }
            // 处理完每组元素后判定如果preTail为空证明这是第一组元素，将head指向currHead；否则将currHead接上preTail
            if (preTail == null){
                head = currHead;
            }else{
                preTail.next = currHead;
            }
            // 更新preTail为当前组元素的尾
            preTail = currTail;
        }
        // 处理完所有元素后链表可能还有剩余，而此时的curr正好指向剩余元素的头一个，在最后一次循环时curr = nextNode
        // 如果没有剩余则curr正好指向null
        preTail.next = curr;

        return head;
    }
}
