/*
Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?
*/
// Tag: Array

// 注意这里需要处理的是第几行，不是总的行数，实际对应最后要计算的是k + 1行。这里只需记录上一行的数据，然后从末尾开始计算即可。
// 跳过开头，更新当前位置的值为自身与前一个数的和，实际就是在计算那个公式。
/*
主要思路：注意k实际是从0开始的，外层循环控制行数，内层循环控制每行的具体数据。内层循环采取倒序，每次先将尾部的1加上，然后在计算某个位置的元素时，
由于此时存在result中对应index位置的值就是其上一层对应的值，按照计算公式更新即可。最后首位的1不作处理，因为index为0的值就是第一行的唯一元素1，
不更新则一直为1。
*/
public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i <= rowIndex; i++){
            // 这里如果不这样写则i=0就不能继续了
            for (int j = i; j >= 0; j--){
                // 加入本行新加入末尾的1
                if (j == i){
                    result.add(1);
                // 首位的1不作处理
                }else if (j != 0){
                    result.set(j, result.get(j) + result.get(j - 1));
                }
            }
        }

        return result;
    }
}
