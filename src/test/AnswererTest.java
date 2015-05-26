package test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mutiple.AbsInfo;
import mutiple.AbsInfoComparator;
import mutiple.Answerer;

public class AnswererTest
{
	public static void main(String[] args)
	{
		Answerer answer = new Answerer();
		
		Map<String,List<AbsInfo>> ans = answer.answer("MOOCÇå»ª");
		
		Iterator<String> ite = ans.keySet().iterator();
		while(ite.hasNext())
		{
			String flag = ite.next();
			List<AbsInfo> infos = ans.get(flag);
			infos.sort(new AbsInfoComparator());
			
			System.out.println(flag);
			System.out.println(infos);
			
			System.out.println("-----------------------------");
		}
	}

}
