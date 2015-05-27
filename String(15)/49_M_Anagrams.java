/*
Given an array of strings, return all groups of strings that are anagrams.

Note: All inputs will be in lower-case.
*/
// Tag: Hash Table, String

/*
分析：
首先算出每个字符串的哈希值，并且新建一个哈希表存储一些值，对于哈希值相同的字符串说明其有anagram，存到一个value中，此
value用arraylist。最后遍历一遍哈希表，找出所有arraylist的size大于1的，将这些arraylist中的所有字符串加入结果。
*/
public class Solution {
    private int getHash(int[] count){
        int hash = 0;
        int a = 378551;
        int b = 63689;
        for (int num : count){
            hash = hash * a + num;
            a = a * b;
        }

        return hash;
    }

    public List<String> anagrams(String[] strs) {
        List<String> result = new ArrayList<String>();
        if (strs == null || strs.length == 0){
            return result;
        }

        Map<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();

        for (String str : strs){
            int[] count = new int[26];
            // 统计某一字符串中每个字母出现的次数
            for (int i = 0; i < str.length(); i++){
                count[str.charAt(i) - 'a']++;
            }
            // 然后根据count数组计算该字符串的哈希值
            int hash = getHash(count);
            if (!map.containsKey(hash)){
                map.put(hash, new ArrayList<String>());
            }
            map.get(hash).add(str);
        }

        for (ArrayList<String> tmp : map.values()){
            if (tmp.size() > 1){
                result.addAll(tmp);
            }
        }

        return result;
    }
}
