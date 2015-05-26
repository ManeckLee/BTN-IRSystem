package expert;

//专家专利模型
public class ExpertPatent
{
	public int getNO()
	{
		return NO;
	}
	public void setNO(int nO)
	{
		NO = nO;
	}
	public String getExpert_name()
	{
		return expert_name;
	}
	public void setExpert_name(String expert_name)
	{
		this.expert_name = expert_name;
	}
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
	public String getApplicant()
	{
		return applicant;
	}
	public void setApplicant(String applicant)
	{
		this.applicant = applicant;
	}
	public String getAbs()
	{
		return abs;
	}
	public void setAbs(String abs)
	{
		this.abs = abs;
	}
	private int NO;
	private String expert_name = null;
	private String title = null;
	private String date = null;
	private String applicant = null;
	private String abs = null;
}
