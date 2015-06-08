/*
Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].

Note: Recursive solution is trivial, could you do it iteratively?
*/
// Tag: Tree, Stack, HashTable

// Solution1: Recursion: Divide & Conquer
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null){
            return result;
        }
        // 同其他两种遍历，递归计算当前的左右子树，但是会先递归到最下层，然后向上传
        List<Integer> left = inorderTraversal(root.left);
        List<Integer> right = inorderTraversal(root.right);

        result.addAll(left);
        result.add(root.val);
        result.addAll(right);
        return result;
    }
}

// Solution2: Recursion: Traverse
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        traverse(result, root);
        return result;
    }

    public void traverse(List<Integer> result, TreeNode root){
        if (root == null){
            return;
        }
        // 首先遍历到最左最下，先被加入到result中
        traverse(result, root.left);
        // 回溯后加入最左最下的父节点，然后继续
        result.add(root.val);
        // 继续遍历当前父节点的右孩子，以此类推
        traverse(result, root.right);
    }
}

// Solution3: Iteration
// 如果可能从根节点一直向左向下遍历，边遍历边入栈，直到遇到没有左孩子的点为止，将这个没有左孩子的点出栈，然后遍历其右孩子
// 如果其有右孩子，继续上述步骤，如果没有右孩子，继续向上回溯。
// 简单来说就是有左就向左走，边走边入栈；左走到头就将结果出栈，存到result中。然后向右走，找右的左，重复上述步骤。
// 由于左已经在开始时候加入结果，中是后来加入，然后才是node = popped.right，保证是中序遍历。
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();

        if (root == null) return result;

        TreeNode node = root;
        // node为null应该是到了叶子节点的下面，stack为空应该是为初始状态或者所有节点都已经遍历过
        while (node != null || !stack.empty()){
            if (node != null){
                // 如果当前指针所指节点不为空则先将当前点入栈，然后继续遍历其左孩子
                stack.push(node);
                node = node.left;
                // 如果当前遍历到的点为null，说明已经遍历到没有左孩子的节点然后再往下，该节点的left肯定为null
            }else{
                // 则这里回溯到该节点，用popped存储stack最上元素出栈，即该节点
                TreeNode popped = stack.pop();
                // 将该节点存入result中
                result.add(popped.val);
                // 然后向右走
                node = popped.right;
            }
        }
        return result;

    }
}
