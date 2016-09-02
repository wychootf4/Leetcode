/*
Given a non-empty binary search tree and a target value, find the value in the BST that is closest
to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
*/
// Company: Microsoft Google Snapchat
// Tag: Tree, Binary Search

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Solution1: 用递归，维护diff和当前result的值，如果root比target小则向左找.由于BST的特性，target不
// 可能越过root，因为如果target比root大而向下查找后diff反而更大，则说明结果就是root，root的左子树
// 都比root小，diff只会又变大
public class Solution {
    // bug1: 没有设为global value，除非通过数组或者类来传参
    int result;
    double diff = Double.MAX_VALUE;

    public int closestValue(TreeNode root, double target) {
        helper(root, target);
        return result;
    }

    private void helper(TreeNode root, double target) {
        if (root == null) {
            return;
        }
        if (Math.abs(root.val - target) < diff) {
            diff = Math.abs(root.val - target);
            result = root.val;
        }

        if (target < root.val) {
            helper(root.left, target);
        } else {
            helper(root.right, target);
        }
    }
}

// Solution2: 用Iteration来做
public class Solution {
    public int closestValue(TreeNode root, double target) {
        int result = root.val;
        double diff = Double.MAX_VALUE;

        while (root != null) {
            if (target > root.val) {
                if (Math.abs(target - root.val) < diff) {
                    diff = Math.abs(target - root.val);
                    result = root.val;
                }
                root = root.right;
            } else if (target < root.val) {
                if (Math.abs(target - root.val) < diff) {
                    diff = Math.abs(target - root.val);
                    result = root.val;
                }
                root = root.left;
            } else {
                return root.val;
            }
        }

        return result;
    }
}
