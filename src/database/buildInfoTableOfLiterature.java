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
 * 建立专家文献信息表
 * 
 * @author maneck
 * 
 */
public class buildInfoTableOfLiterature
{
	public void insertTable(String sourcepath)
	{
		DatabaseWriter writer = new DatabaseWriter();
		try
		{
			File path = new File(sourcepath + 
					"\\文献.txt");
			BufferedReader Reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(path), "UTF-8"));

			String insert = "INSERT INTO ExpLiteratureTable VALUES (?,?,?,?,?,?,?,?,?)";
			writer.setWriter(insert);
			PreparedStatement pstmt = writer.getSetter();

			String buff = null;
			int cnt = 0;
			buff = Reader.readLine();
			while (buff != null)
			{
				cnt++;
				String abs = "", author_cn = "", unit = "", journal_cn = "", app_date = "", key = "", title = "", expert_name = "";
				for (int i = 0; i < 9; ++i)
				{
					String buf = Reader.readLine();
					if (buf != null)
					{
						if (buf.indexOf("title") >= 0)
						{
							title = buf.substring(13, buf.length() - 2);
						}
						if (buf.indexOf("app_date") >= 0)
						{
							app_date = buf.substring(16, buf.length() - 2);
						}
						if (buf.indexOf("journal_cn") >= 0)
						{
							journal_cn = buf.substring(18, buf.length() - 2);
						}
						if (buf.indexOf("expert_name") >= 0)
						{
							expert_name = buf.substring(19, buf.length() - 2);
						}
						if (buf.indexOf("author_cn") >= 0)
						{
							author_cn = buf.substring(17, buf.length() - 2);
						}
						if (buf.indexOf("unit") >= 0)
						{
							unit = buf.substring(12, buf.length() - 2);
						}
						if (buf.indexOf("abs") >= 0)
						{
							abs = buf.substring(11, buf.length() - 2);
						}
						if (buf.indexOf("key") >= 0)
						{
							key = buf.substring(11, buf.length() - 2);
						}
					}
				}
				try
				{
					pstmt.setInt(1, cnt);
					pstmt.setString(2, title);
					pstmt.setString(3, app_date);
					pstmt.setString(4, journal_cn);
					pstmt.setString(5, expert_name);
					pstmt.setString(6, author_cn);
					pstmt.setString(7, unit);
					pstmt.setString(8, abs);
					pstmt.setString(9, key);

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
		buildInfoTableOfLiterature Table = new buildInfoTableOfLiterature();
	}
}
