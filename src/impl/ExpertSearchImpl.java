package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConn;
import expert.Expert;

public class ExpertSearchImpl
{
	// 实现按照专家姓名检索的功能输入
	public ArrayList<Expert> NameSearch(String expert_name)
	{
		ArrayList<Expert> experts = new ArrayList<Expert>();
		Connection con = null;
		PreparedStatement Infopre = null;
		PreparedStatement Asspre = null;
		ResultSet Infors = null;
		ResultSet Assrs = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		try
		{
			Infopre = con
					.prepareStatement("SELECT * FROM ExpInfoTable WHERE expert_name LIKE ?");
			Infopre.setString(1, expert_name);
			Asspre = con
					.prepareStatement("SELECT * FROM ExpAssTable WHERE expert_name LIKE ?");
			Asspre.setString(1, expert_name);
			Infors = Infopre.executeQuery();
			Assrs = Asspre.executeQuery();
			while (Infors.next())
			{
				Assrs.next();
				Expert expert = new Expert();
				expert.setNO(Infors.getInt("NO"));
				expert.setExpert_name(Infors.getString("expert_name"));
				expert.setExpert_org(Infors.getString("expert_org"));
				expert.setLiteratureAss(Assrs.getInt("LiteratureAss"));
				expert.setCntOfIntelligence(Assrs.getInt("CntOfIntelligence"));
				expert.setCntOfPatent(Assrs.getInt("CntOfPatent"));
				expert.setOrgAss(Assrs.getInt("OrgAss"));
				expert.setComprehensiveAss(Assrs.getInt("ComprehensiveAss"));
				experts.add(expert);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return experts;
	}

	// 实现按照工作单位检索的功能输入
	public ArrayList<Expert> OrgSearch(String expert_org)
	{
		ArrayList<Expert> experts = new ArrayList<Expert>();
		Connection con = null;
		PreparedStatement Infopre = null;
		PreparedStatement Asspre = null;
		ResultSet Infors = null;
		ResultSet Assrs = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		try
		{
			Infopre = con
					.prepareStatement("SELECT * FROM ExpInfoTable WHERE expert_org LIKE ?");
			Infopre.setString(1, "%" + expert_org + "%");
			Infors = Infopre.executeQuery();
			while (Infors.next())
			{
				Expert expert = new Expert();
				expert.setNO(Infors.getInt("NO"));
				expert.setExpert_name(Infors.getString("expert_name"));
				expert.setExpert_org(Infors.getString("expert_org"));
				
				int NO = Infors.getInt("NO");
				Asspre = con
						.prepareStatement("SELECT * FROM ExpAssTable WHERE NO = ?");
				Asspre.setInt(1, NO);
				Assrs = Asspre.executeQuery();
				Assrs.next();
				
				expert.setLiteratureAss(Assrs.getInt("LiteratureAss"));
				expert.setCntOfIntelligence(Assrs.getInt("CntOfIntelligence"));
				expert.setCntOfPatent(Assrs.getInt("CntOfPatent"));
				expert.setOrgAss(Assrs.getInt("OrgAss"));
				expert.setComprehensiveAss(Assrs.getInt("ComprehensiveAss"));
				
				experts.add(expert);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return experts;
	}
	
	//实现按照专家研究方向（主题）检索的功能输入
	public Expert KeySearch(String expert_name)
	{
		Expert expert = new Expert();
		Connection con = null;
		PreparedStatement Infopre = null;
		PreparedStatement Asspre = null;
		ResultSet Infors = null;
		ResultSet Assrs = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		try
		{
			Infopre = con
					.prepareStatement("SELECT * FROM ExpInfoTable WHERE expert_name LIKE ?");
			Infopre.setString(1, expert_name);
			Infors = Infopre.executeQuery();
			while (Infors.next())
			{
				expert.setNO(Infors.getInt("NO"));
				expert.setExpert_name(Infors.getString("expert_name"));
				expert.setExpert_org(Infors.getString("expert_org"));
				
				int NO = Infors.getInt("NO");
				Asspre = con
						.prepareStatement("SELECT * FROM ExpAssTable WHERE NO = ?");
				Asspre.setInt(1, NO);
				Assrs = Asspre.executeQuery();
				Assrs.next();
				
				expert.setLiteratureAss(Assrs.getInt("LiteratureAss"));
				expert.setCntOfIntelligence(Assrs.getInt("CntOfIntelligence"));
				expert.setCntOfPatent(Assrs.getInt("CntOfPatent"));
				expert.setOrgAss(Assrs.getInt("OrgAss"));
				expert.setComprehensiveAss(Assrs.getInt("ComprehensiveAss"));
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return expert;
	}
}
