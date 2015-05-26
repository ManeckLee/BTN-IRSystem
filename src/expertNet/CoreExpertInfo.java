package expertNet;

public class CoreExpertInfo implements Comparable<CoreExpertInfo>
{
	private String expert;
	
	private double rank;
	
	public String toString()
	{
		return this.expert + ":" + rank;
	}

	@Override
	public int compareTo(CoreExpertInfo o)
	{
		return -Double.compare(this.rank, o.rank);
	}

	public String getExpert()
	{
		return expert;
	}

	public void setExpert(String expert)
	{
		this.expert = expert;
	}

	public double getRank()
	{
		return rank;
	}

	public void setRank(double rank)
	{
		this.rank = rank;
	}
	
	public CoreExpertInfo(String expert, double rank)
	{
		this.expert = expert;
		this.rank = rank;
	}

}
