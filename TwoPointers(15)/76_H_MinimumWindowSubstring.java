/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in
complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
*/
// Tag: Hash Table, Two Pointers, String

public class Solution {
    /**
     * @param source: A string
     * @param target: A string
     * @return: A string denote the minimum window
     *          Return "" if there is no such a string
     */
    public String minWindow(String source, String target) {
        // write your code
        if (source == null || target == null || source.length() == 0 || target.length() == 0){
            return "";
        }
        // 先建立一个哈希表将字典串的字母以及出现次数保存下来
        Map<Character, Integer> tCounter = new HashMap<Character, Integer>();
        for (int i = 0; i < target.length(); i++){
            char c = target.charAt(i);
            if (!tCounter.containsKey(c)){
                tCounter.put(c, 1);
            }else{
                tCounter.put(c, tCounter.get(c) + 1);
            }
        }
        // 再建立一个哈希表寻找匹配串中的最小窗口
        Map<Character, Integer> minWindowCounter = new HashMap<Character, Integer>();
        int tcount = 0;
        int leftbound = 0;
        String minWindow = null;
        // 遍历匹配串
        for (int i = 0; i < source.length(); i++){
            char c = source.charAt(i);
            // 如果字典中根本没有当前字符，直接跳过
            if (!tCounter.containsKey(c)){
                continue;
            }
            // 否则在最小串口哈希表中记录下当前字符
            if (!minWindowCounter.containsKey(c)){
                minWindowCounter.put(c, 1);
            }else{
                minWindowCounter.put(c, minWindowCounter.get(c) + 1);
            }
            // 如果该字符在最小窗口中不是多余的，tcount加一，直到等于字典串长度时进行处理
            if (minWindowCounter.get(c) <= tCounter.get(c)){
                tcount++;
            }
            // 进行处理时需要得到最小窗口的左边界,因为当前i就是右边界
            if (tcount == target.length()){
                // 将左边界移动到最小窗口的起始位置
                while (leftbound < source.length()){
                    char ch = source.charAt(leftbound);
                    // 先将左边界移动到符合字典条件的首个字符处
                    if (!tCounter.containsKey(ch)){
                        leftbound++;
                        continue;
                    }
                    // 如果当前字符符合字典条件但是出现次数超过字典串中要求的次数，跳过
                    if (minWindowCounter.get(ch) > tCounter.get(ch)){
                        leftbound++;
                        minWindowCounter.put(ch, minWindowCounter.get(ch) - 1);
                        continue;
                    }
                    break;
                }
                // 更新minWindow
                if (minWindow == null || i - leftbound + 1 < minWindow.length()){
                    minWindow = source.substring(leftbound, i + 1);
                }
            }
        }

        if (minWindow == null){
            return "";
        }
        return minWindow;
    }
}
