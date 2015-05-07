/*
Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
*/
// Tag: Two Pointers, String

// Solution1: 用indexOf解决
public class Solution {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null){
            return -1;
        }
        int index = haystack.indexOf(needle);
        if (index >= 0){
            return index;
        }

        return -1;
    }
}

// Solution2: 用两个指针解决
public class Solution {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null){
            return -1;
        }

        int i, j;
        for (i = 0; i < haystack.length() - needle.length() + 1; i++){
            for (j = 0; j < needle.length(); j++){
                if (haystack.charAt(i + j) != needle.charAt(j)){
                    break;
                }
            }
            // 如果中间某一位不对的话j不会指向needle的末尾
            if (j == needle.length()){
                return i;
            }
        }

        return -1;
    }
}

// Official Solution:
/*
O(nm) runtime, O(1) space – Brute force:

You could demonstrate to your interviewer that this problem can be solved using known efficient algorithms
such as Rabin-Karp algorithm, KMP algorithm, and the Boyer- Moore algorithm. Since these algorithms are usually
studied in an advanced algorithms class, it is sufficient to solve it using the most direct method in an interview
 – The brute force method.

The brute force method is straightforward to implement. We scan the needle with the haystack from its first position
and start matching all subsequent letters one by one. If one of the letters does not match, we start over again with
the next position in the haystack.

The key is to implement the solution cleanly without dealing with each edge case separately.
*/
