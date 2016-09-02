/*
You are a product manager and currently leading a team to develop a new product. Unfortunately,
the latest version of your product fails the quality check. Since each version is developed
based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which
causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad.
Implement a function to find the first bad version. You should minimize the number of calls
to the API.
*/

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
// Solution1: Iteration
class Solution {
    /**
    * @param n: An integers.
    * @return: An integer which is the first bad version.
    */
    public int findFirstBadVersion(int n) {
        // write your code here
        int start = 1;
        int end = n;
        int mid;

        while (start + 1 < end){
            mid = start + (end - start) / 2;
            if (VersionControl.isBadVersion(mid)){
                end = mid;
            }else{
                start = mid;
            }
        }

        if (VersionControl.isBadVersion(start)){
            return start;
        }

        return end;
    }
}

// Solution2: Recursion
// 用二分法，取中，如果当前点为bad则后面都是，往前找；若当前点不是bad则往后找
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        if (n < 1) {
            return -1;
        }

        return helper(1, n);
    }

    private int helper(int start, int end) {
        if (start == end) {
            return start;
        }
        int mid = start + (end - start) / 2;
        if (isBadVersion(mid)) {
            // 这两个bug适用于任何case因为二分到最后可能都会有两个version待选
            // bug1: 1,2;version1为bad；end要以mid为界
            return helper(start, mid);
        } else {
            // bug2：1,2;version2为bad；start要以mid+1为界
            return helper(mid + 1, end);
        }
    }
}
