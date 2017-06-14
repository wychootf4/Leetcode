/*
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array A = [1,1,2],

Your function should return length = 2, and A is now [1,2].
*/
// Tag: Array, Two Pointers
// Company: Microsoft, Bloomberg, Facebook

/*
Solution1: 利用双指针，slow指向最后一个不重复的，fast向前找到第一个不重复的，然后slow后面的一位置为fast当前所指元素，
然后fast继续向后扫，slow始终是指向最后一个不重复的，直到遍历完所有元素。由于slow是指向最后一个不重复的，所以要求长度的话应该
是slow + 1
时间复杂度为O(n),空间复杂度为O(1).
 */
public class Solution {
    public int removeDuplicates(int[] A) {
        if (A == null || A.length == 0){
            return 0;
        }
        // slow指向不重复的元素的最后一位
        int slow = 0;
        // fast向前扫描，如果fast找到第一个不等于slow的元素，将这个元素挪到slow + 1位置，同时
        // ++slow已经把slow向前移了一位，这样slow仍然指向最后一个不重复的元素
        for (int fast  = 0; fast < A.length; fast++){
            if (A[fast] != A[slow]){
                A[++slow] = A[fast];
            }
        }
        // slow指向最后一个不重复的元素，但是返回的是数组长度，则应该角标加1
        return slow + 1;
    }
}

/*
Solution2: 自己做的方法，维护一个length变量，第一个元素直接加入新数组，然后遇到重复元素就跳过，不是重复的就加到新数组中，
其index为length+1.
时间复杂度为O(n),空间复杂度为O(1).
 */
public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }

        int length = 0;
        for (int i = 0; i < nums.length; i++) {
            // 去除重复元素
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            nums[length++] = nums[i];
        }

        return length;
    }
}
