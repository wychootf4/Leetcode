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

public void printInRange(TreeNode root, int k1, int k2){
    if (root == null){
        return;
    }
    // 证明有点在根的左子树
    if (k1 < root.val){
        printInRange(root.left, k1, k2);
    }
    if (k1 <= root.val && root.val <= k2){
        System.out.print(root.val + " ");
    }
    // 证明有点在根的右子树
    if (root.val < k2){
        printInRange(root.right, k1, k2);
    }
}
