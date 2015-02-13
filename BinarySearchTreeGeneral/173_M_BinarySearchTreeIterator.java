/*
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
*/
// Tag: Tree, Stack

/**
* Definition for binary tree
* public class TreeNode {
*     int val;
*     TreeNode left;
*     TreeNode right;
*     TreeNode(int x) { val = x; }
* }
*/

public class BSTIterator {

    Stack<TreeNode> stack = new Stack<TreeNode>();
    TreeNode curr;

    public BSTIterator(TreeNode root) {
        curr = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        // 在这里用短路或，如果curr不为空的话就不用计算后面了
        return curr != null || !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        // 迭代到最左下，边走边将遍历的点加入栈
        while (curr != null){
            stack.push(curr);
            curr = curr.left;
        }
        // 走到不能走了将当前点出栈，并返回，如果当前点有右孩子就去遍历右孩子，然后再将右孩子的左孩子迭代入栈
        curr = stack.pop();
        TreeNode node = curr;
        curr = curr.right;

        return node.val;
    }
}

/**
* Your BSTIterator will be called like this:
* BSTIterator i = new BSTIterator(root);
* while (i.hasNext()) v[f()] = i.next();
*/
