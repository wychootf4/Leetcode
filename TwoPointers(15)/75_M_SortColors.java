/*
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?
*/
// Tag: Array, Two Pointers, Sort

// 普通思路可以用radix sort先统计各个颜色出现的次数，然后再循环一次进行处理。
public class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0){
            return;
        }

        int red = 0;
        int white = 0;
        int blue = 0;

        for (int i = 0; i < nums.length; i++){
            if (nums[i] == 0){
                red++;
            }else if (nums[i] == 1){
                white++;
            }else{
                blue++;
            }
        }

        for (int i = 0; i < nums.length; i++){
            if (red > 0){
                nums[i] = 0;
                red--;
            }else if (white > 0){
                nums[i] = 1;
                white--;
            }else{
                nums[i] = 2;
                blue--;
            }
        }
    }
}

// 还可以分别卡着非0和非2，用一个指针遍历一遍数组，如果当前值是0就交换到第一个非零的位置，非零指针向前走一位；如果当前值是1则不用动，
// 继续向下遍历；如果当前值是2则与第一个非2的位置进行交换，且非2指针向前走一位，由于不能确定从非2位置交换过来的元素是否还要向左交换，因此
// 暂时不移动curr指针，这样在下一次循环的时候新被交换过来的元素可以在curr指针下进行检查
public class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1){
            return;
        }

        int left = 0;
        int right = nums.length - 1;
        int curr = 0;
        while (curr <= right){
            if (nums[curr] == 0){
                swap(nums, curr, left);
                left++;
                curr++;
            }else if (nums[curr] == 1){
                curr++;
            }else{
                swap(nums, curr, right);
                right--;
            }
        }
    }

    private void swap(int[] nums, int num1, int num2){
        int temp = nums[num1];
        nums[num1] = nums[num2];
        nums[num2] = temp;
    }
}
