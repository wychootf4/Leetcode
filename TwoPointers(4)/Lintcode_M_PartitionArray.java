/*
Given an array "nums" of integers and an int "k", Partition the array (i.e move the elements in "nums") such that,

* All elements < k are moved to the left

* All elements >= k are moved to the right

Return the partitioning Index, i.e the first index "i" nums[i] >= k.

Note
You should do really partition in array "nums" instead of just counting the numbers of integers smaller than k.

If all elements in "nums" are smaller than k, then return "nums.length"

Example
If nums=[3,2,2,1] and k=2, a valid answer is 1.

Challenge
Can you partition the array in-place and in O(n)?
*/

public class Solution {
    /**
    *@param nums: The integer array you should partition
    *@param k: As description
    *return: The index after partition
    */
    public int partitionArray(ArrayList<Integer> nums, int k) {
        //write your code here
        int len = nums.size();
        if (len == 0){
            return 0;
        }

        int i = 0;
        int j = len - 1;

        while (i <= j){
            while (i <= j && nums.get(i) < k){
                i++;
            }
            while (i <= j && nums.get(j) >= k){
                j--;
            }
            if (i < j){
                int temp = nums.get(i);
                nums.set(i, nums.get(j));
                nums.set(j, temp);
            }
        }

        return i;
    }
}
