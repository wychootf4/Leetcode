/*
Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to
end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lowercase alphabetic characters.
*/
// Tag: Array, Backtracking, BFS, String

public class Solution {
    /**
      * @param start, a string
      * @param end, a string
      * @param dict, a set of string
      * @return a list of lists of string
      */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // write your code here
        List<List<String>> result = new ArrayList<List<String>>();
        if (dict == null || dict.size() == 0){
            return result;
        }
        // map存储某个点与邻接点的对应关系，distance记录某个点与起始点的变换长度
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Map<String, Integer> distance = new HashMap<String, Integer>();
        // 先用bfs遍历找出点与邻接点的关系，并算出变换长度
        // 先将start和end加入字典在进行bfs时候更方便
        dict.add(start);
        dict.add(end);
        bfs(map, distance, start, end, dict);

        List<String> path = new ArrayList<String>();
        dfs(result, path, map, distance, end, start);

        return result;
    }

    private void bfs(Map<String, List<String>> map, Map<String, Integer> distance, String start,
        String end, Set<String> dict){
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        distance.put(start, 0);
        // 初始化每个点的邻接边都为空
        for (String str : dict){
            map.put(str, new ArrayList<String>());
        }

        while (!queue.isEmpty()){
            String current = queue.poll();
            // 获取当前点的所有在字典中的只替换了一个字符的单词，
            List<String> neighbors = getNeighbors(current, dict);
            // 将这些单词存为current的邻接点
            for (String neighbor : neighbors){
                map.get(neighbor).add(current);
                // 证明当前的邻接点还未被遍历过，是在当前点下面的点,距离+1，并将此邻接点加入queue
                if (!distance.containsKey(neighbor)){
                    distance.put(neighbor, distance.get(current) + 1);
                    queue.offer(neighbor);
                }
            }
        }
    }

    private List<String> getNeighbors(String current, Set<String> dict){
        List<String> neighbors = new ArrayList<String>();

        for (int i = 0; i < current.length(); i++){
            for (char c = 'a'; c <= 'z'; c++){
                if (c == current.charAt(i)){
                    continue;
                }
                String tmp = replace(current, i, c);
                if (dict.contains(tmp)){
                    neighbors.add(tmp);
                }
            }
        }

        return neighbors;
    }

    private String replace(String current, int index, char c){
        char[] chars = current.toCharArray();
        chars[index] = c;

        return new String(chars);
    }
    // 从终点出发，每次找其邻接点，只选取distance小的加入path，直至走回起点
    private void dfs(List<List<String>> result, List<String> path, Map<String, List<String>> map,
        Map<String, Integer> distance, String end, String start){
        path.add(end);
        // 如果end已经遍历到start了就path整体反向，加入解集，再倒回来
        if (end.equals(start)){
            Collections.reverse(path);
            result.add(new ArrayList<String>(path));
            Collections.reverse(path);
        // 如果还没到start就遍历end的邻接点，如果其中某个邻接点能到start并且比end距离短，
        // 就从此邻接点继续做dfs
        }else{
            for (String next : map.get(end)){
                if (distance.containsKey(next) && distance.get(end) == distance.get(next) + 1){
                    dfs(result, path, map, distance, next, start);
                }
            }
        }

        path.remove(path.size() - 1);
    }
}
