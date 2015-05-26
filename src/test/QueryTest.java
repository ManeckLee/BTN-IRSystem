package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DbConntion;
import database.Querryer;

public class QueryTest
{
	public static void main(String[] args)
	{
		String sql = "SELECT NO FROM ExpIntelligenceTable WHERE CONTAINS (abs,'MOOC');";

		DbConntion dc = new DbConntion();

		Connection con = dc.getConnection();

		Statement stmt = null;
		ResultSet res = null;
		try
		{
			stmt = con.createStatement();
			res = stmt.executeQuery(sql);
			while (res.next())
			{
				System.out.println(res.getInt(1));
			}
			System.out.println("-------------------------");
			
			Querryer q = new Querryer();
			String qsql = "SELECT NO FROM ExpPatentTable WHERE CONTAINS (abs,'MOOC');";
			q.querryInit(qsql);
			PreparedStatement pstmt = q.getSetter();
//			pstmt.setString(1, "MOOC");
			res = q.querry();
			while(res.next())
			{
				System.out.println(res.getInt(1));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

}
