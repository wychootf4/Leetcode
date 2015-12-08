/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets
in the array which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.
    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
*/
// Tag: Array, Two Pointers

/*
主要思路：题目需要找到所有和为0的三元组而不是求出其index，所以可以先对数组进行排序,然后枚举第一个数，然后利用头尾指针寻找后两个数，如果能
找到和为0则加入result中，如果和小于0则头指针向后移，如果和大于0则尾指针向前移。
*/
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 3){
            return result;
        }
        // 首先进行排序
        Arrays.sort(nums);

        // 然后枚举三个数中的第一个数,由于是三元组且为升序，所以首个数的index至多为nums.length-3
        for (int i = 0; i < nums.length - 2; i++){
            // 去掉重复元素，像是[0, 0, 0, 0]，这里考虑应该再加上nums[i] != 0的条件，否则所有都为0的情况不能被执行
            if (i != 0 && nums[i] == nums[i - 1]){
                continue;
            }

            // 然后就是用头尾指针寻找后面的两个数
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end){
                int sum = nums[i] + nums[start] + nums[end];
                if (sum == 0){
                    List<Integer> path = new ArrayList<Integer>();
                    path.add(nums[i]);
                    path.add(nums[start]);
                    path.add(nums[end]);
                    result.add(path);
                    start++;
                    end--;
                    // 去掉重复元素
                    while (start < end && nums[start] == nums[start - 1]){
                        start++;
                    }
                    while (start < end && nums[end] == nums[end + 1]){
                        end--;
                    }
                }else if (sum > 0){
                    end--;
                }else{
                    start++;
                }
            }
        }

        return result;
    }
}
