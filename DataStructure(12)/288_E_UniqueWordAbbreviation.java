/*
An abbreviation of a word follows the form <first letter><number><last letter>. Below are some
examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the
dictionary. A word's abbreviation is unique if no other word from the dictionary has the same
abbreviation.

Example:
Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true
*/
// Company: Google
// Tag: Hash Table, Design

/*
思路：这里用一个HashMap来存储数据结构，key是某个缩写，value是该缩写所对应的所有原word的HashSet
注意为Unique的情况有三种，一是当前的word所对应的缩写不在map中，二是其对应缩写在map中，但map中
缩写所对应的原word有且只有当前的word一个；最后就是长度小于3的word根本没有缩写，因此肯定unique
*/
public class ValidWordAbbr {
    private Map<String, Set<String>> map = new HashMap<String, Set<String>>();

    public ValidWordAbbr(String[] dictionary) {
        for (String str : dictionary) {
            String abbr = getAbbr(str);
            if (!map.containsKey(abbr)) {
                Set<String> set = new HashSet<String>();
                set.add(str);
                map.put(abbr, set);
            } else {
                // bug1: only need to add str, don't need map.put....
                // bug2: don't need to check if set already contains current word
                map.get(abbr).add(str);
            }
        }
    }

    public boolean isUnique(String word) {
        // bug3: if the length of word is smaller than 3, return true directly
        if (word == null || word.length() < 3) {
            return true;
        }

        String abbr = getAbbr(word);

        if (!map.containsKey(abbr) || (map.get(abbr).contains(word) && map.get(abbr).size() == 1)) {
            return true;
        }

        return false;
    }

    private String getAbbr(String word) {
        if (word.length() < 3) {
            return word;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(word.charAt(0));
        sb.append(word.length() - 2);
        sb.append(word.charAt(word.length() - 1));

        return String.valueOf(sb);
    }
}


// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");
