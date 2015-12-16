/*
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
*/
// Tag: Stack, String

import java.util.ArrayList;
import java.util.List;

/*
Solution:
首先需要说明简化路径的依据:
1.遇到/../需要返回上一级目录,需检查上一层目录是否为空
2.遇到/./表示是本级目录,简化冗余
3.遇到//表示是本级目录,简化冗余
4.遇到其他字符表示是文件夹名,无需简化
5.字符串为空或者遇到/../,需要返回一个/
首先将字符串用/进行分割,如果当前字符串为..则去掉前面已经存入path的目录,如果不为.或是空则加入path.然后为path分别加/,
最后判断result是否为空,如果不为空去掉上一步加到最后的/
 */
public class Solution {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0);

        // 用/将path分割,/+代表一个或多个/
        String[] stubs = path.split("/+");
        List<String> list = new ArrayList<String>();

        for (String s : stubs) {
            // 如果为..需要返回上一级目录,则去掉前面已经存入path的目录
            if (s.equals("..")) {
                if (list.size() > 0) {
                    list.remove(list.size() - 1);
                }
                // 如果不为.而且不为空,则加入path,否则不作处理
            } else if (!s.equals(".") && !s.equals("")) {
                list.add(s);
            }
        }

        // 加/
        // 这里用stringbuilder,减少空间的使用量
        StringBuilder sb = new StringBuilder();
        sb.append("/");

        for (String s : list) {
             sb.append(s + "/");
        }
        // 如果结果不为空则去掉加在末尾的/
        if (sb.length() > 1) {
            sb = sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}