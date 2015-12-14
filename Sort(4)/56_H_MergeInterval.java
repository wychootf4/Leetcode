/*
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
*/
// Tag: Array, Sort

/*
Solution:先进行排序,然后将第一个interval设为标志,遍历整个list,如果当前的interval在标志的右边则将标志加入解集,
将当前interval设成新的标志;如果有重叠就合并成新的标志,最后将标志放入解集.
排序的时间复杂度是O(NlogN),merge的时间复杂度是O(n),所以整体为O(NlogN).需要注意的是最好使用iterator遍历,否则
入参若为linkedlist使用get方法会很慢,不能保证是O(1)的时间
 */

import java.util.*;


public class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<Interval>();
        if (intervals == null || intervals.size() == 0) {
            return res;
        }

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        Iterator<Interval> iterator = intervals.iterator();
        // 设为标志位
        Interval last = intervals.get(0);

        while (iterator.hasNext()) {
            Interval interval = iterator.next();
            if (interval.start > last.end) {
                res.add(last);
                last = interval;
            } else {
                int start = last.start;
                int end = Math.max(last.end, interval.end);
                last = new Interval(start, end);
            }
        }

        res.add(last);
        return res;
    }
}