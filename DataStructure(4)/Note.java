Definition: Data structure is a way to organize data. It provides some methods to handle data stream, e.g. insert,
delete, etc.

Two kinds: Linear Data Structure: Queue, Stack, Hash
           Tree Data Structure: Heap, Trie

1. Queue(first in first out)
Operations:
O(1) Push
O(1) Pop
O(1) Top
Always used for BFS

2. Stack(first in last out)
Operations:
O(1) Push
O(1) Pop
O(1) Top

3. Hash
(1)Operations
O(1) Insert
O(1) Delete
O(1) Find

(2)Hash Function: 哈希的实现
Typical: From String to int

这种md5的方法在校验的时间上比较长，不推荐
int hashfunc(String key){
    // do something to key
    // return a deterministic integer number
    return md5(key) % hash_table_size;
}
这里推荐Apache用到magic number这个方法，33冲突会比较少
int hashfunc(String key){
    int sum = 0;
    for (int i = 0; i < key.length(); i++){
        sum = sum * 33 + (int)(key.charAt(i));
        sum = sum % hash_table_size;
    }
    return sum;
}

(3) Collision - Open Hashing vs Closed Hashing
Open Hashing: 发生冲突时将其插入到应该的slot里，用链表连接
Closed Hashing: 发生冲突后向后检索有空的放进去，这样查找效率会降低
一般存的元素为哈希表大小的10%，这样冲突会小一些

(4) Rehashing 原来的哈希表不够用，那么比如扩大一倍，重新计算哈希

(5) Java
What's the differences of: HashTable, HashSet and HashMap, which one is thread safe?
HashTable is thread safe, it has synchronized mechanism.
HashSet vs HashMap: HashSet only has key, only have value of true or false; HashMap has key and value.

4. Heap (Priority Queue, heapq)
(1) Operations
O(logn) Add
O(logn) Remove
O(1) Min/Max

(2) Implementation
Low level data structure: Dynamic Array

Heap{
    elems[], size;
}
elems[1] - root, also the minimum elem in elems
i's left child: i * 2, right child: i * 2 + 1

Internal Method: shift up, shift down
Add: Push back to elems; size++; shift up
Remove: Replace the elem to be removed whith the last elem(elems[size]); size--; shift up and shift down

5. Trie
利用O（1）的时间查找字符串是否在字典里
(1) 如何建立trie树？
起始head有26个空指针，单词依次加入，单词的末尾加星号标记。

(2) Trie vs Hash
Trie可以一个一个字符串的查找，并且Trie更加节省空间; 需要一个一个遍历字符串或者内存紧张时用Trie比较好，平时用Hash编写比较容易。
