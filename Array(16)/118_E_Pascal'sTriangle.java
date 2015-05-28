/*
Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/
// Tag: Array

public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (numRows == 0){
            return result;
        }
        // i代表行
        for (int i = 0; i < numRows; i++){
            List<Integer> line = new ArrayList<Integer>();
            // j代表某行的每一个元素
            // 除了第一行外，每行的首尾元素都是1，其他元素是上一排的上面和上面的左边一个元素
            for (int j = 0; j <= i; j++){
                if (j == 0 || j == i){
                    line.add(1);
                }else{
                    int sum = result.get(i - 1).get(j) + result.get(i - 1).get(j - 1);
                    line.add(sum);
                }
            }

            result.add(line);
        }

        return result;
    }
}
