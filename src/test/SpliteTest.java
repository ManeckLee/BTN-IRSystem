package test;

public class SpliteTest
{
	public static void main(String[] args)
	{
		String s = " 张长青; 姚可夫;";
		String[] array = s.split(";");
		int i = 0;
		
		for(String a : array)
		{
			++i;
			System.out.println(a);
		}
		System.out.println(i);
	}

}
