/*
Sort a linked list in O(n log n) time using constant space complexity.
*/
// Tag: Linked List, Sort
// Reference: http://www.cnblogs.com/yuzhangcmu/p/4131885.html

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

// Solution1: MergeSort，时间复杂度是O（nlogn），空间复杂度是O（logn）因为每次合并时要使用栈空间
public class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }

        // 先计算right部分这样可以不用新建变量存储中间节点，用完了再切断mid点和后面
        ListNode mid = findMiddle(head);
        ListNode right = sortList(mid.next);
        mid.next = null;
        ListNode left = sortList(head);

        return merge(left, right);
    }

    private ListNode findMiddle(ListNode head){
        // 在这里fast提前走一步这样能快一步，取到中间节点的前一步，否则对于1->2->null,如果slow和fast一起走最后slow会取到2，这样
        // 就分不开两个点
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    private ListNode merge(ListNode head1, ListNode head2){
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (head1 != null && head2 != null){
            if (head1.val < head2.val){
                tail.next = head1;
                head1 = head1.next;
            }else{
                tail.next = head2;
                head2 = head2.next;
            }
            tail = tail.next;
        }

        if (head1 != null){
            tail.next = head1;
        }
        if (head2 != null){
            tail.next = head2;
        }

        return dummy.next;
    }
}

// Solution2：QuickSort
// 快排的时间复杂度是O（n^2），平均时间复杂度是O（nlogn），空间复杂度是O（1），但递归栈是O（logn）- O（n）
// 快速排序的理想情况是每次都将所有元素分成相同长度的两部分，一共需要logn次。但是如果所有元素都相同或者大致已经排序的情况下，每次排序
// 只能减少一个元素，退化为冒泡排序。先跳过，需要时候看reference补充上
