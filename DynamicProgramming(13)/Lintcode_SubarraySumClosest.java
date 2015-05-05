/*
Given an integer array, find a subarray with sum closest to zero. Return the indexes of the first number and
last number.

Example
Given [-3, 1, 1, -3, 5], return [0, 2], [1, 3], [1, 1], [2, 2] or [0, 4]

Challenge
O(nlogn) time
*/
// Tag: Array, Sort

// 建立一个新的数据类型存某个数据的sum和index
class Element implements Comparable<Element>{
    int value;
    int index;
    public Element(int value, int index){
        this.value = value;
        this.index = index;
    }
 
    public int compareTo(Element other){
        return this.val - other.val;
    }

    public int getIndex(){
        return index;
    }

    public int getValue(){
        return val;
    }
}

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number
     *          and the index of the last number
     */
    public ArrayList<Integer> subarraySumClosest(int[] nums) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (nums == null || nums.length == 0){
            return res;
        }

        Element[] sums = new Element[nums.length + 1];
        // 设立一个dummy node，便于计算需要减去头元素的前一个元素的情况，假定dummy node的index为-1
        sums[0] = new Element(0, -1);
        int sum = 0;
        // 初始化所有位置的sum
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            sums[i + 1] = new Element(sum, i);
        }
        // 将sums进行排序
        Arrays.sort(sums);
        int min = Integer.MAX_VALUE;
        int start =  0;
        int end = 0;
        // 比较相邻两个sum的差值，维护最小的差值，随之更新start和end两个index
        for (int i = 0;i < nums.length;i++){
            int diff = Math.abs(sums[i].getValue() - sums[i + 1].getValue());
            // 如果当前两个sum的差值更小，取二者之小 + 1作为起始的index，因为减的时候是比实际index向前一位的
            if (diff < min){
                min = diff;
                start = Math.min(sums[i].getIndex(), sums[i + 1].getIndex()) + 1;
                end  = Math.max(sums[i].getIndex(), sums[i + 1].getIndex());
            }
        }

        res.add(start);
        res.add(end);
        return res;
    }
}
