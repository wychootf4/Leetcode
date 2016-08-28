/*
Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes
in the BST.
According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between
two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow
a node to be a descendant of itself).”

            _______6______
           /              \
       ___2__          ___8__
      /      \        /      \
     0      _4       7       9
           /  \
          3   5
For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of
nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
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
    public TreeNode lowestCommonAncestor(TreeNode node1, TreeNode node2) {
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
/*
思路：用recursion处理，每次判断退出条件为root == null || root == node1 || root == node2
若不许退出则Divide成当前root的左右两个节点进行处理，然后判断如果两个节点都不为空证明都返回值了，
两边一边一个；若只有左边点不为空则返回left；若只有右边点不为空则返回right，若都为空则返回null
*/
public class Solution {
    // 如果不给root点就要给parent点然后求出root
    private TreeNode getRoot(node) {
        while (node.parent != null) {
            node = node.parent;
        }
        return node;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
            // write your code here
            if (A == null || B == null){
                return null;
            }
            return getAncestor(root, A, B);
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
        // 当前root点的左右子树返回值都不为空证明两个node一边一个，返回root即可
        if (left != null && right != null) {
            return root;
        }
        // 只有左边不为空证明只有左边有点，返回left
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        // 左右都为空就返回null,证明该处没有要找的节点
        return null;
    }

    public TreeNode lowestCommonAncestor(TreeNode node1, TreeNode node2) {
        if (node1 == null || node2 == null) {
            return null;
        }
        TreeNode root = getRoot(node1);
        return getAncestor(root, node1, node2);
    }
}
