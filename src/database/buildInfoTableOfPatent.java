package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 建立专家专利信息表
 * 
 * @author maneck
 * 
 */
public class buildInfoTableOfPatent
{
	public void insertTable(String sourcepath)
	{
		DatabaseWriter writer = new DatabaseWriter();
		try
		{
			File path = new File(sourcepath + 
					"\\专利.txt");
			BufferedReader Reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(path), "UTF-8"));

			String insert = "INSERT INTO ExpPatentTable VALUES (?,?,?,?,?,?)";
			writer.setWriter(insert);
			PreparedStatement pstmt = writer.getSetter();

			String buff = null;
			int cnt = 0;
			buff = Reader.readLine();
			while (buff != null)
			{
				cnt++;
				String expert_name = null, title = null, date = null, applicant = null, abs = null;
				for (int i = 0; i < 7; ++i)
				{
					String buf = Reader.readLine();
					if (buf != null)
					{
						if (buf.indexOf("expert_name") >= 0)
						{
							expert_name = buf.substring(19, buf.length() - 2);
						}
						if (buf.indexOf("title") >= 0)
						{
							title = buf.substring(13, buf.length() - 2);
						}
						if (buf.indexOf("date") >= 0)
						{
							date = buf.substring(12, buf.length() - 2);
						}
						if (buf.indexOf("applicant") >= 0)
						{
							applicant = buf.substring(17, buf.length() - 2);
						}
						if (buf.indexOf("abs") >= 0)
						{
							abs = buf.substring(11, buf.length() - 2);
						}
					}
				}
				try
				{
					pstmt.setInt(1, cnt);
					pstmt.setString(2, expert_name);
					pstmt.setString(3, title);
					pstmt.setString(4, date);
					pstmt.setString(5, applicant);
					pstmt.setString(6, abs);

					writer.insert();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
				Reader.readLine();
				buff = Reader.readLine();
			}
			writer.commit();
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		buildInfoTableOfPatent Table = new buildInfoTableOfPatent();
	}
}
