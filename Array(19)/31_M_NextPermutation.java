/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of
numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending
order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand
column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/
// Tag: Array

/*
分析：
题目需要找到下一个permutation，也就是恰好比现在的序列大的序列中最小的一个。
1.所以先要从右向左扫到第一个下降的元素，即dropIndex，可以用单词去类比想象，肯定是要在允许的范围内尽可能的变动右边的元素，
这样才能保证是比现序列大的序列中最小的一个。
2.由于dropIndex后的元素是降序，不可能再增大了，所以要变动dropIndex。还是从右向左扫描找到第一个比dropIndex大的元素与其交换，
这样保证序列最小。
3.交换后由于dropIndex位置的值变大了，相应的后面的序列要整体逆序变为升序，这样才能保证序列最小。
4.如果第1步的时候就没找到dropIndex，直接将序列逆序即可。
*/
public class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0){
            return;
        }

        for (int i = nums.length - 2; i >= 0; i--){
            // 如果当前的比后面的一个小，就找到其后面从右到左第一个比当前元素大的元素，两者交换，然后将当前元素位置
            // 后面的元素全部逆序
            if (nums[i] < nums[i + 1]){
                int j = 0;
                for (j = nums.length - 1; j > i; j--){
                    if (nums[j] > nums[i]){
                        swap(nums, i, j);
                        break;
                    }
                }
                reverse(nums, i + 1, nums.length - 1);
                return;
            }
        }

        reverse(nums, 0, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end){
        while (start < end){
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
