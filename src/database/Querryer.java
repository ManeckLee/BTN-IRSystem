package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Querryer
{
	private DbConntion dc;

	private Connection con;
	
	private PreparedStatement pstmt;
	
	private String database;

	
	public Querryer()
	{
		this.init("expert");
	}

	public Querryer(String database)
	{
		this.init(database);
	}

	private void init(String database)
	{
		this.dc = new DbConntion();
		
		this.con = dc.getConnection();
	}
	
	public PreparedStatement getSetter()
	{
		return this.pstmt;
	}
	
	public ResultSet querry()
	{
		ResultSet res = null;
		try
		{
			res = this.pstmt.executeQuery();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return res;
	}

	public void querryInit(String querrySQL)
	{
		try
		{
			this.pstmt = this.con.prepareStatement(querrySQL);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}
