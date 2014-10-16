/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
*/

public class Solution {
    public int findMin(int[] num) {
        int start = 0;
        int end = num.length - 1;
        int mid;
        // 这是单纯的sorted array
        if (num[start] <= num[end]){
            return num[start];
        }
        // 两段式的情况,最小值在第二段的最下面
        while (start + 1 < end){
            mid = start + (end - start) / 2;
            // mid值小于第一个点，说明mid在下面一段，则min在start和mid之间，令end = mid
            if (num[mid] < num[start]){
                end = mid;
            // mid值大于第一个点，说明mid在上面一段，则min在mid和end之间，令start = mid
            }else{
                start = mid;
            }
        }
        // 经过循环最后min在start和end两个点取到，取出两个点中小者
        if (num[start] < num[end]){
            return num[start];
        }
        return num[end];
    }
}
