/*
Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].

Note: Recursive solution is trivial, could you do it iteratively?
*/
// Tag: Tree, Stack

// Recursion: Divide & Conquer
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null){
            return result;
        }

        List<Integer> left = postorderTraversal(root.left);
        List<Integer> right = postorderTraversal(root.right);

        result.addAll(left);
        result.addAll(right);
        result.add(root.val);

        return result;
    }
}

// Recursion: Traverse
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        traverse(result, root);
        return result;
    }

    public void traverse(List<Integer> result, TreeNode root){
        if (root == null){
            return;
        }

        traverse(result, root.left);
        traverse(result, root.right);
        result.add(root.val);
    }
}

// Iteration
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode prev = null; // 记录上一个traverse的node
        TreeNode curr;

        if (root == null){
            return result;
        }
        stack.push(root);
        while (!stack.empty()){
            curr = stack.peek(); // 保证当前点是stack中最上面的node
            // prev == null证明是初始状态，prev.left或者right == curr证明是向下遍历的状态
            if (prev == null || prev.left == curr || prev.right == curr){
                // 如果此时当前点有左孩子则将左孩子加入stack，如果没有左孩子但是有右孩子则将右孩子
                // 加入stack，如果已将左孩子加入stack则向下执行，因为是DFS遍历，要先找到最左最下的点
                if (curr.left != null){
                    stack.push(curr.left);
                }else if (curr.right != null){
                    stack.push(curr.right);
                }
            // else if是从下往上回溯，比如此时stack里有AB，prev = curr = B，此时经过else会把B加入result，同时
            // B出栈，然后进入下一次循环时prev = B, curr = A,而此时curr.right为C，将C入栈，这是已处理
            // 完左孩子然后回到父节点时的判断条件
            //  A
            // / \
            //B   C
            // curr.left == prev代表上一个点是左孩子，已经把左孩子抛出，所以curr点现在是根节点，如果根节点有
            // 右孩子则加入右孩子。这样做是因为如果根有左孩子根据第一个判断条件就会继续往下走，不会把右孩子加入
            }else if (curr.left == prev){
                if (curr.right != null){
                    stack.push(curr.right);
                }
            // 从下往上遍历，这是已处理完右孩子的情况，因为后序遍历是左右根的顺序，一定要处理完左右结点才能加入
            // 结果，左右都处理完后，curr会在循环第一行被置为根节点，如果此时prev是右孩子，即curr.right == prev
            // 会将根节点加入结果然后抛出。如果此时prev是没有右孩子的根节点的左孩子，即curr.left == prev,
            // 只会将prev置成根节点，再进入下一次循环时将根节点加入结果然后抛出，然后继续往上遍历
            }else{
                result.add(curr.val);
                stack.pop();
            }
            // 当前指针被置为prev记录下来，继续下一次循环
            prev = curr;
        }
        return result;
    }

}
