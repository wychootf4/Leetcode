/*
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
*/
// Tag: Math, String
// Company: Microsoft, Bloomberg, Uber, Facebook, Yahoo

// 注意罗马数字的组成规则，如果小的字母在大的之前就要减掉，如果在大的之后就要加上
// 时间复杂度是O(n),空间复杂度是O(n).
public class Solution {
    public int romanToInt(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }

        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int len = s.length();
        int result = map.get(s.charAt(len - 1));
        for (int i = len - 2; i >= 0; i--){
            // bug1: 判断条件出错
            if (map.get(s.charAt(i + 1)) <= map.get(s.charAt(i))){
                result += map.get(s.charAt(i));
            }else{
                // bug2: index错误
                result -= map.get(s.charAt(i));
            }
        }

        return result;
    }
}
