package mutiple;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import database.DbConntion;
import database.Querryer;

public class Intelligence extends Robot
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
		
		String querrySQL = "SELECT * FROM ExpIntelligenceTable WHERE CONTAINS (abs,";
		this.SQL = querrySQL;
		
		this.querryer.querryInit(querrySQL);
		
		this.robotFlag = "IntelligenceRobot";
	}
	
	public Intelligence()
	{
		this.init();
	}

}
