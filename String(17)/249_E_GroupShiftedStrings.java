/*
Given a string, we can "shift" each of its letter to its successive letter, for example:
"abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to
the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
A solution is:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
*/
// Company: Google Uber
// Tag: Hash Table, String

/*
思路：利用map存储(base, 对应List)对，对于每个string求出对应减去‘a’的差值，即为其base；然后根据base加入
map中，最后将map中的所有list加入到result中，注意在处理前先将数组排序
*/
public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (strings.length == 0 || strings == null) {
            return result;
        }

        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        // bug2: 需要先将数组里元素排序
        Arrays.sort(strings);

        for (String str : strings) {
            char[] arr = str.toCharArray();
            if (arr.length > 0) {
                int diff = arr[0] - 'a';
                for (int i = 0; i < arr.length; i++) {
                    // bug1: 考虑当前位减去差值取模的问题，比如ba，diff为1，其base应为az
                    if (arr[i] - diff < 'a') {
                        arr[i] = (char)(arr[i] - diff + 26);
                    } else {
                        arr[i] = (char)(arr[i] - diff);
                    }
                }
            }

            String ns = new String(arr);
            if (map.containsKey(ns)) {
                map.get(ns).add(str);
            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(str);
                map.put(ns, list);
            }
        }

        result.addAll(map.values());

        return result;
    }
}
