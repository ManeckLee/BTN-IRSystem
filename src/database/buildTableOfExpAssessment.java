package database;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 根据评估参照表对专家进行评估并得出各项指标评分以及综合评分
 * 
 * @author maneck
 *
 */
public class buildTableOfExpAssessment
{
	public static void main(String[] args)
	{
		// 根据专家所属机构评估表和专家科研成果评估表建立最终评估表
		try
		{
			File OrgPath = new File(
					"F:\\Codes\\CNSoftCup\\ExpertFileOperation\\GeneratedFiles\\ExpOrgAssTable.txt");
			File LitPath = new File(
					"F:\\Codes\\CNSoftCup\\ExpertFileOperation\\GeneratedFiles\\ExpLitAssTable.txt");
			BufferedReader OrgReader = new BufferedReader(new FileReader(
					OrgPath));
			BufferedReader LitReader = new BufferedReader(new FileReader(
					LitPath));
			File AssPath = new File(
					"F:\\Codes\\CNSoftCup\\ExpertFileOperation\\GeneratedFiles\\Database\\ExpAssTable.txt");
			FileWriter Asswriter = new FileWriter(AssPath, true);

			String OrgBuff = null, LitBuff = null;
			OrgBuff = OrgReader.readLine();
			int cnt = 0;
			while (OrgBuff != null)
			{
				cnt++;
				LitBuff = LitReader.readLine();
				// 将专家所属机构评分提取出来
				String[] OrgList = OrgBuff.split("\t");
				int OrgGrade = Integer.parseInt(OrgList[OrgList.length - 1]);

				String[] digitList = LitBuff.split("\t");
				int LitGrade = Integer.parseInt(digitList[1]);
				int IntelligenceGrade = Integer.parseInt(digitList[2]);
				int PatentGrade = Integer.parseInt(digitList[3]);
				// 得出综合评分
				int comGrade = OrgGrade + LitGrade + IntelligenceGrade
						+ PatentGrade * 5;
				// 向专家综合评估表中存入信息
				Asswriter.write(cnt + "\t" + LitBuff + "\t" + OrgGrade + "\t" + comGrade +"\n");
				OrgBuff = OrgReader.readLine();
			}
			Asswriter.close();
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
