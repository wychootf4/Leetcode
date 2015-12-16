import java.util.ArrayList;
import java.util.List;

/*
Given an array of words and a length L, format the text such that each line has exactly L characters

and is fully (left and right) justified. You should pack your words in a greedy approach; that is,

pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line

has exactly L characters. Extra spaces between words should be distributed as evenly as possible.

If the number of spaces on a line do not divide evenly between words, the empty slots on the left

will be assigned more spaces than the slots on the right. For the last line of text, it should be

left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.

Corner Cases:
A line other than the last line might contain only one word. What should you do in this case?
In this case, that line should be left-justified.
*/
// Tag: String
public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<String>();
        if (words == null || maxWidth < 0) {
            return res;
        }
        // 标记处理到哪个单词
        int startIndex = 0;
        while (startIndex < words.length) {
            int charCount = maxWidth;
            int endIndex = startIndex;
            // 对于每个仍能放入本行的单词
            for (int i = startIndex; i < words.length && words[i].length() <= charCount; i++) {
                charCount -= words[i].length();
                // 计算该单词后面跟着的空格
                charCount--;
                endIndex = i;
            }
            // 收回最后一个空格,最后统一处理
            charCount++;
            // 计算本行放了几个单词
            int wordCount = endIndex - startIndex + 1;

            int extraSpace = 0;
            int firstExtra = 0;
            // 如果单词数大于1,分配空格,否则都加到最后即可
            if (wordCount > 1) {
                // n个单词中间有n-1个空可以加空格,每个单词后面多余可加的空格数
                extraSpace = charCount / (wordCount - 1);
                // 均分后富裕出来的空格数
                firstExtra = charCount % (wordCount - 1);
            }

            StringBuilder sb = new StringBuilder();
            // 对于本行的单词
            for (int i = startIndex, i <= endIndex; i++) {
                sb.append(words[i]);
                int spaceCount = 0;
                // 如果是最后一个单词后面不加空格
                if (i == endIndex) {
                    break;
                }
                // 如果不是最后一行
                if (endIndex != words.length - 1) {
                    if (firstExtra > 0) {
                        spaceCount++;
                        firstExtra--;
                    }
                    // 不是最后一个单词,可以加额外空格
                    spaceCount += extraSpace;
                }

                // 1是每个单词后面本来要加的空格
                appendSpace(sb, spaceCount + 1);
            }

            // 最后一行的尾部的空格,或是只有一个单词的情况
            appendSpace(sb, maxWidth - sb.length());

            res.add(sb.toString());
            startIndex = endIndex + 1;
        }

        return res;
    }

    public void appendSpace(StringBuilder sb, int spaceCount) {
        while (spaceCount > 0) {
            sb.append(' ');
            spaceCount--;
        }
    }
}
