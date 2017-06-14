/*
Given an array and a value, remove all instances of that value in place and return the new length.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

Example:
Given input array nums = [3,2,2,3], val = 3

Your function should return length = 2, with the first two elements of nums being 2.
*/
// Tag: Array, Two Pointers

/*
Solution1:利用快慢指针，慢指针指向最后一个合法的元素，快指针向前扫描不为val的元素。
时间复杂度为O(n),空间复杂度为O(1).
 */
public class Solution {
    public int removeElement(int[] nums, int val) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }

        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
        }

        return slow;
    }
}

/*
Solution2：
利用两个指针，left从最左边起代表当前需要检查的位，right从最右边起记录新的长度.这个解法对于需要删掉很少val的情况可以优化
算法的速度，比如[1,2,3,4,5] and val=5, 就不需要再copy 1-4.
时间复杂度为O(n),空间复杂度为O(1).
*/
public class Solution {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0){
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            // 如果当前检查位是要找的元素，拿right指向的元素代替它，同时right--，由于还要检查从right替换过来的元素，所以left在原处
            if (nums[left] == val){
                nums[left] = nums[right];
                // 数组长度减1
                right--;
            }else{
            // 当前位检查过不是要找的元素，继续检查下一位
                left++;
            }
        }
        // 由于数组的index从0开始，所以实际长度为right + 1
        return right + 1;
    }
}
