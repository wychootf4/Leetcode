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
		t[0] = true;
		for (int i = 0;i < s.length() ;i++ )
		{
			if (!t[i])
			{
				continue;
			}
			for (String a : dict)
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