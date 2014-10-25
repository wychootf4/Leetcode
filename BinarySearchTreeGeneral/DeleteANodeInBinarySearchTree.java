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
