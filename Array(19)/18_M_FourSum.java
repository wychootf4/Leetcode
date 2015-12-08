/*
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all
unique quadruplets in the array which gives the sum of target.

Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.
    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)
*/
// Tag: Array, Hash Table, Two Pointers


// 思路1：外面双层循环，里面2sum，时间复杂度O(n^3)
/*
主要思路：外面双层循环分别定位第一个和第二个数，最后两个数用2sum解决
*/
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4){
            return result;
        }

        Arrays.sort(nums);
        // 定位第一个数
        for (int i = 0; i < nums.length - 3; i++){
            // 跳过重复
            if (i != 0 && nums[i] == nums[i - 1]){
                continue;
            }
            // 定位第二个数
            for (int j = i + 1; j < nums.length - 2; j++){
                // 跳过重复
                if (j !=  i + 1 && nums[j] == nums[j - 1]){
                    continue;
                }
                int start = j + 1;
                int end = nums.length - 1;
                while (start < end){
                    // 内层while loop实际就是two sum
                    int sum = nums[i] + nums[j] + nums[start] + nums[end];
                    if (sum == target){
                        List<Integer> path = new ArrayList<Integer>();
                        path.add(nums[i]);
                        path.add(nums[j]);
                        path.add(nums[start]);
                        path.add(nums[end]);
                        result.add(path);
                        start++;
                        end--;
                        // 别忘记跳过重复
                        while (start < end && nums[start] == nums[start - 1]){
                            start++;
                        }
                        while (start < end && nums[end] == nums[end + 1]){
                            end--;
                        }
                    }else if (sum < target){
                        start++;
                    }else{
                        end--;
                    }
                }
            }
        }

        return result;
    }
}

// 思路2：用哈希表存2sum的对，key是sum值，value是一个list，存的是原始位置。
