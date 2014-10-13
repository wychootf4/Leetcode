/*
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
*/

public class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        // 判断输入字符串的长度是否符合IP地址的长度定义
        if (s.length() < 4 || s.length() > 12){
            return result;
        }

        List<String> path = new ArrayList<String>();
        addrHelper(result, s, path, 0);
        return result;
    }

    public void addrHelper(List<String> result, String s, List<String> path, int pos){
        // 初始不可能执行，后面每次执行到这里时不合法的情况已经被外层递归for循环中的两个判断条件过滤了
        // 因此各段语义合法的IP地址段如果为4，并且pos指向s.length()则证明已经判定完整个字符串，将此合法串处理后加入result
        // 由于pos是从0开始，pos指向s.length() - 1时已经检查完整个字符串，此时进入内层循环时pos + 1，因此
        // 此时如果pos == s.length()则证明已经判定完整个字符串
        if (path.size() == 4 && pos == s.length()){
            result.add(convertPath(path));
            return;
        }

        for (int i = pos; i < s.length(); i++){
            // 判定当前子串是否满足小于255
            if (Integer.parseInt(s.substring(pos, i + 1)) > 255){
                return;
            }
            // 判定当前子串是不是以0开头并且长度大于1
            if (s.substring(pos, i + 1).length() > 1 && s.substring(pos, i + 1).startsWith("0")){
                return;
            }
            path.add(s.substring(pos, i + 1));
            addrHelper(result, s, path, i + 1);
            path.remove(path.size() - 1);
        }
    }
    // 处理合法的字符串地址将其转换为IP地址格式
    public String convertPath(List<String> path){
        StringBuilder sb = new StringBuilder();
        for (String str : path){
            sb.append(str + ".");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }
}
