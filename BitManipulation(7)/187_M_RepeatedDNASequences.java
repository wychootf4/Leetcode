/*
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When
studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].
*/
// Tag: Hash Table, Bit Manipulation

/*
分析：
遍历整个数组，对每个符合要求的字串进行编码，新建两个hashset，encoding存是否某个串已编码，dna存编码的内容。
对某个字串进行编码后，看此编码是否在encoding中，如果在则证明该子串为重复串，将其加入dna。最后将所有dna中的串加入result即可。
*/

public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<String>();
        if (s == null || s.length() == 0){
            return result;
        }

        HashSet<Integer> encoding = new HashSet<Integer>();
        HashSet<String> dna = new HashSet<String>();

        for (int i = 0; i < s.length() - 9; i++) {
            String subString = s.substring(i, i + 10);
            int encoded = encode(subString);
            if (encoding.contains(encoded)) {
                dna.add(subString);
            } else {
                encoding.add(encoded);
            }
        }

        for (String d: dna) {
            result.add(d);
        }

        return result;
    }

    private int encode(String s) {
        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') {
                sum = sum * 4;
            } else if (s.charAt(i) == 'C') {
                sum = sum * 4 + 1;
            } else if (s.charAt(i) == 'G') {
                sum = sum * 4 + 2;
            } else {
                sum = sum * 4 + 3;
            }
        }

        return sum;
    }
}
