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
		Spliter ph = new Spliter(); //分词器
		String s = "三维光谱";
		
		ph.split(s);
		Set<String> set = ph.getSpliteResult(); // 获取分词结果
		
		Recommender recommender = new Recommender(); //推荐机器人
		
		
		List<RecomendInfo> res = recommender.recommend(set);  //推荐
		
		Iterator<RecomendInfo> ite = res.iterator(); //遍历结果
		while(ite.hasNext())
		{
			RecomendInfo info = ite.next();
			String name = info.getExpertName();
			String[] names = name.split("_"); //获取专家名
			List<String> keyWords = info.getKeyWords(); //获取推荐依据
			System.out.println(names[0]);
			System.out.println(keyWords);
		}
	}

}
