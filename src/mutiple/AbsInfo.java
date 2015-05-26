package mutiple;

import java.util.HashSet;
import java.util.Set;

public class AbsInfo implements Comparable<AbsInfo>
{
	private Integer ID;
	
	private Set<String> words;
	
	private String robotFlag;

	public String getRobotFlag()
	{
		return robotFlag;
	}

	public void setRobotFlag(String robotFlag)
	{
		this.robotFlag = robotFlag;
	}

	public Integer getID()
	{
		return ID;
	}

	public void setID(Integer iD)
	{
		ID = iD;
	}

	public Set<String> getWords()
	{
		return words;
	}

	public void setWords(Set<String> words)
	{
		this.words = words;
	}
	
	public AbsInfo(Integer ID,String robotFlag)
	{
		this.ID = ID;
		
		this.words = new HashSet<String>();
		
		this.robotFlag = robotFlag;
	}
	
	public String toString()
	{
		return this.ID + this.words.toString();
	}

	@Override
	public int compareTo(AbsInfo o)
	{
		return -Integer.compare(o.words.size(), this.words.size());
	}
}
