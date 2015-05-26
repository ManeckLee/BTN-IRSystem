package recommend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import database.DbConntion;

public class Recommender
{
	private Map<String, ExpertInfo> experts;

	private Connection databaseCon;

	private ResultSet getExpertsFromDatabase(String table, String word)
	{
		StringBuffer querySQL = new StringBuffer("SELECT name,value FROM ")
				.append(table).append(" WHERE word = ?");
		PreparedStatement pstmt = null;
		ResultSet res = null;
		try
		{
			pstmt = this.databaseCon.prepareStatement(querySQL.toString());
			pstmt.setString(1, word);
			res = pstmt.executeQuery();
			this.databaseCon.commit();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return res;
	}
	
	private Map<String, ExpertInfo> turnintoExpert(String word, ResultSet res)
	{
		Map<String, ExpertInfo> expert = new HashMap<String, ExpertInfo>();
		String name = null;
		ExpertInfo ei = null;
		try
		{
			name = res.getString(1);
			float tfidf = res.getFloat(2);
			ei = new ExpertInfo(tfidf);
			ei.addKeyWords(word);
			
			expert.put(name, ei);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return expert;
	}
	
	private void addToExperts(String name, ExpertInfo info)
	{
		ExpertInfo oldinfo = this.experts.get(name);
		if(null == oldinfo)
		{
			this.experts.put(name, info);
		}
		else
		{
			oldinfo.merge(info);
		}
	}
	private void merge(Map<String, ExpertInfo> expertsFromDatabase)
	{
		Iterator<String> ite = expertsFromDatabase.keySet().iterator();
		while(ite.hasNext())
		{
			String name = ite.next();
			ExpertInfo info = expertsFromDatabase.get(name);
			this.addToExperts(name, info);
		}
	}

	private Map<String, ExpertInfo> getExperts(String word)
	{
		Map<String, ExpertInfo> experts = new HashMap<String, ExpertInfo>();

		boolean isEmpty = true;
		ResultSet res = this.getExpertsFromDatabase("tfidf", word);
		try
		{
			while (res.next())
			{
				isEmpty = false;
				experts.putAll(this.turnintoExpert(word, res));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		if(isEmpty) //如果返回结果为空集（数据库不存在这个词）
		{
			return null;
		}

		return experts;
	}

	private void getDatabaseCon(String database)
	{
		DbConntion dc = new DbConntion(database);
		this.databaseCon = dc.getManualCommitConnection();

	}
	
	private boolean hasExpert(Map<String,ExpertInfo> experts)
	{
		return !(null == experts);
	}
	
	private List<RecomendInfo> parseList(Map<String,ExpertInfo> map)
	{
		List<RecomendInfo> list = new ArrayList<RecomendInfo>();
		Iterator<String> ite = map.keySet().iterator();
		while(ite.hasNext())
		{
			String name = ite.next();
			ExpertInfo info = map.get(name);
			
			RecomendInfo rinfo = new RecomendInfo(name,info);
			list.add(rinfo);
		}
		
		return list;
	}
	
	public List<RecomendInfo> recommend(Set<String> words)
	{
		Iterator<String> ite = words.iterator();
		Map<String,ExpertInfo> expertsFromDatabase = null;
//		System.out.println("In recommend :");
		while(ite.hasNext())
		{
			String word = ite.next();
//			System.out.println(word);
			expertsFromDatabase = this.getExperts(word);
//			System.out.println(expertsFromDatabase);
			if(this.hasExpert(expertsFromDatabase))
			{
				this.merge(expertsFromDatabase);
			}
		}
		
		List<RecomendInfo> recomendExperts = this.parseList(this.experts);
		
		recomendExperts.sort(new RecommendInfoComparator());
		
		return recomendExperts;
	}

	public Recommender()
	{
		this.experts = new HashMap<String, ExpertInfo>();
		this.getDatabaseCon("expert");
	}

}
