/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to
display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
*/
// Tag: String

/*
分析：
两个规律：
1 两个zigzag之间间距为2*nRows-2
2 每个zigzag中间（在j和j+interval之间）位置为j+interval-2*i
这道题有点数学问题，找到间隔的规律就容易做，算法型和通用型不强。
注意：当Rows = 1时，此方法不适用，因为size = 0,会造成死循环。所以Rows = 1时，需要独立处理。
时间复杂度为O(n^2)，空间复杂度为O(n).
*/
public class Solution {
    public String convert(String s, int numRows) {
        int len = s.length();
        if (len <= numRows || numRows == 1){
            return s;
        }

        char[] chars = new char[len];
        // step是新的划分中每一行的字符间在原字符串中的间隔
        int step = 2 * (numRows - 1);
        int count = 0;
        // 对于新划分的每一行
        for (int i = 0; i < numRows; i++){
            // interval是指在当前step和下一个step间如果需要加入字符其间距是多少
            int interval = step - 2 * i;
            // 对于当前行，从当前起始位置每次增加一个步长, 依次放入当前步长对应的原字符串中的字符
            for (int j = i; j < len; j += step){
                chars[count] = s.charAt(j);
                count++;
                // 如果interval处于0和step之间，且还能放字符，则在下一个step前放入原字符串中相应interval位置的字符
                if (0 < interval && interval < step && j + interval < len && count < len){
                    chars[count] = s.charAt(j + interval);
                    count++;
                }
            }
        }
        // bug: 这里用toString()会直接打印出String的地址，要用new的方式
        return new String(chars);
    }
}
