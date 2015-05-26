package mutiple;

import java.io.FilterWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import database.DbConntion;

public class Filter
{
	private PreparedStatement pstmt;

	private Connection con;

	private Set<String> tfidfWords;

	private void getConnecion(String database)
	{
		DbConntion dc = new DbConntion(database);

		this.con = dc.getConnection();
	}

	public boolean isTfIdfContains(String word)
	{
		if (this.tfidfWords.contains(word))
		{
			return true;
		}

		if (this.isDatabaseContains(word))
		{
			this.tfidfWords.add(word);
			return true;
		}

		return false;
	}

	public Filter()
	{
		this.getConnecion("expert");
		
		this.init();

	}

	public Set<String> filtrate(Set<String> words)
	{
		Iterator<String> ite = words.iterator();

		Set<String> newWords = new HashSet<String>();

		while (ite.hasNext())
		{
			String word = ite.next();
			if (this.isTfIdfContains(word))
			{
				newWords.add(word);
			}
		}

		return newWords;
	}

	private void init()
	{
		this.tfidfWords = new HashSet<String>();

		String querySQL = "SELECT * FROM tfIdf WHERE word = ?;";
		try
		{
			this.pstmt = this.con.prepareStatement(querySQL);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

	}
	
	public Set<String> filte(Set<String> allWords)
	{	
		Set<String> filteWords = new HashSet<String>();
		Iterator<String> ite = allWords.iterator();
		while(ite.hasNext())
		{
			String word = ite.next();
			if(this.isTfIdfContains(word))
			{
				filteWords.add(word);
			}
		}
		return filteWords;
	}

	public boolean isDatabaseContains(String word)
	{
		ResultSet res = null;

		try
		{
			this.pstmt.setString(1, word);
			res = pstmt.executeQuery();

			while (res.next())
			{
				return true;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return false;
	}

}
