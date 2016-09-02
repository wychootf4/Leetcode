/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the
relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be
[1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*/
// Company: Bloomberg, Facebook
// Tag: Array, Two Pointers
/*
思路：遍历数组如果当前数不是0，则找到后面第一个不是0的数与之交换
*/
// Solution1
public class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        // 最后一个数不用管，如果是0就放在那里，如果不是0的话证明之前的操作都没能把它交换走，则整个
        // 数组中没有0
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != 0) {
                continue;
            } else {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] == 0) {
                        continue;
                    } else {
                        int temp = nums[j];
                        nums[j] = nums[i];
                        nums[i] = temp;
                        // bug1: 找到第一个不是0的数交换后就应该跳出循环
                        break;
                    }
                }
            }
        }
    }
}

// Solution1.1: 还是in place，但时间复杂度降到了O(n)
public class Solution {
    public void moveZeroes(int[] nums) {
        // m代表当前首先需要交换的最左的0的index
        int m = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                // 如果不是首位或是m处不为0(为0则代表待处理的0已经在最后了)，则当前为设为m
                // bug1: m == -1, wrong operator
                if (m == -1 || nums[m] != 0) {
                    m = i;
                }
            } else {
                // 如果当前位非0且有待交换的0
                if (m != -1) {
                    int temp = nums[i];
                    nums[i] = nums[m];
                    nums[m] = temp;
                    // 若m后面没有其他0则与后面一位交换；若后面有其他0则m+1代表待处理的零为后面的一个0
                    m++;
                }
            }
        }
    }
}

// Solution2:这里用two pointers的方法更加巧妙，由于最终的结果就是要把不是0的数按原来的顺序移到前面去，
// 因此维护两个指针，第一个指针指向应该替换的位置，第二个指针指向当前第一个不是0的位置，将第一个指针处
// 赋值后两个指针继续向后扫，直到第二个指针到末尾。而此时第一个指针的后面所有元素都应该设为0
public class Solution {
    /**
     * @param nums an integer array
     * @return nothing, do this in-place
     */
    public void moveZeroes(int[] nums) {
        // Write your code here
        int i = 0;
        int j = 0;
        while (j < nums.length) {
            if (nums[j] == 0) {
                j++;
            } else {
                nums[i++] = nums[j++];
            }
        }

        while (i < nums.length) {
            nums[i++] = 0;
        }
    }
}
