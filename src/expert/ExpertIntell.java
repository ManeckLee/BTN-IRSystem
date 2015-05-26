package expert;

//专家文献模型
public class ExpertIntell
{
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getAbs()
	{
		return abs;
	}

	public void setAbs(String abs)
	{
		this.abs = abs;
	}

	public String getExpert_name()
	{
		return expert_name;
	}

	public void setExpert_name(String expert_name)
	{
		this.expert_name = expert_name;
	}
	public int getNO()
	{
		return NO;
	}

	public void setNO(int nO)
	{
		NO = nO;
	}
	private int NO;
	private String expert_name = null;
	private String title = null;
	private String date = null;
	private String abs = null;

}
