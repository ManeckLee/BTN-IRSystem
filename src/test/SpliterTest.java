package test;

import process.Spliter;

public class SpliterTest
{
	public static void main(String[] args)
	{
		Spliter sp = new Spliter();

		try
		{
			sp.spliteFile("e:\\source\\", "e:\\targert\\");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
