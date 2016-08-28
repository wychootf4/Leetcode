/*
Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
Assume that the total area is never beyond the maximum possible value of int.
*/
// Tag: Math
/*
思路：通过坐标判断，如果没有重叠则将两个矩形面积相加；若有重叠区域则将重叠区域减去
*/
public class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        // no overlapping area
        if ((C <= E) || (G <= A) || (B >= H) || (F >= D)) {
            return (C - A) * (D - B) + (G - E) * (H - F);
        }

        // have overlapping area, two sum minus overlapping area
        int leftBottomX = (A == E) ? A : Math.max(A, E);
        int leftBottomY = (B == F) ? B : Math.max(B, F);
        int rightTopX = (C == G) ? C : Math.min(C, G);
        int rightTopY = (D == H) ? D : Math.min(D, H);

        return (C - A) * (D - B) + (G - E) * (H - F) - (rightTopX - leftBottomX) * (rightTopY - leftBottomY);
    }
}
