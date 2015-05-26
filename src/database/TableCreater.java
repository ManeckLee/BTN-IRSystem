package database;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableCreater
{
	
	private Connection con;

	private Statement stmt;
	
	private void createFullTestIndex(String tableName,String uniqueColumLable,String fullTextColumLable)
	{
		String catalog = String.format("CREATE FULLTEXT CATALOG %s AS DEFAULT;", tableName);
		String uniqueIndex = String.format("CREATE UNIQUE INDEX uniquei ON %s(%s);", tableName,uniqueColumLable);
		String fullText1 = String.format("CREATE FULLTEXT INDEX ON %s (%s Language 2052)", tableName,fullTextColumLable);
		String fullText2 = String.format("KEY INDEX  uniquei ON %s WITH CHANGE_TRACKING AUTO",tableName );
		try
		{
			this.stmt.execute(catalog);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			this.stmt.execute(uniqueIndex);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			this.stmt.execute(fullText1 + fullText2);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public TableCreater()
	{
		DbConntion dc = new DbConntion();
		
		this.con = dc.getConnection();
		
		try
		{
			this.stmt = con.createStatement();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void createTable()
	{
//		//专家情报信息表
		try
		{
			String createInfoTableOfIntelligence = "create table ExpIntelligenceTable(NO bigint IDENTITY primary key,expert_name varchar(200),title text,date text,abs text);";
			String createInfoTableOfIntelligeenceIndex = "CREATE NONCLUSTERED INDEX nameindex ON ExpIntelligenceTable(expert_name);";
			stmt.execute(createInfoTableOfIntelligence);
			stmt.execute(createInfoTableOfIntelligeenceIndex);
			this.createFullTestIndex("ExpIntelligenceTable", "NO" , "abs");
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		//专家文献信息表
		try
		{
			String createInfoTableOfLiterature = "create table ExpLiteratureTable(NO bigint primary key,title text,app_date text,journal_cn text,expert_name varchar(200),author_cn text,unit text,abs text,keywords text);";
			String createInfoTableOfLiteratureIndex = "CREATE NONCLUSTERED INDEX nameindex ON ExpLiteratureTable(expert_name);";
			stmt.execute(createInfoTableOfLiterature);
			stmt.execute(createInfoTableOfLiteratureIndex);
			this.createFullTestIndex("ExpLiteratureTable", "NO" , "abs");
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		//专家专利信息表
		try
		{
			String createInfoTableOfPatent = "create table ExpPatentTable(NO bigint primary key,expert_name varchar(200),title text,date text,applicant text,abs text);";
			String createInfoTableOfPatentIndex = "CREATE NONCLUSTERED INDEX nameindex ON ExpPatentTable(expert_name);";
			stmt.execute(createInfoTableOfPatent);
			stmt.execute(createInfoTableOfPatentIndex);
			this.createFullTestIndex("ExpPatentTable", "NO" , "abs");
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		//专家综合评估表
//		try
//		{
//			String createTableOfAssessment = "create table ExpAssTable(NO bigint primary key,expert_name text,LiteratureAss int,CntOfIntelligence int,CntOfPatent int,OrgAss int,ComprehensiveAss int);";
//			stmt.execute(createTableOfAssessment);
//		}
//		catch (SQLException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//机构评级参照表
//		try
//		{
//			String createAssTableOfOrg = "create table OrgAssTable(NO bigint primary key,OrgName text,OrgScore float);";
//			stmt.execute(createAssTableOfOrg);
//		}
//		catch (SQLException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////		//文献评级参照表
//		try
//		{
//			String createAssTableOfLit = "create table LitAssTable(NO bigint primary key,LiteratureName text,LiteratureScore float);";
//			stmt.execute(createAssTableOfLit);
//		}
//		catch (SQLException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//专家基本信息表
		try
		{
			String createTableOfExp = "create table ExpInfoTable(NO bigint primary key,expert_name varchar(200),expert_org text,ResearchDirection text,Achievements text);";
			String createTableOFExpIndex = "CREATE NONCLUSTERED INDEX nameindex ON ExpInfoTable(expert_name);";
			stmt.execute(createTableOfExp);
			stmt.execute(createTableOFExpIndex);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
