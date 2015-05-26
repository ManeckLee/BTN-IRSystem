package mutiple;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import database.DbConntion;
import database.Querryer;

public class Literature extends Robot
{ 


	private void init()
	{
		this.answerMap = new HashMap<Integer, AbsInfo>();
		
		this.querryer = new Querryer();
		DbConntion dc = new DbConntion();
		Connection con = dc.getConnection();
		try
		{
			this.stmt = con.createStatement();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		String querrySQL = "SELECT * FROM ExpLiteratureTable WHERE CONTAINS (abs,";
		this.SQL = querrySQL;
		
		this.querryer.querryInit(querrySQL);
		
		this.robotFlag = "literatureRobot";
	}
	
	public Literature()
	{
		this.init();
	}
}
