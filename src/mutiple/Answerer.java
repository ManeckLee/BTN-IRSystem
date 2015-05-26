package mutiple;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import process.Spliter;
import database.Querryer;

public class Answerer
{

	private Spliter spliter;

	private Filter filter;

	private Set<String> splite(String sentence)
	{
		this.spliter.split(sentence);

		return this.spliter.getSpliteResult();
	}

	private void init()
	{
		this.spliter = new Spliter();
		
		this.filter = new Filter();
	}
	
	private List<AbsInfo> answerBy(InformationRobot robot,Set<String> words)
	{
		return robot.getAnswer(words);
	}
	
	public Answerer()
	{
		this.init();
	}

	public Map<String, List<AbsInfo>> answer(String sentence)
	{
		Set<String> allWords = this.splite(sentence);
		Set<String> words = this.filter.filte(allWords);
		
		Map<String,List<AbsInfo>> answer = new HashMap<String, List<AbsInfo>>();
		List<AbsInfo> patentAnswer = this.answerBy(new Patent(), words);
		List<AbsInfo> literatureAnswer = this.answerBy(new Literature(),words);
		List<AbsInfo> intelligenceAnswer = this.answerBy(new Intelligence(),words);
		
		answer.put("patent", patentAnswer);
		answer.put("literature", literatureAnswer);
		answer.put("intelligence", intelligenceAnswer);
		return answer;
	}

}
