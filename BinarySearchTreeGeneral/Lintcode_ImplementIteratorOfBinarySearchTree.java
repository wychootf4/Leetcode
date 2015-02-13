/*
Design an iterator over a binary search tree with the following properties:
Elements are visited in ascending order (i.e. an inorder traversal)
next() and hasNext() queries run in O(1) time in average.
Example
For the following binary search tree, inorder traversal by using iterator is [1, 6, 10, 11, 12]

            10
           /  \
          1   11
           \    \
            6   12

Challenge
Extra memory usage O(h), h is the height of the tree.
Super Star: Extra memory usage O(1)
*/

// 此题的实质就是用非递归，栈的方式实现中序遍历
// http://www.cnblogs.com/yuzhangcmu/p/4197346.html
/**
* Definition of TreeNode:
* public class TreeNode {
*     public int val;
*     public TreeNode left, right;
*     public TreeNode(int val) {
*         this.val = val;
*         this.left = this.right = null;
*     }
* }
* Example of iterate a tree:
* Solution iterator = new Solution(root);
* while (iterator.hasNext()) {
*    TreeNode node = iterator.next();
*    do something for node
* }
*/
public class Solution {

    Stack<TreeNode> stack = new Stack<TreeNode>();
    TreeNode curr;
    //@param root: The root of binary tree.
    public Solution(TreeNode root) {
        // write your code here
        curr = root;
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        // write your code here
        return curr != null || !stack.isEmpty();
    }

    //@return: return next node
    public TreeNode next() {
        // write your code here
        while (curr != null){
            stack.push(curr);
            curr = curr.left;
        }
        curr = stack.pop();
        TreeNode node = curr;
        curr = curr.right;

        return node;
    }
}

/*
Note:
// http://stackoverflow.com/questions/4581576/%20implementing-an-iterator-over-a-binary-search-tree
Is there a way to design an iterator over a binary search tree with the following properties?

Elements are visited in ascending order (i.e. an inorder traversal)
next() and hasNext() queries run in O(1) time.
Memory usage is O(1)
//

The simplest possible iterator stores the last seen key, and then on the next iteration,
searches the tree for the least upper bound for that key. Iteration is O(log n). This has
the advantage of being very simple. If keys are small then the iterators are also small.
of course it has the disadvantage of being a relatively slow way of iterating through the tree.
It also won't work for non-unique sequences.

Some trees use exactly the implementation you already use, because it's important for their
specific use that scanning is very fast. If the number of keys in each node is large, then
the penalty of storing sibling pointers isn't too onerous. Most B-Trees use this method.

many search tree implementations keep a parent pointer on each node to simplify other operations.
If you have that, then you can use a simple pointer to the last seen node as your iterator's state.
at each iteration, you look for the next child in the last seen node's parent. if there are no more
siblings, then you go up one more level.

If none of these techniques suit you, you can use a stack of nodes, stored in the iterator.
This serves a the same function as the function call stack when iterating through the search
tree as normal, but instead of looping through siblings and recursing on children, you push
children onto the stack and return each successive sibling.
*/
