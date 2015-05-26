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
 * 建立专家基本信息表
 * 
 * @author maneck
 *
 */
public class buildTableOfExp
{
	public void insertTable(String sourcepath)
	{
		DatabaseWriter writer = new DatabaseWriter();
		try
		{
			File path = new File(sourcepath + 
					"\\GeneratedFiles\\ExpName.txt");
			BufferedReader fileReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(path), "UTF-8"));
			String insert = "INSERT INTO ExpInfoTable VALUES (?,?,?,?,?)";
			writer.setWriter(insert);
			PreparedStatement pstmt = writer.getSetter();

			String buff = null;
			int cnt = 0;
			buff = fileReader.readLine();
			while (buff != null)
			{
				cnt++;
				String expert_name = "", expert_org = "", ResearchDirection = "", Achievements = "";
				String[] bu = buff.split("\\s+");
				expert_name = bu[0];
				expert_org = bu[1];
				try
				{
					pstmt.setInt(1, cnt);
					pstmt.setString(2, expert_name);
					pstmt.setString(3, expert_org);
					pstmt.setString(4, ResearchDirection);
					pstmt.setString(5, Achievements);

					writer.insert();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
				buff = fileReader.readLine();
			}
			writer.commit();
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		buildTableOfExp Table = new buildTableOfExp();
	}
}
