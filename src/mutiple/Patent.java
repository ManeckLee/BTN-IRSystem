package mutiple;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import database.DatabaseOp;
import database.DbConntion;
import database.Querryer;

public class Patent extends Robot
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
		
		String querrySQL = "SELECT NO FROM ExpPatentTable WHERE CONTAINS (abs,";
		
		this.SQL = querrySQL;
		
		this.querryer.querryInit(querrySQL);
		
		this.robotFlag = "patentRobot";
	}
	
	public Patent()
	{
		this.init();
	}

}
