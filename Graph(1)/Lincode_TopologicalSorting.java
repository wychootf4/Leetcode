/*
Given an directed graph, a topological order of the graph nodes is defined as follow:

For each directed edge A-->B in graph, A must before B in the order list.
The first node in the order can be any node in the graph with no nodes direct to it.
Find any topological order for the given graph.
Example
For graph as follow:
0(1,2,3),1(4),2(4,5),3(4,5)


The topological order can be:

[0, 1, 2, 3, 4, 5]

or

[0, 2, 3, 1, 5, 4]

or

....



Note
You can assume that there is at least one topological order in the graph.

Challenge
Can you do it in both BFS and DFS?
*/

/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */
public class Solution {
    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
        Map<DirectedGraphNode, Integer> inCount = new HashMap<DirectedGraphNode, Integer>();

        // 首先计算图中每个点的入度，先取入度为0的点
        for (DirectedGraphNode node : graph){
            for (DirectedGraphNode neighbor : node.neighbors){
                // 如果之前此邻接点已经有入度，则入度+ 1
                if (inCount.containsKey(neighbor)){
                    inCount.put(neighbor, inCount.get(neighbor) + 1);
                // 如果没有入度则初始化入度为1
                }else{
                    inCount.put(neighbor, 1);
                }
            }
        }

        // 因为前面是通过遍历到某个点时取其neighbor判断然后更改其neighbor的入度，所以
        // 对于初始入度为0的点，inCount里是没有的，将这些点加入队列。
        Queue<DirectedGraphNode> q = new LinkedList<DirectedGraphNode>();
        for (DirectedGraphNode node : graph){
            if (!inCount.containsKey(node)){
                q.offer(node);
            }
        }

        // 然后陆续将入度为0的点加入结果，然后将其邻接点的入度-1，如果其邻接点的入度降为
        // 0,则加入队列继续处理，直到队列为空为止
        while (!q.isEmpty()){
            DirectedGraphNode node = q.poll();
            result.add(node);
            for (DirectedGraphNode neighbor : node.neighbors){
                inCount.put(neighbor, inCount.get(neighbor) - 1);
                if (inCount.get(neighbor) == 0){
                    q.offer(neighbor);
                }
            }
        }

        return result;
    }
}
