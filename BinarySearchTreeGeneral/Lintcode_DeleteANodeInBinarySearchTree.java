/*
Given a root of Binary Search Tree with unique value for each node.  Remove the node with given value.
If there is no such a node with given value in the binary search tree, do nothing. You should keep the
tree still a binary search tree after removal.

Example
Given binary search tree:

         5
        / \
       3   6
      / \
     2   4

Remove 3, you can either return:

         5
        / \
       2   6
        \
         4
or :

         5
        / \
       4   6
      /
     2
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

Steps:
1. Find the node
2. Find the maximum node in the left subtree
3. Replace the node with the maximum node in the left subtree.

Special Cases:
1. The node doest have a left child.
2. The maximum node in the left subtree has a left child.
3. The node is the root of the tree.

public class Solution {
    /**
    * @param root: The root of the binary search tree.
    * @param value: Remove the node with given value.
    * @return: The root of the binary search tree after removal.
    */
    public TreeNode removeNode(TreeNode root, int value) {
        // 新建一个dummy node作为root的父节点
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;

        // 找到待删除点的父节点
        TreeNode parent = findNode(dummy, root, value);
        // node作为待删除点的指针
        TreeNode node;

        if (parent.left != null && parent.left.val == value){
            // 待删除点是父节点的左孩子
            node = parent.left;
        }else if (parent.right != null && parent.right.val == value){
            // 待删除点是父节点的右孩子
            node = parent.right;
        }else{
            return dummy.left;
        }

        deleteNode(parent, node);
        return dummy.left;
    }

    public TreeNode findNode(TreeNode parent, TreeNode node, int value){
        if (node == null){
            return parent;
        }
        if (node.val == value){
            return parent;
        }
        if (value < node.val){
            return findNode(node, node.left, value);
        }else{
            return findNode(node, node.right, value);
        }
    }

    public void deleteNode(TreeNode parent, TreeNode node){
        // 如果待删除点没有右子树
        if (node.right == null){
            if (parent.left == node){
                parent.left = node.left;
            }else{
                parent.right = node.left;
            }
        // 待删除点有右子树
        }else{
            TreeNode maxNodeParent = node;
            TreeNode maxNode = node.right;

            while (maxNode.left != null){
                maxNodeParent = maxNode;
                maxNode = maxNode.left;
            }

            if (maxNodeParent.left == maxNode){
                maxNodeParent.left = maxNode.right;
            }else{
                maxNodeParent.right = maxNode.right;
            }

            maxNode.left = node.left;
            maxNode.right = node.right;

            if (parent.left == node){
                parent.left = maxNode;
            }else{
                parent.right = maxNode;
            }
        }
    }
}

/*
Note:
// http://answer.ninechapter.com/solutions/delete-a-node-in-binary-search-tree/
// http://www.mathcs.emory.edu/~cheung/Courses/171/Syllabus/9-BinTree/BST-delete.html

public class Solution {
    // node就是待删除点
    private void myDeleteNode(TreeNode parent, TreeNode node) {
        // case 1: 如果待删除点没有左子树
        if (node.left == null) {
            // 待删除点没有左子树并且待删除点是父节点的右孩子，直接接上即可
            //          p
            //         / \
            //            n
            //             \
            if (parent.right == node) {
                parent.right = node.right;
            // 待删除点没有左子树并且待删除点是父节点的左孩子，直接接上即可
            //          p
            //         / \
            //        n
            //         \
            } else {
                parent.left = node.right;
            }
        // case 2: 如果待删除点有左子树，则删除node后要从node的左子树中找到最大的点代替node位置
        } else {
            // 此时先新建两个点存最大点的父节点和最大点本身，初始最大点的父节点为node
            TreeNode maxNodeParent = node;
            TreeNode maxNode = node.left;

	        // 在左子树中找最大点，其肯定在左子树中最下最右的位置
            while (maxNode.right != null) {
                maxNodeParent = maxNode;
                maxNode = maxNode.right;
            }
            // 如果待删除结点的下面只有单左枝，则最大点就在待删除点的下面，将最大点的左枝直接接上即可
            //          parent
            //           /
            //         maxP(node)
            //         /
            //       maxN
            //        /
            //     maxN.left
            if (maxNodeParent.left == maxNode) {
	        maxNodeParent.left = maxNode.left;
            // 如果待删除结点的下面有右枝并且最大点如果有左孩子，则接到最大点的父节点上
            //         maxP
            //            \
            //           maxN
            //            /
            //         maxN.left
            } else {
                maxNodeParent.right = maxNode.left;
            }

            // 将待删除点左子树中的最大点放到node的位置，先将下面接好
            maxNode.left = node.left;
            maxNode.right = node.right;
            // 如果待删除点是父节点的左孩子，接好上面
            if (parent.left == node) {
                parent.left = maxNode;
            // 如果待删除点是父节点的右孩子，接好上面
            } else {
                parent.right = maxNode;
            }
        }
    }

    private void findAndDelete(TreeNode parent, TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.val == val) {
            // 如果已经找到待删除点，调用myDeleteNode方法删除节点
            myDeleteNode(parent, node);
            // 如果当前节点小于待删除点则到右子树去找待删除点
        } else if (node.val < val) {
            findAndDelete(node, node.right);
            // 否则去左子树找
        } else {
            findAndDelete(node, node.left);
        }
    }

    public TreeNode deleteNode(TreeNode root, int val) {
        // 新建一个dummyNode作为root的parent节点
        TreeNode dummyNode = new TreeNode();
        dummyNode.left = root;
        findAndDelete(dummyNode, root);
        return dummyNode.left;
    }
}
*/
