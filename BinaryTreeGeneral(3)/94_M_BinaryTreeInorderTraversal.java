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
        traverse(result, root.left);
        result.add(root.val);
        traverse(result, root.right);
    }
}

// Solution3: Iteration
