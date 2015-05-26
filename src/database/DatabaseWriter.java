package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseWriter
{
	
	private Connection con;
	
	private PreparedStatement pstmt;
	
	public void setWriter(String insertSQL)
	{
		try
		{
			this.pstmt = this.con.prepareStatement(insertSQL);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public DatabaseWriter()
	{
		this.init();
	}
	
	public PreparedStatement getSetter()
	{
		return this.pstmt;
	}
	
	public void insert()
	{
		try
		{
		this.pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void commit()
	{
		try
		{
		this.con.commit();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void init()
	{
		DbConntion dc = new DbConntion();
		
		this.con = dc.getManualCommitConnection();
	}

}
