package expertNet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import database.Querryer;

public class ExpertRelationNet
{

	private final int WORD = 1;

	private PageRankMatrix<Integer, Integer, RelationInfo> expertsMatrix;

	private Relation relation;

	private PageRank pageRank;

	private Map<String, Integer> stringIndex;

	private Map<Integer, String> integerIndex;

	private Querryer querryer;

	private int expertIDCount;

	private List<CoreExpertInfo> coreExperts;
	
	private File pageRankPath;

	private Set<String> themeExperts; // 与主题词有关的专家

	private void init()
	{
		this.expertsMatrix = new PageRankMatrix<Integer, Integer, RelationInfo>();

		this.themeExperts = new HashSet<String>();
		
		this.pageRankPath = new File("D:\\tnb\\PageRank");

		this.pageRank = new PageRank(this.pageRankPath.getAbsolutePath());

		this.stringIndex = new HashMap<String, Integer>();

		this.integerIndex = new HashMap<Integer, String>();

		this.querryer = new Querryer();
		String querrySQL = "SELECT name FROM tfidf WHERE word = ?";
		this.querryer.querryInit(querrySQL);

		this.expertIDCount = 0;
	}

	public ExpertRelationNet()
	{
		this.init();
	}

	private Integer getIndex(String expert)
	{
		Integer index = null;
		if ((index = this.stringIndex.get(expert)) == null)
		{
			index = expertIDCount++;
			this.stringIndex.put(expert, index);
			this.integerIndex.put(index, expert);
		}

		return index;
	}

	private ResultSet getExperts(String word)
	{
		PreparedStatement pstmt = this.querryer.getSetter();
		ResultSet res = null;
		try
		{
			pstmt.setString(WORD, word);
			res = pstmt.executeQuery();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return res;
	}

	private void addToExpertMatrix(Integer expert1, Integer expert2)
	{
		RelationInfo info = null;
		if ((info = this.expertsMatrix.get(expert2, expert1)) == null)
		{
			if ((info = this.expertsMatrix.get(expert1, expert2)) == null)
			{
				info = new RelationInfo();
			}

			this.relation.updateRelation(info);

			this.expertsMatrix.put(expert1, expert2, info);
		}
	}

	private void addExpertsToExpertsMatrix(List<String> experts)
	{
		for (int i = 0; i < experts.size(); i++)
		{
			for (int j = i + 1; j < experts.size(); j++)
			{
				Integer expert1Index = this.getIndex(experts.get(i));
				Integer expert2Index = this.getIndex(experts.get(j));

				this.addToExpertMatrix(expert1Index, expert2Index);
			}
		}
	}

	private String getExpertNameByIndex(Integer index)
	{
		return this.integerIndex.get(index);
	}

	private CoreExpertInfo parseCoreExpert(String str)
	{
		Scanner scanner = new Scanner(str);
		int expertIndex = -1;
		if (scanner.hasNextInt())
		{
			expertIndex = scanner.nextInt();
		}
		// System.out.println(expertIndex);
		double rank = 0;
		if (scanner.hasNextDouble())
		{
			rank = scanner.nextDouble();
		}
		// System.out.println(rank);
		String expertName = this.getExpertNameByIndex(expertIndex);
		// System.out.println(expertName);
		scanner.close();

		return new CoreExpertInfo(expertName, rank);
	}

	private boolean matrixContains(String expert)
	{
		// Integer expertId = this.getIndex(expert);

		return this.themeExperts.contains(expert);
	}

	private List<String> filte(Set<String> allExperts)
	{
		List<String> experts = new ArrayList<String>();
		Iterator<String> ite = allExperts.iterator();
		while (ite.hasNext())
		{
			String expert = ite.next();
			if (this.matrixContains(expert)) // 是否矩阵中有它
			{
				experts.add(expert);
			}
		}
		return experts;
	}

	private void getCoreExpertsFromFile()
	{
		BufferedReader reader = null;
		String file = this.pageRankPath.getAbsolutePath() + "\\pr.txt";
		List<CoreExpertInfo> coreExperts = new ArrayList<CoreExpertInfo>();
		try
		{
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				// System.out.println(line);
				CoreExpertInfo info = this.parseCoreExpert(line);
				// System.out.println(info);
				coreExperts.add(info);
			}
			reader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		this.coreExperts = coreExperts;
	}

	private void putThemeExpertsToMatrix()
	{
		Iterator<String> ite = this.themeExperts.iterator();
		while (ite.hasNext())
		{
			String expert = ite.next();
			Integer expertIndex = this.getIndex(expert);
			this.expertsMatrix.put(expertIndex, null);
		}

	}

	public void computeCoreExpert()
	{
		this.expertsMatrix.writeToFile(this.pageRankPath + "\\sim.txt");
		this.pageRank.compute();
		this.getCoreExpertsFromFile();
	}

	public List<CoreExpertInfo> getCoreExperts()
	{
		System.out.println(this.coreExperts);
//		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		this.coreExperts.sort(new CoreExpertInfoComparator());
		return this.coreExperts;
	}

	private void addExpertGroupToMatrix(List<Set<String>> expertsGroup)
	{
		Iterator<Set<String>> ite = expertsGroup.iterator();
		while (ite.hasNext())
		{
			Set<String> allExperts = ite.next();
			List<String> experts = this.filte(allExperts);
			this.addExpertsToExpertsMatrix(experts);
		}
	}

	public void buildRelationNet(String word, Relation relation)
	{
		final int EXPERT = 1;

		this.relation = relation;

		ResultSet experts = this.getExperts(word);

		try
		{
			while (experts.next())
			{
				String expert = experts.getString(EXPERT);
				String[] expertSplite = expert.split("_", 2);
				this.themeExperts.add(expertSplite[0]);
			}

			this.putThemeExpertsToMatrix();

			Iterator<String> ite = this.themeExperts.iterator();

			while (ite.hasNext())
			{
				String e = ite.next();
				List<Set<String>> relationExpert = this.relation
						.getRelationExpert(e);

				this.addExpertGroupToMatrix(relationExpert);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		// System.out.println(this.expertsMatrix);

	}

}
