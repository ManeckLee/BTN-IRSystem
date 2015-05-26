package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 建立专家情报信息表
 * 
 * @author maneck
 * 
 */
public class buildInfoTableOfIntelligence
{
	public void insertTable(String sourcepath)
	{
		DatabaseWriter writer = new DatabaseWriter();
		try
		{
			File path = new File(sourcepath + 
					"\\情报.txt");
			BufferedReader Reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(path), "UTF-8"));

			String insert = "INSERT INTO ExpIntelligenceTable VALUES (?,?,?,?)";
			writer.setWriter(insert);
			PreparedStatement pstmt = writer.getSetter();

			String buff = null;
			int cnt = 0;
			buff = Reader.readLine();
			while (buff != null)
			{
				cnt++;
				String title = "", date = "", abs = "", expert_name = "";
				for (int i = 0; i < 6; ++i)
				{
					String buf = Reader.readLine();
					if (buf != null)
					{
						if (buf.indexOf("title") >= 0)
						{
							title = buf.substring(13, buf.length() - 2);
						}
						if (buf.indexOf("date") >= 0)
						{
							date = buf.substring(12, buf.length() - 2);
						}
						if (buf.indexOf("abs") >= 0)
						{
							abs = buf.substring(11, buf.length() - 2);
						}
						if (buf.indexOf("expert_name") >= 0)
						{
							expert_name = buf.substring(19, buf.length() - 2);
						}
					}
				}

				try
				{
//					pstmt.setInt(1, cnt);
					pstmt.setString(2 -1, expert_name);
					pstmt.setString(3 -1, title);
					pstmt.setString(4 -1, date);
					pstmt.setString(5 -1, abs);

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
		buildInfoTableOfIntelligence Table = new buildInfoTableOfIntelligence();
	}
}
