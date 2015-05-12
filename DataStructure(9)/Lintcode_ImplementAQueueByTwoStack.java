/*
As the title described, you should only use two stacks to implement a queue's actions.

The queue should support push(element), pop() and top() where pop is pop the first(a.k.a front) element in the queue.

Both pop and top methods should return the value of first element.

Example
For push(1), pop(), push(2), push(3), top(), pop(), you should return 1, 2 and 2

Challenge
implement it by two stacks, do not use any other data structure and push, pop and top should be O(1) by AVERAGE.
*/
// Tag: Stack, Queue

/*
分析：
用两个栈，push时候push到第一个栈，当pop和top时将第一个栈的元素全转移到第二个栈，然后从第二个栈中找。第二个栈的栈顶元素就是第一个栈的栈底元素，
这样就从先进后出变为了先进先出，即queue的定义
*/
public class Solution {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public Solution() {
       // do initialization if necessary
       stack1 = new Stack<Integer>();
       stack2 = new Stack<Integer>();
    }

    public void stack1Tostack2(){
        while (!stack1.empty()){
            stack2.push(stack1.pop());
        }
    }

    public void push(int element) {
        // write your code here
        stack1.push(element);
    }

    public int pop() {
        // write your code here
        if (stack2.empty()){
            this.stack1Tostack2();
        }

        return stack2.pop();
    }

    public int top() {
        // write your code here
        if (stack2.empty()){
            this.stack1Tostack2();
        }

        return stack2.peek();
    }
}
