package test;

import mutiple.Filter;

public class FilterTest
{
	public static void main(String[] args)
	{
		Filter filter  = new Filter();
		
		boolean flag1 = filter.isDatabaseContains("������");
		
		System.out.println(flag1);
		
		boolean flag2 = filter.isTfIdfContains("������");
		
		System.out.println(flag2);

		flag2 = filter.isTfIdfContains("������");
		
		System.out.println(flag2);
	}

}
