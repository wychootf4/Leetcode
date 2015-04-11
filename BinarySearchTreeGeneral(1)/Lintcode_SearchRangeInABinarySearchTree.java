// http://www.geeksforgeeks.org/print-bst-keys-in-the-given-range/
/*
Print BST keys in the given range
Given two values k1 and k2 (where k1 < k2) and a root pointer to a Binary Search Tree.
Print all the keys of tree in range k1 to k2. i.e. print all x such that k1<=x<=k2 and
x is a key of given BST. Print all the keys in increasing order.

For example, if k1 = 10 and k2 = 22, then your function should print 12, 20 and 22.
                     20
                    /  \
                   8   22
                  / \
                 4  12
*/
// 时间复杂度是O(N)
public class TreeNode{
    TreeNode left;
    TreeNode right;
    int val;
    TreeNode(int x){
        this.val = x;
    }
}

public class Solution {
    /**
    * @param root: The root of the binary search tree.
    * @param k1 and k2: range k1 to k2.
    * @return: Return all keys that k1<=key<=k2 in ascending order.
    */
    public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        // write your code here
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null){
            return result;
        }

        helper(result, root, k1, k2);
        return result;
    }

    public void helper(ArrayList<Integer> result, TreeNode root, int k1, int k2){
        if (root == null){
            return;
        }
        // 左子树和k1都比root小，去左子树找
        if (k1 < root.val){
            helper(result, root.left, k1, k2);
        }
        // 如果root正好在k1和k2之间，将root加入result
        if (k1 <= root.val && k2 >= root.val){
            result.add(root.val);
        }
        // 同理右子树
        if (k2 > root.val){
            helper(result, root.right, k1, k2);
        }
        // 为保证返回值是按升序排列必须按此顺序写判断语句
    }
}
