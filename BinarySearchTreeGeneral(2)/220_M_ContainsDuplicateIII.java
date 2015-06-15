/*
Given an array of integers, find out whether there are two distinct indices i and j in the array such that the
difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
*/
// Tag: Binary Search Tree

/*
分析：
维护一个长度为k的window，遍历数组看当前元素是否与window中某个元素的差值小于等于t，如果有则返回true。
时间复杂度：O(n)，空间复杂度:O(k)
*/
import java.util.SortedSet;
public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // check edge case
        if (k < 1 || t < 0 || nums == null || nums.length < 2){
            return false;
        }

        SortedSet<Long> set = new TreeSet<Long>();
        for (int i = 0; i < nums.length; i++){
            // nums[i]为当前元素，确定上限为t的左右边界，确定左右边界的子集，如果子集不为空证明窗口中存在与当前元素差值小于
            // 等于t的元素。
            long leftBound = (long)nums[i] - t;
            long rightBound = (long)nums[i] + t + 1;
            SortedSet<Long> subSet = set.subSet(leftBound, rightBound);

            if (!subSet.isEmpty()){
                return true;
            }
            // 然后将当前元素加入窗口，如果窗口长度已经超过k了则删除当前窗口最前的一个元素
            set.add((long)nums[i]);
            if (i >= k){
                set.remove((long)nums[i - k]);
            }
        }

        return false;
    }
}
