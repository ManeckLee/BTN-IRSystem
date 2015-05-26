package database;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * �����������ձ��ר�ҽ����������ó�����ָ�������Լ��ۺ�����
 * 
 * @author maneck
 *
 */
public class buildTableOfExpAssessment
{
	public static void main(String[] args)
	{
		// ����ר�����������������ר�ҿ��гɹ�������������������
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
				// ��ר����������������ȡ����
				String[] OrgList = OrgBuff.split("\t");
				int OrgGrade = Integer.parseInt(OrgList[OrgList.length - 1]);

				String[] digitList = LitBuff.split("\t");
				int LitGrade = Integer.parseInt(digitList[1]);
				int IntelligenceGrade = Integer.parseInt(digitList[2]);
				int PatentGrade = Integer.parseInt(digitList[3]);
				// �ó��ۺ�����
				int comGrade = OrgGrade + LitGrade + IntelligenceGrade
						+ PatentGrade * 5;
				// ��ר���ۺ��������д�����Ϣ
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
