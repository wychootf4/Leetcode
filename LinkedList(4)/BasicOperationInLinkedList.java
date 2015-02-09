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
   a->b->c->d
（1）指针在开头，将c删除
    先指针指向b，然后b.next = b.next.next
（2）指针已经指向c，将c删除
    可以将d复制过来，然后删除d
    c.val = d.val;
    c.next = c.next.next;
