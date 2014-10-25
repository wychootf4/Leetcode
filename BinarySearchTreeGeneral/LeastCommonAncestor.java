/* http://answer.ninechapter.com/solutions/lowest-common-ancestor/
   寻找两个结点的最近公共祖先
            A
           / \
          B   C
         / \
        D   E
           / \
          F   G
比如D和F的最近公共祖先就是B
*/

Version 1: Traditional Method
// 思路：从两个点得到两条到根的路径，然后逆序比较找到分道扬镳处然后返回其parent就是两个点最近的公共祖先
// 此方法时间和空间复杂度都是O(N)，很直观但是需要有parent这个指针，如果TreeNode里不提供就不能这么做
public class Solution {
    private ArrayList<TreeNode> getPath2Root(TreeNode node) {
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();
        while (node != null) {
            list.add(node);
            node = node.parent;
        }
        return list;
    }
    public TreeNode latestCommonAncestor(TreeNode node1, TreeNode node2) {
        ArrayList<TreeNode> list1 = getPath2Root(node1);
        ArrayList<TreeNode> list2 = getPath2Root(node2);

        int i, j;
        for (i = list1.size() - 1, j = list2.size() - 1; i >= 0 && j >= 0; i--, j--) {
            if (list1[i] != list2[j]) {
                return list1[i].parent;
            }
        }
        // 循环比较所有点都相同，证明两个点是相同点，循环结束后i为-1，则这样能返回root点
        return list1[i+1];
    }
}

Version 2: Divide & Conquer
// 不需要额外的parent指针但是至少要给root点
public class Solution {
    // 如果不给root点就要给parent点然后求出root
    private TreeNode getRoot(node) {
        while (node.parent != null) {
            node = node.parent;
        }
        return node;
    }

    private TreeNode getAncestor(TreeNode root, TreeNode node1, TreeNode node2) {
        // 如果root为空或者root就是两个点之一，那么直接返回root即可
        if (root == null || root == node1 || root == node2) {
            return root;
        }

        // Divide
        // 分当前root点的左右子树去找node1和node2的最近公共祖先
        TreeNode left = getAncestor(root.left, node1, node2);
        TreeNode right = getAncestor(root.right, node1, node2);

        // Conquer
        // 当前root点得左右子树返回值都不为空证明两个node一边一个，返回root即可
        if (left != null && right != null) {
            return root;
        }
        // 只有左边不为空则返回左边继续找
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        // 左右都为空就返回null
        return null;
    }

    public TreeNode latestCommonAncestor(TreeNode node1, TreeNode node2) {
        if (node1 == null || node2 == null) {
            return null;
        }
        TreeNode root = getRoot(node1);
        return getAncestor(root, node1, node2);
    }
}
