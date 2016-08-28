/*
A strobogrammatic number is a number that looks the same when rotated 180
degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented
as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.
*/
// Company: Google
// Tag: Hash Table, Math

/*
思路：只有01689允许出现，rotate时候先将69互换，然后逐一比较
*/
public class Solution {
    Set<Character> set = new HashSet<Character>();

    public boolean isStrobogrammatic(String num) {
        if (num == null) {
            return false;
        }
        // bug1: initialize set value outside the method
        // bug2: missing '0'
        set.add('0');
        set.add('1');
        set.add('6');
        set.add('8');
        set.add('9');
        String rotated = rotate(num);

        return compare(num, rotated);
    }

    private String rotate(String num) {
        char[] arr = num.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '6') {
                arr[i] = '9';
            } else if (arr[i] == '9') {
                arr[i] = '6';
            }
        }

        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }

        return String.valueOf(arr);
    }

    private boolean compare(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }

        for (int i = 0; i < str1.length(); i++) {
            if (!set.contains(str1.charAt(i)) || (str1.charAt(i) != str2.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
// Optimization: 可以不需要rotate，用map提前存好然后比较
public class Solution {

    public boolean isStrobogrammatic(String num) {
        if (num == null) {
            return false;
        }

        Map<Character, Character> map = new HashMap<Character, Character>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');

        return check(num, map);
    }

    private boolean check(String num, Map<Character, Character> map) {
        if (num == null) {
            return false;
        }

        int i = 0;
        int j = num.length() - 1;
        char head, tail;
        while (i <= j) {
            head = num.charAt(i);
            tail = num.charAt(j);
            // 看映射是否存在
            if (!map.containsKey(tail) || head != map.get(tail)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }
}
