package expert;

//专家详情模型
public class Expert
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
	public String getExpert_org()
	{
		return expert_org;
	}
	public void setExpert_org(String expert_org)
	{
		this.expert_org = expert_org;
	}
	public String getResearchDirection()
	{
		return ResearchDirection;
	}
	public void setResearchDirection(String researchDirection)
	{
		ResearchDirection = researchDirection;
	}
	public int getLiteratureAss()
	{
		return LiteratureAss;
	}
	public void setLiteratureAss(int literatureAss)
	{
		LiteratureAss = literatureAss;
	}
	public int getCntOfIntelligence()
	{
		return CntOfIntelligence;
	}
	public void setCntOfIntelligence(int cntOfIntelligence)
	{
		CntOfIntelligence = cntOfIntelligence;
	}
	public int getCntOfPatent()
	{
		return CntOfPatent;
	}
	public void setCntOfPatent(int cntOfPatent)
	{
		CntOfPatent = cntOfPatent;
	}
	public int getOrgAss()
	{
		return OrgAss;
	}
	public void setOrgAss(int orgAss)
	{
		OrgAss = orgAss;
	}
	public int getComprehensiveAss()
	{
		return ComprehensiveAss;
	}
	public void setComprehensiveAss(int comprehensiveAss)
	{
		ComprehensiveAss = comprehensiveAss;
	}
	private int NO;// 专家编号
	private String expert_name = null;// 专家姓名
	private String expert_org = null;// 工作单位
	private String ResearchDirection = null;// 科研方向
	private int LiteratureAss;// 文献评估
	private int CntOfIntelligence;// 情报统计
	private int CntOfPatent;// 专利统计
	private int OrgAss;// 机构评估
	private int ComprehensiveAss;// 综合评估
}
