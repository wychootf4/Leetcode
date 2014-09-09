/*
Given a string s and a dictionary of words dict, determine if s can be segmented into a 
space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
*/
import java.util.Set;
public class WordBreak
{
	public static void main(String args[]){
		String s = "leetcode";
		Set<String> dict= new Set<String>();
		dict.add("leet");
		dict.add("code");
		System.out.println(wordBreak(s,dict));
	}
	public static boolean wordBreak(String s,Set<String> dict){
		boolean[] t = new boolean[s.length() + 1];
		t[0] = true; // initial state, suppose t[0] = true
		for (int i = 0;i < s.length() ;i++ )
		{
			/*
			reduce overlapped subproblem, if t[i] = false means that it never 
			reached before and therefore no need to run code below, run next loop
			*/
			if (!t[i]) 
			{
				continue;
			}
			for (String a : dict) // check each dict word with substring
			{
				int len = a.length();
				int end = i + len;
				if (t[end])
				{
					continue;
				}
				if (end > s.length())
				{
					continue;
				}
				if (s.substring(i,end).equals(a))
				{
					t[end] = true;
				}
			}
		}
		return t[s.length()];
	}
}
// Time complexity: O(string length * dict size)
// Using DP to solve this problem, t[i] = true represents 0...i - 1 can be segmented   
// into dict words