/*
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters
left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function will only be called once for each test case.
*/
// Tag: String

// 刚开始没有搞懂，此题题目的意思是用read4来实现readn。read4(buf)函数是读4个字符存入buf中，返回实际存入的字符数.
/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        // 先用一个小buffer存入数据
        char[] buffer = new char[4];
        int readBytes = 0;
        boolean eof = false;
        // 有可能最后一次读出的buffer长度为4，所以要在判断条件中加上readBytes < n, 否则会无限循环
        while (!eof && readBytes < n){
            // 从文件中读取4个字符存入buffer，并返回实际读取的字符数为temp
            int temp = read4(buffer);
            // 如果文件字符数少于4则标记为文件末尾处
            if (temp < 4){
                eof = true;
            }
            // 为了避免buf存入比n更大的数溢出，比如n为1，temp为2时，应该只存入一个字符
            int bytes = Math.min(n - readBytes, temp);
            // 将数据从临时buffer存到实际buf中，分别对应src，srcindex，dst，dstindex，length
            System.arraycopy(buffer, 0, buf, readBytes, bytes);
            readBytes += bytes;
        }

        return readBytes;
    }
}
