/*
Given a rotated sorted array, recover it to sorted array in-place.

Example
[4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]

Challenge Expand
In-place, O(1) extra space and O(n) time.

Clarification Expand
What is rotated array:

- For example, the orginal array is [1,2,3,4], The rotated array of it
can be [1,2,3,4], [2,3,4,1], [3,4,1,2], [4,1,2,3]
*/

// 思路是用三步翻转法：第一步找到分割点，第二步是对各分部进行翻转，第三步是对整体进行翻转
// O（1）空间复杂度可以理解为没有开辟新的数据结构，利用几个变量完成，递归也可以算，因为递归是利用栈空间
// 计算机分配给每个程序的栈空间是很小的，堆空间是对应实际的内存大小
public class Solution {
    /**
     * @param nums: The rotated sorted array
     * @return: The recovered sorted array
     */
    public void recoverRotatedSortedArray(ArrayList<Integer> nums) {
        // write your code
        for (int index = 0; index < nums.size() - 1; index++){
            if (nums.get(index) > nums.get(index + 1)){
                reverse(nums, 0, index);
                reverse(nums, index + 1, nums.size() - 1);
                reverse(nums, 0, nums.size() - 1);
            }
        }
    }

    public void reverse(ArrayList<Integer> nums, int start, int end){
        for (int i = start, j = end; i < j; i++, j--){
            int temp = nums.get(i);
            nums.set(i, nums.get(j));
            nums.set(j, temp);
        }
    }
}

/*
拓展1：
Rotate String: abcdefg, offset=3 -> efgabcd
还是用三步翻转法，index = nums[0 + offset]
*/

/*
拓展2：
Reverse Words List: I love you -> you love I, LC原题
三步翻转法，找到空格点当index，翻转各分部再整体翻
*/
