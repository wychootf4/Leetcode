/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
*/
// Tag: Array

/*
分析：
一种简单的方法是对于每个数扫一遍，找到上下界，但是这样时间复杂度太高；或者先排序再找，时间复杂度降到O(nlogn)，空间复杂度为O(1)。
但是还可以通过优化去掉重复检查过的状态,时间复杂度降到O(n),空间复杂度也是。
*/

public class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        // key是数组中的某一个元素，value为标志位，0代表未被用过，1代表已被用过
        Map<Integer, Integer> hs = new HashMap<Integer, Integer>();
        for (int i : nums){
            hs.put(i, 0);
        }
        // 无论取哪个元素，最长连续至少为1
        int maxLength = 1;

        for (int i : nums){
            int tmp = i;
            int currentMax = 1;
            // 如果当前元素已经被用过
            if (hs.get(tmp) == 1){
                continue;
            }
            // 如果当前的tmp向后扫一位能够找到，将其标志位设为1，继续向后扫
            while (hs.containsKey(tmp + 1)){
                tmp++;
                currentMax++;
                hs.put(tmp, 1);
            }
            // 同理向前扫，但扫之前先把tmp挪回i
            tmp = i;
            while (hs.containsKey(tmp - 1)){
                tmp--;
                currentMax++;
                hs.put(tmp, 1);
            }

            maxLength = Math.max(maxLength, currentMax);
        }

        return maxLength;
    }
}
