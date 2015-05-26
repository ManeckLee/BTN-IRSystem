package recommend;

import java.util.List;

public class RecomendInfo implements Comparable<RecomendInfo>
{
	private String expertName;

	private ExpertInfo expertInfo;
	
	public RecomendInfo(String expertName,ExpertInfo expertInfo)
	{
		this.expertName = expertName;
		
		this.expertInfo = expertInfo;
	}
	
	public String getExpertName()
	{
		return this.expertName;
	}

	public Double getTfIdf()
	{
		return this.expertInfo.getTfidf();
	}
	
	/**
	 * @return ·µ»ØÍÆ¼öÒÀ¾Ý
	 */
	public List<String> getKeyWords() 
	{
		return this.expertInfo.getKeyWords();
	}

	@Override
	public int compareTo(RecomendInfo o)
	{
		return -Double.compare(o.getTfIdf(),this.getTfIdf());
	}
}
