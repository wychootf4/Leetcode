/*
Given an array of integers, the majority number is the number that occurs more than 1/3 of the size of the array.

Find it.

Example
Given [1, 2, 1, 2, 1, 3, 3], return 1.

Note
There is only one majority number in the array.

Challenge
O(n) time and O(1) extra space.
*/
// Tag: Greedy, Enumeration

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: The majority number that occurs more than 1/3
     */
    public int majorityNumber(ArrayList<Integer> nums) {
        // write your code
        if (nums == null || nums.size() == 0){
            return -1;
        }
        // 进行三三抵消，同时记录两个candidate。如果当前的元素与记录的两个都不同，就把
        // 三个数都抵消掉，那么最后主元素肯定在最后count > 0的两个数中产生
        int candidate1 = 0;
        int count1 = 0;
        int candidate2 = 0;
        int count2 = 0;

        for (int i = 0; i < nums.size(); i++){
            if (candidate1 == nums.get(i)){
                count1++;
            }else if (candidate2 == nums.get(i)){
                count2++;
            }else if (count1 == 0){
                candidate1 = nums.get(i);
                count1++;
            }else if (count2 == 0){
                candidate2 = nums.get(i);
                count2++;
            }else{
                count1--;
                count2--;
            }
        }

        // 最后遍历一遍数组取出出现次数多的元素，不能直接比较count1和count2，因为有可能
        // 实际的主元素被抵消掉很多次反而更小
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < nums.size(); i++){
            if (candidate1 == nums.get(i)){
                count1++;
            }
            if (candidate2 == nums.get(i)){
                count2++;
            }
        }

        return (count1 > count2) ? candidate1 : candidate2;
    }
}
