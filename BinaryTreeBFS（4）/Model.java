/*
public class Solution {
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList result = new ArrayList();

        if (root == null)
            return result;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();
                level.add(head.val);
                if (head.left != null)
                    queue.offer(head.left);
                if (head.right != null)
                    queue.offer(head.right);
            }
            result.add(level);
        }

        return result;
    }
}
*/

宽度搜索有三种方法:
(1) 2 queues
两个queue轮流记录另一个queue中点的左右孩子，进行层层扩展

(2) 1 queue + dummy node
用一个dummy node标记每层的结束

(3) 1 queue
