package test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import database.Querryer;
import expertNet.Cooperation;
import expertNet.CoreExpertInfo;
import expertNet.ExpertRelationNet;

public class NetTest
{
	private Querryer querryer;
	
	public NetTest()
	{
		this.querryer = new Querryer();		
		String querrySQL = "SELECT name FROM tfidf WHERE word = ?";
		this.querryer.querryInit(querrySQL);
	}
	
	public ResultSet querry(String word)
	{
		ResultSet res = null;
		PreparedStatement pstmt = null;
		try
		{
			pstmt = this.querryer.getSetter();
			pstmt.setString(1, word);
			res = this.querryer.querry();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return res;
	}
	public void create()
	{
		String collumLab = "expert varchar(200), coop text";
	}
	
	public void createTestTable(String word)
	{
		
	}
	public static void main(String[] args)
	{
		ExpertRelationNet net = new ExpertRelationNet();
		
		Cooperation coop = new Cooperation();
		
		net.buildRelationNet("นโฦื", coop);
		net.computeCoreExpert();
		List<CoreExpertInfo> cinfos = net.getCoreExperts();
		System.out.println(cinfos);
	}

}
