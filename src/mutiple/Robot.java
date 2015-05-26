package mutiple;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import database.Querryer;

public class Robot implements InformationRobot
{
	
	protected final int WORD = 1;

	protected Querryer querryer;
	
	protected Statement stmt;
	
	protected String robotFlag; //机器人标志
	
	protected Map<Integer,AbsInfo> answerMap;
	
	protected String SQL;

	protected ResultSet getIDFromDatabase(String word)
	{
		ResultSet res = null;
		StringBuffer querySQL = new StringBuffer(this.SQL);
		querySQL.append("'").append(word).append("');");
		try
		{
			String query = querySQL.toString();
			System.out.println(query);
			res = this.stmt.executeQuery(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return res;
	}
	
	
	protected void addToansertMap(ResultSet res,String word)
	{
		AbsInfo info  = null;
		try
		{
			while(res.next())
			{
				int id = res.getInt(1);
				if((info = this.answerMap.get(id)) == null)
				{
					info = new AbsInfo(id,this.robotFlag);
				}

				this.updateInfo(info, word);
				
				this.answerMap.put(id, info);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public List<AbsInfo> getAnswer(Set<String> words)
	{
		Iterator<String> ite = words.iterator();
		while(ite.hasNext())
		{
			String word = ite.next();

			ResultSet res = this.getIDFromDatabase(word);
			
			this.addToansertMap(res,word);
		}
		List<AbsInfo> infos = this.parseMapToList(this.answerMap);

		return infos;
	}
	
	private List<AbsInfo> parseMapToList(Map<Integer,AbsInfo> map)
	{
		List<AbsInfo> infos = new ArrayList<AbsInfo>();
		Iterator<Integer> ite = map.keySet().iterator();
		while(ite.hasNext())
		{
			Integer id = ite.next();
			infos.add(map.get(id));
		}
		return infos;
	}
	
	private void init()
	{
		
	}

	protected void updateInfo(AbsInfo info,String word)
	{
		Set<String> words = info.getWords();
		words.add(word);
	}
	
	public Robot()
	{
		this.init();
	}

}
