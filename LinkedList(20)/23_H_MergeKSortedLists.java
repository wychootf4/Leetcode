/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
*/
// Tag: Divide and Conquer, Linked List, Heap
// Company: Linkedin, Google, Uber, Airbnb, Facebook, Twitter, Amazon, Microsoft

// Reference: http://www.cnblogs.com/yuzhangcmu/p/4146119.html
// Solution1: 用递归的方法将k个链表的合并问题分解到两个链表的合并
// 假设总共有k个list，每个list的最大长度是n，那么运行时间满足递推式T(k) = 2T(k/2)+O(n*k)。根据主定理，可以算出算法的总复杂度是O(nklogk)
// 空间复杂度的话是递归栈的大小O(logk)。
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
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0){
            return null;
        }

        return helper(lists, 0, lists.size() - 1);
    }

    private ListNode helper(List<ListNode> lists, int left, int right){
        if (left < right){
            // divide
            int mid = left + (right - left) / 2;
            // 一直分到只有两个链表，然后进行合并回溯, conquer
            return merge(helper(lists, left, mid), helper(lists, mid + 1, right));
        }
        // 返回当前合并后的表头
        return lists.get(left);
    }

    private ListNode merge(ListNode n1, ListNode n2){
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (n1 != null && n2 != null){
            if (n1.val < n2.val){
                curr.next = n1;
                n1 = n1.next;
            }else{
                curr.next = n2;
                n2 = n2.next;
            }
            curr = curr.next;
        }

        if (n1 != null){
            curr.next = n1;
        }else{
            curr.next = n2;
        }

        return dummy.next;
    }
}

// Solution2: 用priority queue来做
// 维护一个大小为k的堆，每次取堆顶的最小元素放到结果中， 然后读取该元素的下一个元素放入堆中，重新维护好。因为每个链表是有序的，
// 每次又是去当前k个元素中最小的，所以当所有链表都读完时结束，这个时候所有元素按从小到大放在结果链表中。这个算法每个元素要读取一次，
// 即是k*n次，然后每次读取元素要把新元素插入堆中要logk的复杂度，所以总时间复杂度是 O(nklogk)。空间复杂度是堆的大小，即为O(k)。
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
    private Comparator<ListNode> listNodeComparator = new Comparator<ListNode>(){
        public int compare(ListNode left, ListNode right){
            if (left == null){
                return 1;
            }else if (right == null){
                return -1;
            }
            return left.val - right.val;
        }
    };

    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0){
            return null;
        }

        // 将所有k个点加入heap中
        Queue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(), listNodeComparator);
        for (int i = 0; i < lists.size(); i++){
            if (lists.get(i) != null){
                heap.add(lists.get(i));
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!heap.isEmpty()){
            // 从堆poll出来的是k个结点中最小的，接到tail后面，如果head的next还有元素就将其加入堆中
            ListNode head = heap.poll();
            tail.next = head;
            tail = head;
            if (head.next != null){
                heap.add(head.next);
            }
        }

        return dummy.next;
    }
}
