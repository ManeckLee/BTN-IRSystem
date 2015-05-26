package build;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import database.DbConntion;

public class Unbuilder
{
	private Connection con;

	private Statement stmt;

	private void init()
	{
		DbConntion dc = new DbConntion();

		this.con = dc.getConnection();

		try
		{
			this.stmt = this.con.createStatement();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	public Unbuilder()
	{
		this.init();
	}

	public void unBuild()
	{
		String dropExpAssTable = "DROP TABLE ExpAssTable;";
		String dropExpInfoTable = "DROP TABLE ExpInfoTable;";
		String dropExpIntelligenceTable = "DROP TABLE ExpIntelligenceTable;";
		String dropeExpLiteratureTable = "DROP TABLE ExpLiteratureTable;";
		String dropExpPatentTable = "DROP TABLE ExpPatentTable;";
		String dropLitAssTable = "DROP TABLE LitAssTable;";
		String dropOrgAssTable = "DROP TABLE OrgAssTable;";
		String dropTfidfTable = "DROP TABLE tfidf";

//		try
//		{
//			this.stmt.execute(dropExpAssTable);
//		}
//		catch (SQLException e)
//		{
//			e.printStackTrace();
//		}
		try
		{
			this.stmt.execute(dropExpInfoTable);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			this.stmt.execute(dropExpIntelligenceTable);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			this.stmt.execute(dropeExpLiteratureTable);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		try
		{
			this.stmt.execute(dropExpPatentTable);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
//		try
//		{
//			this.stmt.execute(dropLitAssTable);
//		}
//		catch (SQLException e)
//		{
//			e.printStackTrace();
//		}
//		try
//		{
//			this.stmt.execute(dropOrgAssTable);
//		}
//		catch (SQLException e)
//		{
//			e.printStackTrace();
//		}
		try
		{
			this.stmt.execute(dropTfidfTable);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

}
