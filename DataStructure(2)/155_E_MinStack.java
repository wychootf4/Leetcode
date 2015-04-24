/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
*/
// Tag: Stack, Data Structure

/*
分析：
用两个栈实现，一个常规栈实时完成各项操作，另一个最小栈push时压入min（入栈元素，最小栈栈顶元素）
*/
class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack(){
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }

    public void push(int x) {
        stack.push(x);

        if (minStack.empty()){
            minStack.push(x);
        }else if (minStack.peek() >= x){
            minStack.push(x);
        }else{
            minStack.push(minStack.peek());
        }
    }

    public void pop() {
        minStack.pop();
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
