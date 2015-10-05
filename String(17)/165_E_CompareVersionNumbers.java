/*
Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of
the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37
*/
// Tag: String

// 注意1和1.0是相同的。首先将两个字符串的版本号分割开，然后同时检查一组版本号，如果某个字符串已经检查完而另一个没完且版本号不是0则该
// 字符串比较大。
public class Solution {
    public int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null){
            return 0;
        }

        String[] str1 = version1.split("\\.");
        String[] str2 = version2.split("\\.");
        int i = 0;
        while (i < str1.length || i < str2.length){
            if (i < str1.length && i < str2.length){
                if (Integer.parseInt(str1[i]) > Integer.parseInt(str2[i])){
                    return 1;
                }else if (Integer.parseInt(str1[i]) < Integer.parseInt(str2[i])){
                    return -1;
                }
            }else if (i < str1.length){
                if (Integer.parseInt(str1[i]) != 0){
                    return 1;
                }
            }else if (i < str2.length){
                if (Integer.parseInt(str2[i]) != 0){
                    return -1;
                }
            }
            i++;
        }

        return 0;
    }
}
