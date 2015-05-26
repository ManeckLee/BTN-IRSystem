package expertNet;

import java.util.HashSet;
import java.util.Set;

public class RelationInfo
{
	private Set<String> relations;
	
	private double weight;
	
	public String toString()
	{
		return Double.toString(this.weight);
	}
	public boolean hasRelation(String relation)
	{
		return this.relations.contains(relation);
	}
	
	public Set<String> getRelations()
	{
		return this.relations;
	}
	
	public void addRelation(String relation)
	{
		this.relations.add(relation);
	}

	public double getWeight()
	{
		return weight;
	}

	public void setWeight(double weight)
	{
		this.weight = weight;
	}
	
	public void updateWeight(double weight)
	{
		this.weight += weight;
	}
	
	private void init()
	{
		this.relations = new HashSet<String>();
	}
	
	public RelationInfo()
	{
		this.init();
		this.weight = 0;
	}
	
	public RelationInfo(String relation,double weight)
	{
		this.init();
		this.weight = weight;
		this.addRelation(relation);
	}
	

}
