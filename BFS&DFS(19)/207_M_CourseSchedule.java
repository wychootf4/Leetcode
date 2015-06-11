/*
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is
expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0
you should also have finished course 1. So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about
how a graph is represented.
https://www.khanacademy.org/computing/computer-science/algorithms/graph-representation/a/representing-graphs

Hints:
This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological
ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of
Topological Sort.
Topological sort could also be done via BFS.
*/
// Tag: DFS, BFS, Graph, Topological Sort

/*
分析：
这里采用的是topological sort，先找到所有入度为0即没有先修课程的入queue，然后每次BFS，对于队列中每个入度为0的课程，如果该课程是
其他某个课程的先修课程，那么此某个课程的先修课程即入度-1.如果入度减为0则入度为0的课程数+1，此某个课程入queue。由于必须是入度为0的点
才能counter+1，所以最后如果counter不等于课程总数则说明有环存在。
*/
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null){
            return false;
        }
        if (prerequisites.length == 0 || numCourses == 0){
            return true;
        }
        // 存储某门课有几门先修课程的数组
        int[] counter = new int[numCourses];
        // 遍历一遍记录各门课程的先修课程数
        for (int i = 0; i < prerequisites.length; i++){
            counter[prerequisites[i][0]]++;
        }
        // queue存储没有先修课程的
        Queue<Integer> queue = new LinkedList<Integer>();
        // 将初始没有先修课程的入queue
        for (int i = 0; i < counter.length; i++){
            if (counter[i] == 0){
                queue.offer(i);
            }
        }

        int nonPreNum = queue.size();
        while (!queue.isEmpty()){
            int top = queue.poll();
            // 如果有某门课程是以top这门课程为先修课程，将该某门课程的入度-1.如果减为0则入queue
            for (int i = 0; i < prerequisites.length; i++){
                if (prerequisites[i][1] == top){
                    counter[prerequisites[i][0]]--;
                    if (counter[prerequisites[i][0]] == 0){
                        nonPreNum++;
                        queue.offer(prerequisites[i][0]);
                    }
                }
            }
        }

        return nonPreNum == numCourses;
    }
}
