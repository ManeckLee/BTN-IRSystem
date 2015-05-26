package test;

import java.util.Set;

import process.Spliter;

public class SentenceProcessTest
{
	public static void main(String[] args)
	{
		Spliter ph = new Spliter();
		String s = "ÈıÎ¬¹âÆ×";
		
		ph.split(s);
		Set<String> set = ph.getSpliteResult();
		
		System.out.println(set);
	}

}
