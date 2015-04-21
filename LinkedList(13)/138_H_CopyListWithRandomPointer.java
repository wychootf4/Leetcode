/*
A linked list is given such that each node contains an additional random pointer which could point to any node
in the list or null.

Return a deep copy of the list.
*/
// Tag: Hash Table, Linked List

// Solution1: HashMap
// 建立一个hashmap存head与newNode，head.random和newNode.random的映射关系，顺序遍历链表，如果head或者head.random没有被
// 存为hashmap的key则存入，否则直接取出赋给newNode或newNode.random。

/**
* Definition for singly-linked list with a random pointer.
* class RandomListNode {
*     int label;
*     RandomListNode next, random;
*     RandomListNode(int x) { this.label = x; }
* };
*/
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null){
            return null;
        }

        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode pre = dummy;
        RandomListNode newNode;

        while (head != null){
            if (map.containsKey(head)){
                newNode = map.get(head);
            }else{
                newNode = new RandomListNode(head.label);
                map.put(head, newNode);
            }
            pre.next = newNode;

            if (head.random != null){
                if (map.containsKey(head.random)){
                    newNode.random = map.get(head.random);
                }else{
                    newNode.random = new RandomListNode(head.random.label);
                    map.put(head.random, newNode.random);
                }
            }

            pre = newNode;
            head = head.next;
        }

        return dummy.next;
    }
}

// Solution2: No HashMap
// 首先将复制出来的新节点插入到各个老节点的后面，然后再复制random指针，最后将新老节点组成的两个链表分开

/**
* Definition for singly-linked list with a random pointer.
* class RandomListNode {
*     int label;
*     RandomListNode next, random;
*     RandomListNode(int x) { this.label = x; }
* };
*/
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null){
            return null;
        }

        copyNext(head);
        copyRandom(head);
        return splitList(head);
    }

    private void copyNext(RandomListNode head){
        while (head != null){
            // 首先创建出新节点
            RandomListNode newNode = new RandomListNode(head.label);
            newNode.random = head.random;
            newNode.next = head.next;
            // 将新节点插入到老节点的后面
            head.next = newNode;
            // 指针移到当前节点的原next处继续遍历
            head = head.next.next;
        }
    }

    private void copyRandom(RandomListNode head){
        while (head != null){
            // head.random的next就是head.random的复制，把head.next.random指过去
            if (head.next.random != null){
                head.next.random = head.random.next;
            }
            head = head.next.next;
        }
    }

    private RandomListNode splitList(RandomListNode head){
        RandomListNode newHead = head.next;

        while (head != null){
            // 用一个临时节点保存当前点next的信息
            RandomListNode temp = head.next;
            // 将当前点的next跳过一位，指向应该的位置
            head.next = temp.next;
            head = head.next;
            if (temp.next != null){
                temp.next = temp.next.next;
            }
        }

        return newHead;
    }
}
// Time Complexity: O(n)
