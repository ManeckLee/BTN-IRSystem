package build;

import java.io.File;

import matrix.TfIdf;
import mutiple.Intelligence;
import mutiple.Literature;
import mutiple.Patent;
import process.AbsProcess;
import process.FileProcess;
import process.Spliter;
import database.TableCreater;
import database.buildInfoTableOfIntelligence;
import database.buildInfoTableOfLiterature;
import database.buildInfoTableOfPatent;
import database.buildTableOfExp;

public class InitBuilder
{
	private File basePath;
	private String bitdataPath;
	private String expertDataPath;
	private String absDataPath;
	private String wordsDataPath;

	private void init()
	{

		this.basePath = new File("d:\\tnb");
		this.bitdataPath = this.basePath.getAbsolutePath() + "\\BitData\\";
		this.expertDataPath = this.basePath.getAbsolutePath() + "\\experts\\";
		this.absDataPath = this.basePath.getAbsolutePath() + "\\abs\\";
		this.wordsDataPath = this.basePath.getAbsolutePath() + "\\words\\";
	}

	public InitBuilder()
	{
		this.init();
	}

	public void bitdataProcess()
	{

		System.out.println("Get Expert File ...");
		FileProcess fp = new FileProcess();
		fp.processExpert(this.bitdataPath, this.expertDataPath);

		System.out.println("Get Abs And Title...");
		AbsProcess ap = new AbsProcess();
		ap.processAbsAndTitle(this.expertDataPath, this.absDataPath);

		System.out.println("Splite Word ...");
		Spliter sp = new Spliter();
		try
		{
			sp.spliteFile(this.absDataPath, this.wordsDataPath);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public void buildDatabaseTable()
	{
		System.out.println("Create Table ...");

		TableCreater db = new TableCreater();

		db.createTable();

	}

	public void CreateInformationTable()
	{
//		Builder builder = new Builder();
		Patent patent = new Patent();
		Literature literature = new Literature();
		Intelligence intelligence = new Intelligence();

//		builder.buildInformation(patent);
//		builder.buildInformation(literature);
//		builder.buildInformation(intelligence);
//		builder.writeToDatabase();
	}

	public void insertDataToDatabaseTable()
	{

		System.out.println("Insert Data to intelligenceTable....");
		buildInfoTableOfIntelligence intelligence = new buildInfoTableOfIntelligence();
		intelligence.insertTable(this.basePath.getAbsolutePath() + "\\bitdata\\");

		System.out.println("Insert Data to literatureTable....");
		buildInfoTableOfLiterature literature = new buildInfoTableOfLiterature();
		literature.insertTable(this.basePath.getAbsolutePath() + "\\bitdata\\");

		System.out.println("Insert Data to patentTable....");
		buildInfoTableOfPatent patent = new buildInfoTableOfPatent();
		patent.insertTable(this.basePath.getAbsolutePath() + "\\bitdata\\");

		System.out.println("Insert Data to expinfoTable....");
		buildTableOfExp exp = new buildTableOfExp();
		exp.insertTable(this.basePath.getAbsolutePath());

		System.out.println("Create tfidf Table...");
		TfIdf ti = new TfIdf(this.wordsDataPath);
		ti.compute();
		ti.writeToDatabase();

		System.out.println("Create Infomation Table...");
		this.CreateInformationTable();
	}

	public void build()
	{
		this.bitdataProcess();
		this.buildDatabaseTable();
		this.insertDataToDatabaseTable();
	}

}
