/*
Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Write a function to determine if a given target is in the array.
*/
// Tag: Array, Binary Search

// 最坏情况是1111111111111111，找2的情况。假设2先拿出来然后是要在循环最后才被确定位置加进去，则至少检查了n - 1个元素
// 时间复杂度从O(logn)变为O(n)
// SearchInRotatedSortedArrayI里面start，end和mid三个数都不一样可以判定mid在哪边
// 如果本题中有两个数一样，比如start和mid一样，如果target < A[start],则mid在case2，还是可以减小规模
public class Solution {
    public boolean search(int[] A, int target) {
        for (int i = 0; i < A.length; i++){
            if (A[i] == target){
                return true;
            }
        }
        return false;
    }
}

// 自己做的用二分法，但是此种情况由于排序时间复杂度是O(n),所以和直接遍历查找是一样的
/*
public class Solution {
    public boolean search(int[] A, int target) {
        Arrays.sort(A);
        int start = 0;
        int end = A.length - 1;
        int mid;

        while (start + 1 < end){
            mid = start + (end - start) / 2;
            if (A[mid] == target){
                return true;
            }else if (A[mid] < target){
                start = mid;
            }else{
                end = mid;
            }
        }

        if (A[end] == target){
            return true;
        }
        if (A[start] == target){
            return true;
        }
        return false;
    }
}
*/
