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
	// 建立专家机构评分表
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
				// 进行评估
				String Org = null;
				char[] buf = buff.toCharArray();
				if (buf[3] == '\t')// 专家名两个字
				{
					Org = buff.substring(4);
				} else
				// 专家名三个字
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
					if (buff.indexOf("重点实验室") >= 0)
					{
						filewriter.write("80" + "\n");
					} else if (buff.indexOf("实验室") >= 0)
					{
						filewriter.write("60" + "\n");
					} else if (buff.indexOf("北京") >= 0
							|| buff.indexOf("上海") >= 0)
					{
						filewriter.write("71" + "\n");
					} else if (buff.indexOf("江苏") >= 0
							|| buff.indexOf("浙江") >= 0
							|| buff.indexOf("安徽") >= 0
							|| buff.indexOf("福建") >= 0
							|| buff.indexOf("江西") >= 0
							|| buff.indexOf("山东") >= 0
							|| buff.indexOf("台湾") >= 0)// 华东
					{
						filewriter.write("70" + "\n");
					} else if (buff.indexOf("广东") >= 0
							|| buff.indexOf("广西") >= 0
							|| buff.indexOf("海南") >= 0
							|| buff.indexOf("香港") >= 0
							|| buff.indexOf("澳门") >= 0)// 华南
					{
						filewriter.write("68" + "\n");
					} else if (buff.indexOf("河南") >= 0
							|| buff.indexOf("湖北") >= 0
							|| buff.indexOf("湖南") >= 0)// 华中
					{
						filewriter.write("64" + "\n");
					} else if (buff.indexOf("天津") >= 0
							|| buff.indexOf("河北") >= 0
							|| buff.indexOf("山西") >= 0
							|| buff.indexOf("内蒙古") >= 0)// 华北
					{
						filewriter.write("60" + "\n");
					} else if (buff.indexOf("黑龙江") >= 0
							|| buff.indexOf("辽宁") >= 0
							|| buff.indexOf("吉林") >= 0)// 东北
					{
						filewriter.write("56" + "\n");
					} else if (buff.indexOf("重庆") >= 0
							|| buff.indexOf("贵州") >= 0
							|| buff.indexOf("云南") >= 0
							|| buff.indexOf("西藏") >= 0
							|| buff.indexOf("四川") >= 0)// 西南
					{
						filewriter.write("52" + "\n");
					} else if (buff.indexOf("陕西") >= 0
							|| buff.indexOf("甘肃") >= 0
							|| buff.indexOf("青海") >= 0
							|| buff.indexOf("宁夏") >= 0
							|| buff.indexOf("新疆") >= 0)// 西北
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
