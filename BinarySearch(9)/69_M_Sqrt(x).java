/*
Implement int sqrt(int x).

Compute and return the square root of x.
*/
// Tag: Math, Binary Search

class Solution {
    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        // write your code here
        if (x == 0 || x == 1){
            return x;
        }

        long start = 1;
        long end = x;
        long mid;
        while (start + 1 < end){
            mid = start + (end - start) / 2;
            if (mid * mid == x){
                return (int)mid;
            }else if (mid * mid < x){
                start = mid;
            }else {
                end = mid;
            }
        }

        return (int)start;
    }
}
/*
就是当循环终止的时候，l一定是偏小，r一定是偏大（实际的值是介于l和r之间的）：

比如以下的例子，90开根号是9.48 按照开方向下取整的原则, 我们应该返回L.

以下展示了在循环过程中，L,R两个变量的变化过程

1. System.out.println(sqrt(90));

L  R

1 45

1 23

1 12

6 12

9 12

9 10

9

2. System.out.println(sqrt(20));

1 10

1 5

3 5

4 5

4

3. System.out.println(sqrt(3));

1 2
*/
// 还有一种方法是用牛顿法，但是算法性体现的比较少
