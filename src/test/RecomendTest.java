package test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import process.Spliter;
import recommend.ExpertInfo;
import recommend.RecomendInfo;
import recommend.Recommender;

public class RecomendTest
{
	public static void main(String[] args)
	{
		Spliter ph = new Spliter(); //�ִ���
		String s = "��ά����";
		
		ph.split(s);
		Set<String> set = ph.getSpliteResult(); // ��ȡ�ִʽ��
		
		Recommender recommender = new Recommender(); //�Ƽ�������
		
		
		List<RecomendInfo> res = recommender.recommend(set);  //�Ƽ�
		
		Iterator<RecomendInfo> ite = res.iterator(); //�������
		while(ite.hasNext())
		{
			RecomendInfo info = ite.next();
			String name = info.getExpertName();
			String[] names = name.split("_"); //��ȡר����
			List<String> keyWords = info.getKeyWords(); //��ȡ�Ƽ�����
			System.out.println(names[0]);
			System.out.println(keyWords);
		}
	}

}
