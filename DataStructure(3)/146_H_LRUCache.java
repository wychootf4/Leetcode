/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following
operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
it should invalidate the least recently used item before inserting a new item.
*/
// Tag: Data Structure

/*
分析：
先考虑一种简单链表的方法，维护头尾指针，保持链表的长度为固定值，插入新元素时如果链表已满，则删除头，将新元素插入尾。这样二层循环外层遍历要插入的点，
内层循环查找与之相同的点，时间复杂度为O(n^2)。
这里可以对内层循环查找相同做优化，利用HashMap和双向链表：
1. 如果需要移除老的节点，我们从头节点移除。
2. 如果某个节点被访问（SET/GET），将其移除并挂在双向链表的结尾。
3. 链表满了后，我们删除头节点。
4. 最近访问的节点在链尾。最久被访问的节点在链头。
*/
public class LRUCache {

    private class Node{
        Node prev;
        Node next;
        int key;
        int value;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private int capacity;
    private Map<Integer, Node> map = new HashMap<Integer, Node>();
    // 新建两个dummy node方便在头尾插入删除节点
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        tail.prev = head;
        head.next = tail;
    }

    public int get(int key) {
        // 如果map里没找到key返回-1
        if (!map.containsKey(key)){
            return -1;
        }
        // 找到key的话把相应的点删除，然后把该点挪到队尾，证明刚被访问过
        Node current = map.get(key);
        current.next.prev = current.prev;
        current.prev.next = current.next;

        moveToTail(current);

        return current.value;
    }

    public void set(int key, int value) {
        // 如果要设置的key已经存在于map，修改相应key的value，然后删除该点，将其挪到队尾
        if (this.get(key) != -1){
            Node current = map.get(key);
            current.value = value;

            current.next.prev = current.prev;
            current.prev.next = current.next;

            moveToTail(current);
            return;
        }
        // 如果要设置的key没在map中而且map已经满了，删除表头的点，将新的点插入队尾
        if (map.size() == capacity){
            map.remove(head.next.key);
            head.next = head.next.next;
            head.next.prev = head;
        }

        Node insert = new Node(key, value);
        map.put(key, insert);
        moveToTail(insert);
    }

    private void moveToTail(Node node){
        node.prev = tail.prev;
        tail.prev = node;
        node.prev.next = node;
        node.next = tail;
    }
}
