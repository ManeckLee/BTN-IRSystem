package expert;

//专家情报模型
public class ExpertLit
{
	public int getNO()
	{
		return NO;
	}
	public void setNO(int nO)
	{
		NO = nO;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getApp_date()
	{
		return app_date;
	}
	public void setApp_date(String app_date)
	{
		this.app_date = app_date;
	}
	public String getJournal_cn()
	{
		return journal_cn;
	}
	public void setJournal_cn(String journal_cn)
	{
		this.journal_cn = journal_cn;
	}
	public String getExpert_name()
	{
		return expert_name;
	}
	public void setExpert_name(String expert_name)
	{
		this.expert_name = expert_name;
	}
	public String getAuthor_cn()
	{
		return author_cn;
	}
	public void setAuthor_cn(String author_cn)
	{
		this.author_cn = author_cn;
	}
	public String getUnit()
	{
		return unit;
	}
	public void setUnit(String unit)
	{
		this.unit = unit;
	}
	public String getAbs()
	{
		return abs;
	}
	public void setAbs(String abs)
	{
		this.abs = abs;
	}
	public String getKeywords()
	{
		return keywords;
	}
	public void setKeywords(String keywords)
	{
		this.keywords = keywords;
	}
	private int NO;
	private String title = null;
	private String app_date = null;
	private String journal_cn = null;
	private String expert_name = null;
	private String author_cn = null;
	private String unit = null;
	private String abs = null;
	private String keywords = null;
}
