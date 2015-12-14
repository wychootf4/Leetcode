/*
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
*/
// Tag: Array, Sort
/*
Solution:依次遍历list,用一个index维护最后新的interval需要插入的位置.如果当前interval整体小于newInterval,
由于要将newInterval插入其后,index+1,并将当前interval加入解集;如果当前interval整体大于newInterval,不用更新
index,将当前interval加入解集,newInterval应该在最前;否则有重叠,更新newInterval的范围.
整体时间复杂度应为O(N)
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        if (intervals == null || newInterval == null) {
            return result;
        }

        int insertPos = 0;
        Iterator<Interval> it = intervals.iterator();

        while (it.hasNext()) {
            Interval curInterval = it.next();
            // 待插入在当前interval的后面
            if (curInterval.end < newInterval.start) {
                result.add(curInterval);
                insertPos++;
                // 待插入在当前interval的前面,由于list已经升序,所以只可能在开始遍历时发生,也就是插在最前面
            } else if (curInterval.start > newInterval.end) {
                result.add(curInterval);
                // 否则合并重叠部分,更新待插入
            } else {
                int start = Math.min(curInterval.start, newInterval.start);
                int end = Math.max(curInterval.end, newInterval.end);
                newInterval = new Interval(start, end);
            }
        }

        result.add(insertPos, newInterval);

        return result;
    }
}