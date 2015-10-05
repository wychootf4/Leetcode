/*
Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
*/
// Tag: Array

/*
分析：
维护一个prev指针，判断与curr指针的差如果大于等于2则产生missing range。
*/
public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<String>();
        // 如果数组为空则直接将整体加入结果
        if (nums.length == 0){
            result.add(getRange(lower, upper));
            return result;
        }

        // 初始prev为lower - 1可以避免开头为1，2...时的漏算
        int prev = lower - 1;
        for (int i = 0; i <= nums.length; i++){
            // 同样如果curr到数组末尾时应该为end + 1可以避免结尾为...97,98时的漏算
            int curr = (i == nums.length) ? upper + 1 : nums[i];
            if (curr - prev >= 2){
                result.add(getRange(prev + 1, curr - 1));
            }
            prev = curr;
        }

        return result;
    }

    private String getRange(int start, int end){
        return (start == end) ? String.valueOf(start) : start + "->" + end;
    }
}
