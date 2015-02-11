/*
Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?
*/
// Tag: Tree, Stack

// 二叉树的非递归遍历：http://www.cnblogs.com/dolphin0520/archive/2011/08/25/2153720.html

// Recursion solution: Divide & Conquer
// 思路：取到最下面的左子树和右子树，返回给上一层的left，然后取到上一层的right，打包上一层自身的node然后层层
// 往上返, 分治是先算出当前点的左子树的遍历和右子树的遍历，最后把结果拼接起来。而traverse是完全的遍历过程
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        // 如果是初始root即为空树；如果是叶子结点则返回叶子节点
        if (root == null){
            return result;
        }
        // 这里分别计算当前点的左和右，但是要先递归到最左最下的地方，然后层层将结果向上传
        // 比如三层的树，祖父的left（左父亲）包括左父亲及其下面的所有结点信息，right同理
        List<Integer> left = preorderTraversal(root.left);
        List<Integer> right = preorderTraversal(root.right);

        result.add(root.val);
        result.addAll(left);
        result.addAll(right);
        // 将本层的root，left，right封装到result里返回给上层的left或者right
        return result;
    }
}

// Recursion solution: Traverse
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        traverse(root, result);
        return result;
    }

    public void traverse(TreeNode root, List<Integer> result){
        if (root == null){
            return;
        }
        result.add(root.val);
        traverse(root.left, result);
        traverse(root.right, result);
    }
}

// No-recursion solution:
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> traversal = new Stack<TreeNode>();
        if (root == null){
            return result;
        }
        // 将root放到stack内
        traversal.push(root);
        while (!traversal.empty()){
            // 每次取出最上面的点加到结果，然后将左右孩子放入栈内
            TreeNode node = traversal.pop();
            result.add(node.val);
            // 先放右孩子，保证左孩子先被取出
            if (node.right != null){
                traversal.push(node.right);
            }
            if (node.left != null){
                traversal.push(node.left);
            }
        }

        return result;
    }
}
// 用栈空间复杂度是O(N),如果要求O(1)空间复杂度则用Morris Traversal
