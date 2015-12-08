/*
Given an array of integers that is already sorted in ascending order, find two numbers such that they add up
to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1
must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/
// Tag: Array, Two Pointers, Binary Search

/*
主要思路：由于这里数组已经排序了，所以可以利用窗口法完成，从头尾两个指针扫，如果头尾和小于target则头向后移，如果大于target则尾向前移。
*/
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] ans = new int[2];
        if (numbers == null || numbers.length == 0){
            return ans;
        }

        int head = 0;
        int tail = numbers.length - 1;
        while (head < tail){
            if (numbers[head] + numbers[tail] > target){
                tail--;
            }else if (numbers[head] + numbers[tail] < target){
                head++;
            }else{
                ans[0] = head + 1;
                ans[1] = tail + 1;
                return ans;
            }
        }

        return ans;
    }
}
