/*
Given a string S, find the longest palindromic substring in S. You may assume that the maximum 
length of S is 1000, and there exists one unique longest palindromic substring.
*/
public class LongestPalindromicSubstring
{
	public static void main(String args[]){
		String s = "dabcba";
		System.out.println(longestPalindrome(s));
	}
	public static String longestPalindrome(String s){
		if (s.isEmpty())
		{
			return null;
		}
		if (s.length() == 1)
		{
			return s;
		}
		String longest = s.substring(0,1);
		for (int i = 0;i < s.length() ;i++ )
		{
			String tmp = helper(s,i,i);
			if (tmp.length() > longest.length())
			{
				longest = tmp;
			}
			tmp = helper(s,i,i + 1);
			if (tmp.length() > longest.length())
			{
				longest = tmp;
			}
		}
		return longest;
	}
	public static String helper(String s,int start,int end){
		while (start >= 0 && end <= s.length() - 1 && s.charAt(start) == s.charAt(end))
		{
			start--;
			end++;
		}
		return s.substring(start + 1,end);
	}
}
// Time complexity: O(n ^ 2),Space complexity: O(1)
