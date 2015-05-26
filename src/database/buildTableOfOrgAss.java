package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class buildTableOfOrgAss
{
	// ����ר�һ������ֱ�
	public static void main(String[] args)
	{
		try
		{
			File path = new File(
					"F:\\Codes\\CNSoftCup\\ExpertFileOperation\\GeneratedFiles\\ExpName.txt");
			BufferedReader fileReader = new BufferedReader(new FileReader(path));
			File namepath = new File(
					"F:\\Codes\\CNSoftCup\\ExpertFileOperation\\GeneratedFiles\\OrgName.txt");
			File scorepath = new File(
					"F:\\Codes\\CNSoftCup\\ExpertFileOperation\\GeneratedFiles\\OrgScore.txt");
			BufferedReader nameReader = new BufferedReader(new FileReader(
					namepath));
			BufferedReader scoreReader = new BufferedReader(new FileReader(
					scorepath));
			List<String> orgname = new ArrayList<String>();
			List<String> orgscore = new ArrayList<String>();
			String namebuf = null, scorebuf = null;
			namebuf = nameReader.readLine();
			scorebuf = scoreReader.readLine();
			while (namebuf != null)
			{
				orgname.add(namebuf);
				orgscore.add(scorebuf);
				namebuf = nameReader.readLine();
				scorebuf = scoreReader.readLine();
			}

			File file = new File(
					"F:\\Codes\\CNSoftCup\\ExpertFileOperation\\GeneratedFiles\\ExpOrgAssTable.txt");
			FileWriter filewriter = new FileWriter(file, true);

			String buff = null;
			buff = fileReader.readLine();
			while (buff != null)
			{
				// ��������
				String Org = null;
				char[] buf = buff.toCharArray();
				if (buf[3] == '\t')// ר����������
				{
					Org = buff.substring(4);
				} else
				// ר����������
				{
					Org = buff.substring(3);
				}
				int flag = 0;
				filewriter.write(buff + "\t");
				for (int i = 0; i < orgname.size(); ++i)
				{
					String thisorg = orgname.get(i);
					int thisscore = (int) Double.parseDouble(orgscore.get(i));
					if (Org.indexOf(thisorg) >= 0)
					{
						filewriter.write(thisscore + "\n");
						flag = 1;
						break;
					}
				}
				if (flag == 0)
				{
					if (buff.indexOf("�ص�ʵ����") >= 0)
					{
						filewriter.write("80" + "\n");
					} else if (buff.indexOf("ʵ����") >= 0)
					{
						filewriter.write("60" + "\n");
					} else if (buff.indexOf("����") >= 0
							|| buff.indexOf("�Ϻ�") >= 0)
					{
						filewriter.write("71" + "\n");
					} else if (buff.indexOf("����") >= 0
							|| buff.indexOf("�㽭") >= 0
							|| buff.indexOf("����") >= 0
							|| buff.indexOf("����") >= 0
							|| buff.indexOf("����") >= 0
							|| buff.indexOf("ɽ��") >= 0
							|| buff.indexOf("̨��") >= 0)// ����
					{
						filewriter.write("70" + "\n");
					} else if (buff.indexOf("�㶫") >= 0
							|| buff.indexOf("����") >= 0
							|| buff.indexOf("����") >= 0
							|| buff.indexOf("���") >= 0
							|| buff.indexOf("����") >= 0)// ����
					{
						filewriter.write("68" + "\n");
					} else if (buff.indexOf("����") >= 0
							|| buff.indexOf("����") >= 0
							|| buff.indexOf("����") >= 0)// ����
					{
						filewriter.write("64" + "\n");
					} else if (buff.indexOf("���") >= 0
							|| buff.indexOf("�ӱ�") >= 0
							|| buff.indexOf("ɽ��") >= 0
							|| buff.indexOf("���ɹ�") >= 0)// ����
					{
						filewriter.write("60" + "\n");
					} else if (buff.indexOf("������") >= 0
							|| buff.indexOf("����") >= 0
							|| buff.indexOf("����") >= 0)// ����
					{
						filewriter.write("56" + "\n");
					} else if (buff.indexOf("����") >= 0
							|| buff.indexOf("����") >= 0
							|| buff.indexOf("����") >= 0
							|| buff.indexOf("����") >= 0
							|| buff.indexOf("�Ĵ�") >= 0)// ����
					{
						filewriter.write("52" + "\n");
					} else if (buff.indexOf("����") >= 0
							|| buff.indexOf("����") >= 0
							|| buff.indexOf("�ຣ") >= 0
							|| buff.indexOf("����") >= 0
							|| buff.indexOf("�½�") >= 0)// ����
					{
						filewriter.write("48" + "\n");
					} else
					{
						filewriter.write("30" + "\n");
					}
				}
				buff = fileReader.readLine();
			}
			filewriter.close();
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
