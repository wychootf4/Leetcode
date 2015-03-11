1. Insert Node
将c插入到a -> b中
（1）插到a前面
    c.next = head;
    head = c;
（2）插到a和b中间
    先将指针指向待插入点，然后进行操作
    a.next = c;
    c.next = b;
（3）插到b后面
    b.next = c;
    c.next = null;

2. Delete Node
删除某个节点要知道这个点的前继节点，即prev。删掉curr点就是prev.next = prev.next.next;
   a->b->c->d
（1）指针在开头，将c删除
    先指针指向b，然后b.next = b.next.next
（2）指针已经指向c，将c删除
    可以将d复制过来，然后删除d
    c.val = d.val;
    c.next = c.next.next;

3. Reverse Linked List
见reverse linked list的例题，维护prev, head, next指针, 利用next指针存储变化前原来next所指的位置，变化后整体后移一位，直到prev
指向原链表的最后一个元素，跳出循环，返回prev即可

4. Merge Two Sorted Linked Lists
while循环比较两个链表中指针所指的元素，取小的一个然后该链表指针加1，最后如果某个链表还有元素直接加到最后面

5. Find middle of the linked list
简单的方法是先遍历一遍算出链表的长度，然后再遍历一遍走到链表的中间
好的方法是用快慢指针，快指针一次走两步，慢指针一次走一步，这样当快指针走到尾部时候慢指针恰好在中间位置

6.注意：防止递归死循环，recursion（a，b，c）每次往下递归调用时这些参数所代表的问题规模要变小
Binary Tree：node.left，node.right
Linked List：保证链表越来越短
Array：数组越来越小
Permutation：问题规模越来越小，n个数的排列->n-1个数的排列
递归算法的思路：让问题的规模通过同样的处理，越来越小，从程序角度来讲就是函数的自我调用
