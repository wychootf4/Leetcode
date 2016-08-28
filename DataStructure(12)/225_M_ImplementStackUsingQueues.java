/*
Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
Notes:
You may assume that all operations are valid (for example, no pop or top operations will be called on an empty
stack).
Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or
deque (double-ended queue), as long as you use only standard operations of a queue -- which means only push to
back, pop from front, size, and is empty operations are valid.
*/
// Tag: Data Structure

/*
思路：用两个queue实现，比如top方法将queue1元素复制到queue2仅剩一个，保存最后一个值，然后可以交换两个queue
再将该值push到queue1
*/
class MyStack {
    private Queue<Integer> stack;
    private Queue<Integer> backup;

    public MyStack() {
        stack = new LinkedList<Integer>();
        backup = new LinkedList<Integer>();
    }
    // bug1: missing return type
    private void moveLeftOne() {
        while (stack.size() != 1) {
            backup.offer(stack.poll());
        }
    }

    private void swap() {
        Queue<Integer> temp = stack;
        stack = backup;
        backup = temp;
    }

    // Push element x onto stack.
    public void push(int x) {
        stack.offer(x);
    }

    // Removes the element on top of the stack.
    public void pop() {
        moveLeftOne();
        // bug2: wrong method, pop instead of poll
        stack.poll();
        swap();
    }

    // Get the top element.
    public int top() {
        moveLeftOne();
        int top = stack.poll();
        swap();
        stack.offer(top);

        return top;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return stack.isEmpty();
    }
}
