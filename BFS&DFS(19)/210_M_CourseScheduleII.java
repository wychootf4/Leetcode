/*
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is
expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should
take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all
courses, return an empty array.

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course
order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]
There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both
courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another
correct ordering is[0,2,1,3].

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a
graph is represented.

Hints:
This problem is equivalent to finding the topological order in a directed graph. If a cycle exists, no
topological ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of
Topological Sort.
Topological sort could also be done via BFS.
*/
// Tag: DFS, BFS, Graph, Topological Sort

/*
还是和上一题一样的思路，只是要加上结果数组记录过程。
*/
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null){
            return new int[0];
        }
        if (numCourses == 0 || prerequisites.length == 0){
            int[] result = new int[numCourses];
            for (int i = 0; i < prerequisites.length; i++){
                result[i] = i;
            }
        }

        int[] counter = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++){
            counter[prerequisites[i][0]]++;
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < counter.length; i++){
            if (counter[i] == 0){
                queue.offer(i);
            }
        }

        int nonPreNum = queue.size();
        int[] result = new int[numCourses];
        int retIndex = 0;
        while (!queue.isEmpty()){
            int top = queue.poll();
            // top是没有先修课程的点，可以加入结果
            result[retIndex++] = top;
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

        if (nonPreNum == numCourses){
            return result;
        }else{
            return new int[0];
        }
    }
}
