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
