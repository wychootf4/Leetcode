/*
Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.
Notes:
You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is
empty operations are valid.
Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque
(double-ended queue), as long as you use only standard operations of a stack.
You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
*/
// Tag: Stack, Data Structure

/*
Solution: 先push到备用stack，再弹出push到stack1
*/
class MyQueue {

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public MyQueue() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }

    private void move() {
        while (stack2.size() != 0) {
            stack1.push(stack2.pop());
        }
    }

    // Push element x to the back of queue.
    public void push(int x) {
        stack2.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
      // 注意判断stack1是否为空，避免为空时候直接pop
        if (stack1.isEmpty()) {
            move();
        }
        stack1.pop();
    }

    // Get the front element.
    public int peek() {
        if (stack1.isEmpty()) {
            move();
        }
        return stack1.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        if (!stack2.isEmpty()) {
            move();
        }
        return stack1.isEmpty();
    }
}
