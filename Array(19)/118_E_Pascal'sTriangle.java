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

/*主要思路：利用两层循环，外层循环为行数，内层循环为各行的具体分布情况。根据规律可以得出除去第一行只有1之外，其余各行首位元素为1，其他元素为
上一排相对应的元素以及其左边的元素之和，例如第三行第二个元素 2 = 第二行第二个元素+第二行第一个元素 = 1+1 = 2。每行计算完毕后将整行结果存入
result中。
*/
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
