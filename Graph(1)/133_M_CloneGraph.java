/*
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
*/
// Tag: DFS, BFS, Graph

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null){
            return null;
        }
        // 新建nodes存储BFS的队列，map存储原点和克隆点间的对
        List<UndirectedGraphNode> nodes = new ArrayList<UndirectedGraphNode>();
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        // 把root放进队列和hashmap
        nodes.add(node);
        map.put(node, new UndirectedGraphNode(node.label));
        // 首先克隆点，BFS
        int index = 0;
        while (index < nodes.size()){
            UndirectedGraphNode root = nodes.get(index++);
            for (UndirectedGraphNode neighbor : root.neighbors){
                if (!map.containsKey(neighbor)){
                    nodes.add(neighbor);
                    map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                }
            }
        }

        // 然后克隆点的边, 先取得旧点对应的新点，再将旧点的neighbor加入新点的neighbor中去
        for (UndirectedGraphNode oldNode : nodes){
            UndirectedGraphNode newNode = map.get(oldNode);
            for (UndirectedGraphNode oldNeighbor : oldNode.neighbors){
                newNode.neighbors.add(map.get(oldNeighbor));
            }
        }

        return map.get(node);
    }
}
