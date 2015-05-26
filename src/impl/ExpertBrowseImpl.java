package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConn;
import expert.Expert;
import expert.ExpertIntell;
import expert.ExpertLit;
import expert.ExpertPatent;

//实现专家浏览功能
public class ExpertBrowseImpl
{
	// 专家详情
	public Expert browse_detail(String expert_name)
	{
		Expert expert_detail = new Expert();
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		try
		{
			pre = con
					.prepareStatement("SELECT * FROM ExpInfoTable WHERE expert_name LIKE ?");
			pre.setString(1, expert_name);
			rs = pre.executeQuery();
			while (rs.next())
			{
				expert_detail.setNO(rs.getInt("NO"));
				expert_detail.setExpert_name(rs.getString("expert_name"));
				expert_detail.setExpert_org(rs.getString("expert_org"));
				//expert_detail.setResearchDirection(rs.getString("ReaserchDirection"));
				//expert_detail.setAchievements(rs.getString("Achievements"));
			}
			pre = con
					.prepareStatement("SELECT * FROM ExpAssTable WHERE expert_name LIKE ?");
			pre.setString(1, expert_name);
			rs = pre.executeQuery();
			while (rs.next())
			{
				expert_detail.setLiteratureAss(rs.getInt("LiteratureAss"));
				expert_detail.setCntOfIntelligence(rs.getInt("CntOfIntelligence"));
				expert_detail.setCntOfPatent(rs.getInt("CntOfPatent"));
				expert_detail.setOrgAss(rs.getInt("OrgAss"));
				expert_detail.setComprehensiveAss(rs.getInt("comprehensiveAss"));
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return expert_detail;
	}

	//浏览专家文献信息
	public ArrayList<ExpertLit> browseLit(String expert_name)
	{
		ArrayList<ExpertLit> expert_lit = new ArrayList<ExpertLit>();
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		try
		{
			pre = con.prepareStatement("SELECT * FROM ExpLiteratureTable WHERE expert_name LIKE ?");
			pre.setString(1, expert_name);
			rs = pre.executeQuery();
			while(rs.next())
			{
				ExpertLit lit = new ExpertLit();
				lit.setNO(rs.getInt("NO"));
				lit.setTitle(rs.getString("title"));
				lit.setApp_date(rs.getString("app_date"));
				lit.setJournal_cn(rs.getString("journal_cn"));
				lit.setExpert_name(rs.getString("expert_name"));
				lit.setAuthor_cn(rs.getString("author_cn"));
				lit.setUnit(rs.getString("unit"));
				lit.setAbs(rs.getString("abs"));
				lit.setKeywords(rs.getString("keywords"));
				expert_lit.add(lit);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return expert_lit;
	}
	//浏览专家情报信息
	public ArrayList<ExpertIntell> browseIntell(String expert_name)
	{
		ArrayList<ExpertIntell> expert_intell = new ArrayList<ExpertIntell>();
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		try
		{
			pre = con
					.prepareStatement("SELECT * FROM ExpIntelligenceTable WHERE expert_name LIKE ?");
			pre.setString(1, expert_name);
			rs = pre.executeQuery();
			while(rs.next())
			{
				ExpertIntell intell = new ExpertIntell();
				intell.setNO(rs.getInt("NO"));
				intell.setExpert_name(rs.getString("expert_name"));
				intell.setTitle(rs.getString("title"));
				intell.setDate(rs.getString("date"));
				intell.setAbs(rs.getString("abs"));
				expert_intell.add(intell);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return expert_intell;
	}
	//浏览专家专利信息
	public ArrayList<ExpertPatent> browsePatent(String expert_name)
	{
		ArrayList<ExpertPatent> expert_pat = new ArrayList<ExpertPatent>();
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		try
		{
			pre = con
					.prepareStatement("SELECT * FROM ExpPatentTable WHERE expert_name LIKE ?");
			pre.setString(1, expert_name);
			rs = pre.executeQuery();
			while(rs.next())
			{
				ExpertPatent pat = new ExpertPatent();
				pat.setNO(rs.getInt("NO"));
				pat.setExpert_name(rs.getString("expert_name"));
				pat.setTitle(rs.getString("title"));
				pat.setDate(rs.getString("date"));
				pat.setApplicant(rs.getString("applicant"));
				pat.setAbs(rs.getString("abs"));
				expert_pat.add(pat);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return expert_pat;
	}
}
