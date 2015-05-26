package expertNet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import database.Querryer;

public class Cooperation implements Relation
{
	
	private int EXPERT = 1;

	private String relation;
	
	private double weight;
	
	private Querryer querryer;

	private void init()
	{
		this.relation = "Cooperation";
		this.weight = 10.00;
		this.querryer = new Querryer();
		String querrySQL = "SELECT author_cn FROM ExpLiteratureTable WHERE expert_name = ?";
		this.querryer.querryInit(querrySQL);
	}
	
	public Cooperation()
	{
		this.init();
	}
	
	private ResultSet querry(String expert)
	{
		PreparedStatement pstmt = this.querryer.getSetter();
		ResultSet res = null;
		
		try
		{
			pstmt.setString(EXPERT, expert);
			res = pstmt.executeQuery();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public String getRelation()
	{
		return this.relation;
	}

	@Override
	public void updateRelation(RelationInfo info)
	{
		Set<String> infoRelations = info.getRelations();
		if(!infoRelations.contains(this.relation))
		{
			info.addRelation(this.relation);
		}
		
		info.updateWeight(this.weight);
	}
	
	private void parseQuerryExpertsToSet(String[] strArray, Set<String> experts)
	{
		for(String s : strArray)
		{
			experts.add(s.trim());
		}
	}

	@Override
	public List<Set<String>> getRelationExpert(String expert)
	{
		Set<String> experts = new HashSet<String>();
		List<Set<String>> expertsGroup = new ArrayList<Set<String>>();
		experts.add(expert);
		ResultSet querryExperts = this.querry(expert);
		try
		{
			while(querryExperts.next())
			{
				String expertss = querryExperts.getString(EXPERT);
				this.parseQuerryExpertsToSet(expertss.split(";"), experts);
				expertsGroup.add(experts);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		return expertsGroup;
	}

}
