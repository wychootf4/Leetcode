/*
Given an integer array with no duplicates. A max tree building on this array is defined as follow:

The root is the maximum number in the array
The left subtree and right subtree are the max trees of the subarray divided by the root number.
Construct the max tree by the given array.
Example
Given [2, 5, 6, 0, 3, 1], the max tree is

              6
             / \
            5   3
           /   / \
          2   0   1

Challenge
O(n) time complexity
*/
// Tag: Stack, Cartesian Tree

/*
分析：
可以用递归的方式做，但是这样最坏情况的时间复杂度是O(n^2)，而这里可以用到的思路是对于一个点，如果能找到其左右第一个比它大的元素中的较小者，
那么这个较小者就可以确定为是它的父节点。这样我们可以维护一个递减栈，这样栈顶元素是入栈元素中最小的，此时时间复杂度降为O(n)。
*/
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
 */
public class Solution {
    /**
     * @param A: Given an integer array with no duplicates.
     * @return: The root of max tree.
     */
    public TreeNode maxTree(int[] A) {
        // write your code here
        if (A == null || A.length == 0){
            return new TreeNode(0);
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();

        for (int i = 0; i < A.length; i++){
            // 每次待检查的新点
            TreeNode newNode = new TreeNode(A[i]);
            TreeNode prevNode = null;
            // 如果栈不为空并且待检查的新点比栈顶元素大，
            while (!stack.isEmpty() && A[i] > stack.peek().val){
                TreeNode currNode = stack.pop();
                // preNode == null的情况就是已经入栈若干元素，而待检查的新点比这几个点都要
                // 大，此时前一次循环接好的点是有问题的，因为栈底的点一定更接近于新点
                if (prevNode != null){
                    currNode.right = prevNode;
                }
                // 将当前出栈的栈顶元素保存下来，新点的左孩子是栈顶元素
                prevNode = currNode;
                newNode.left = prevNode;
            }
            // 待站内所有比新点小的都出栈后将其入栈
            stack.push(newNode);
        }

        // 此时站内可能会剩下未处理的元素，依次出栈，先出的就是后出的右孩子
        TreeNode prev = null;
        while (!stack.isEmpty()){
            TreeNode curr = stack.pop();
            curr.right = prev;
            prev = curr;
        }

        return prev;
    }
}
