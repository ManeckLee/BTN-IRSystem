package matrix;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import database.DatabaseOp;
import database.DbConntion;

/**
 * @author lxyzk
 *
 */
public class TfIdf
{
	/**
	 * ���˴�������û���õĴ�
	 */
	private Set<String> dirtWordList;

	private Map<String, Map<String, Double>> tfMap;

	private Map<String, Double> idf;

	private Map<String, Map<String, Double>> tfidf;

	/**
	 * �ִʺ���ļ����ļ��У��༴����·��
	 */
	private File wordPath;

	private File keyWordPath;

	private int fileCount;

	private int fileFlag = 0; // is name of a file?

	private Map<String, Integer> frequencyMap;

	private String fileName;

	private Connection con;

	private String databaseName;

	private String tableName;

	private int idCount = 0;

	private double importantThresholdValue; // �Ƿ�Ϊ��Ҫ�ʵ���ֵ

	public Set<String> getDirtWordList()
	{
		return dirtWordList;
	}

	public Map<String, Map<String, Double>> getTfMap()
	{
		return tfMap;
	}

	public Map<String, Double> getIdf()
	{
		return idf;
	}

	public Map<String, Map<String, Double>> getTfidf()
	{
		return tfidf;
	}

	public File getWordPath()
	{
		return wordPath;
	}

	public File getKeyWordPath()
	{
		return keyWordPath;
	}

	public int getFileCount()
	{
		return fileCount;
	}

	public int getFileFlag()
	{
		return fileFlag;
	}

	public Map<String, Integer> getFrequencyMap()
	{
		return frequencyMap;
	}

	public String getFileName()
	{
		return fileName;
	}

	private void addFrequencyMapTotfMap(String fileName,
			Map<String, Double> fileTfMap)
	{
		this.tfMap.put(fileName, fileTfMap);
	}

	private void addToFrequencyMap(Map<String, Integer> map, String str)
	{
		Integer frequency = null;
		if ((frequency = map.get(str)) == null)
		{
			map.put(str, 1);
		}
		else
		{
			map.put(str, frequency + 1);
		}
	}

	private Map<String, Integer> buildExpertTFMap(List<String> list) // not used
	{
		Iterator<String> ite = list.iterator();
		Map<String, Integer> map = new HashMap<String, Integer>();
		while (ite.hasNext())
		{
			this.addToFrequencyMap(map, ite.next());
		}
		return map;
	}

	private Map<String, Double> convertToFileTFMap(
			Map<String, Integer> frequencyMap, double size)
	{
		Map<String, Double> tf = new HashMap<String, Double>();
		Iterator<String> ite = frequencyMap.keySet().iterator();
		while (ite.hasNext())
		{
			String word = ite.next();
			Integer frequency = frequencyMap.get(word);

			tf.put(word, Double.valueOf((frequency / size)));

		}

		return tf;
	}

	private void readFileOfDirectory(File file)
	{
		BufferedReader reader = null;
		String line = null;
		Map<String, Integer> map = new HashMap<String, Integer>();
		Map<String, Double> fileTf = null;
		;
		try
		{
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF-8"));
			String fileName = file.getName();
			double wordCount = 0;
			while ((line = reader.readLine()) != null)
			{
				this.addToFrequencyMap(map, line);
				wordCount++;
			}
			fileTf = this.convertToFileTFMap(map, wordCount);
			this.addFrequencyMapTotfMap(fileName, fileTf);
			reader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void readDirectory()
	{
		File[] files = this.wordPath.listFiles();
		for (File f : files)
		{
			System.out.println("Reading : " + f.toString());
			this.readFileOfDirectory(f);
		}
	}

	private String createFileName(String line)
	{
		return line.replace(" ", "");
	}

	private void scanLine(String line)
	{
		Scanner sc = new Scanner(line).useDelimiter("\\s+");
		while (sc.hasNext())
		{
			this.addToFrequencyMap(this.frequencyMap, sc.next());
		}
		sc.close();
	}

	private void addToMap(String line, boolean isFileName)
	{
		if (isFileName)
		{
			if (this.frequencyMap != null)
			{
//				this.addFrequencyMapTotfMap(fileName, this.frequencyMap);
			}
			this.fileName = this.createFileName(line);
			System.out.println(fileName);
			this.frequencyMap = new HashMap<String, Integer>();
		}
		this.scanLine(line);
	}

	private boolean isFileName(String lineNum)
	{
		if (!lineNum.equals(Integer.toString(this.fileFlag)))
		{
			this.fileFlag++;
			return true;
		}
		return false;
	}

	private void readFile()
	{
		BufferedReader reader = null;
		String line = null;
		String[] aline = null;
		int lineNum = 0;
		try
		{
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(this.wordPath), "UTF-8"));
			while ((line = reader.readLine()) != null)
			{
				aline = line.split("\\s+", 2);
				System.out.println(aline[0]);
				System.out.println(aline[1]);
				// System.out.println(this.isFileName(aline[0]));
				this.addToMap(aline[1], this.isFileName(aline[0]));
			}
			this.addToMap("", true);
			reader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void computeNiOfFile(Map<String,Double> frequencyMap,
			Map<String, Double> ni)
	{
		Iterator<String> ite = frequencyMap.keySet().iterator();
		Double value = null;
		while (ite.hasNext())
		{
			String word = ite.next();
			if ((value = ni.get(word)) == null)
			{
				ni.put(word, 1.0);
			}
			else
			{
				ni.put(word, value + 1);
			}
		}
	}

	private Map<String, Double> computeNi()
	{
		System.out.println("computingNi....");
		Iterator<Map.Entry<String, Map<String, Double>>> ite = this.tfMap
				.entrySet().iterator();
		Map<String, Double> ni = new HashMap<String, Double>();
		while (ite.hasNext())
		{
			Map<String,Double> frequencyMap = ite.next().getValue();

			this.computeNiOfFile(frequencyMap, ni);
		}
		return ni;
	}

	private void idf()
	{
		Map<String, Double> Ni = this.computeNi();
		this.fileCount = this.tfMap.size();
		Iterator<Map.Entry<String, Double>> ite = Ni.entrySet().iterator();
		while (ite.hasNext())
		{
			Map.Entry<String, Double> ni = ite.next();
			double idf = Math.log(this.fileCount / (ni.getValue() + 1));
			this.idf.put(ni.getKey(), idf);
		}
	}

	private Map<String, Double> computeFileTFIDF(Map<String, Double> map)
	{
		Map<String, Double> tfidfmap = new HashMap<String, Double>();
		Iterator<Map.Entry<String, Double>> ite = map.entrySet().iterator();
		while (ite.hasNext())
		{
			Map.Entry<String, Double> mapentry = ite.next();
			String word = mapentry.getKey();
			double tfidf = mapentry.getValue() * this.idf.get(word) * 10;
			if (this.isImportant(tfidf))
			{
				tfidfmap.put(word, tfidf);
			}
			else
			{
				this.dirtWordList.add(word);
			}
		}
		return tfidfmap;
	}

	public void read() // TODO directory or a file?
	{
		if (this.wordPath.isDirectory())
		{
			this.readDirectory();
		}
		else
		{
			this.readFile();
		}
	}

	public TfIdf(String wordPath)
	{
		this.wordPath = new File(wordPath);
		this.keyWordPath = new File("keyWords");
		this.init();
	}

	public TfIdf(String wordPath, String keyWordPath)
	{
		this.wordPath = new File(wordPath);
		this.keyWordPath = new File(keyWordPath);
		this.init();
	}

	private void computeTFIDF()
	{
		Iterator<Map.Entry<String, Map<String, Double>>> ite = this.tfMap
				.entrySet().iterator();
		while (ite.hasNext())
		{
			Map.Entry<String, Map<String, Double>> mapEntry = ite.next();
			String fileName = mapEntry.getKey();
			Map<String, Double> fileTfidf = this.computeFileTFIDF(mapEntry
					.getValue());
			this.tfidf.put(mapEntry.getKey(), fileTfidf);
		}
	}

	private boolean isImportant(Map.Entry<String, Double> me) // �ж�һ�����Ƿ�����Ҫ
	{
		return me.getValue() > 1.0000;
	}

	/**
	 * ���ݸ����ʵ�tfidfֵ���ж�һ�����ǲ�����Ҫ.
	 * 
	 * @param tfidf
	 *            �����ʵ�tfidf
	 * @return ��Ҫ����true�����򷵻�false
	 */
	private boolean isImportant(double tfidf)
	{
		return tfidf > this.importantThresholdValue;
		// return true;
	}

	/**
	 * ���ݸ����ʵ�idfֵ���ж�һ�����ǲ�����Ҫ
	 * 
	 * @param idf
	 *            �����ʵ�idfֵ
	 * @return ��Ҫ����true�����򷵻�false
	 */
	private boolean isIdfImportant(double idf)
	{
		return idf > 5.000;
	}

	public Map<String, Map<String, Double>> getTFIDF()
	{
		return this.tfidf;
	}

	public void write() // TODO todosomething
	{
		File file = new File("tf_idf.txt");
		BufferedWriter writer = null;
		try
		{
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "UTF-8"));
			Iterator<Map.Entry<String, Map<String, Double>>> ite = this.tfidf
					.entrySet().iterator();
			while (ite.hasNext())
			{
				Map.Entry<String, Map<String, Double>> me = ite.next();
				String fileName = me.getKey();
				writer.append("*");
				writer.append(fileName);
				writer.append("\n");
				this.writeAFile(me.getValue(), writer);
			}
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void writeAFile(Map<String, Double> map, BufferedWriter writer)
	{
		Iterator<Map.Entry<String, Double>> ite = map.entrySet().iterator();
		try
		{
			while (ite.hasNext())
			{
				Map.Entry<String, Double> me = ite.next();
				// if (this.isImportant(me) == true) // output the word that is
				// important
				// {
				writer.append(me.toString());
				writer.append("\n");
				// }
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void createDirtWordsDict()
	{
		BufferedWriter writer = null;
		try
		{
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("dirtDict"), "UTF-8"));
			Iterator<String> ite = this.dirtWordList.iterator();
			while (ite.hasNext())
			{
				writer.append(ite.next());
				writer.append("\n");
			}
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * �������ݿ�
	 * 
	 * @return ���õ����ݿ�����
	 */
	private String createDatabase()
	{
		return DatabaseOp.createDatabase(this.databaseName);
	}

	/**
	 * ����һ�ű�
	 * 
	 * @param tableName
	 *            Ҫ�����ı���
	 * @param databaseName
	 *            �ѱ������ĸ����ݿ�
	 * @return �����õı�������
	 */
	private String createTable(String tableName, String databaseName)
	{
		String columLable = "id bigint NOT NULL PRIMARY KEY, name varchar(200), word varchar(200) , value float ";

		String name = DatabaseOp.createTable(tableName, columLable,
				databaseName);

		this.createIndex();

		return name;
	}

	private void createIndex()
	{
		String wordIndexSQL = "CREATE NONCLUSTERED INDEX wordindex ON tfidf(word);";
		String nameIndexSQL = "CREATE NONCLUSTERED INDEX nameindex ON tfidf(name);";
		// String apartIndexSQL =
		// "CREATE NONCLUSTERED INDEX nameindex ON tfidf(apart);";

		DatabaseOp.createIndex(wordIndexSQL, this.databaseName);
		DatabaseOp.createIndex(nameIndexSQL, this.databaseName);
		// DatabaseOp.createIndex(apartIndexSQL, this.databaseName);
	}

	/**
	 * ����this.databaseName��ȡһ���ر��Զ��ύ(setAutoCommint(false))��connection
	 * 
	 */
	private void getDatabaseConnection()
	{
		DbConntion dc = new DbConntion(this.databaseName);
		this.con = dc.getManualCommitConnection();
	}

	/**
	 * �������tfidfд�����
	 * 
	 * @param word
	 *            ����
	 * @param value
	 *            tfidfֵ
	 * @param expertName
	 *            ר����
	 * @param pstmt
	 *            Ԥ�����statement,���������������
	 */
	private void insertIntoTable(String word, Double value, String expertName,
			PreparedStatement pstmt)
	{
		try
		{
			// pstmt.setString(1, "tfiidf");
			pstmt.setInt(2 - 1, ++this.idCount);
			pstmt.setString(3 - 1, expertName);
			pstmt.setString(4 - 1, word);
			pstmt.setFloat(5 - 1, value.floatValue());
			pstmt.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * ��ĳ��ר�ҵ����д�д�뵽����
	 * 
	 * @param expertName
	 *            ר������
	 * @param expert
	 *            ר�Ҷ�Ӧ�����дʼ���tfidf
	 * @param pstmt
	 *            Ԥ�����statement,���������������
	 */
	private void writeTfidfToDataBase(String expertName,
			Map<String, Double> expert, PreparedStatement pstmt)
	{

		Iterator<Map.Entry<String, Double>> ite = expert.entrySet().iterator();
		while (ite.hasNext()) // ����
		{
			Map.Entry<String, Double> me = ite.next();
			String word = me.getKey();
			Double value = me.getValue();

			this.insertIntoTable(word, value, expertName, pstmt);
		}
	}

	public void writeMapToDatabase(Map<String, Double> map, String tableName)
	{
		DbConntion dc = new DbConntion();
		Connection con = dc.getManualCommitConnection();
		PreparedStatement pstmt = null;

		StringBuffer insertSQL = new StringBuffer("INSERT INTO ").append(
				tableName).append(" VALUES (?,?);");
		try
		{
			pstmt = con.prepareStatement(insertSQL.toString());

			Iterator<Map.Entry<String, Double>> ite = map.entrySet().iterator();
			while (ite.hasNext())
			{
				Map.Entry<String, Double> me = ite.next();

				String word = me.getKey();
				Double value = me.getValue();

				if (this.isImportant(value)) // TODO: is idf important
												// ��дһ���������ж�һ��idf�ǲ�����Ҫ
				{
					// System.out.println(word);
					pstmt.setString(1, word);
					// System.out.println(value);
					pstmt.setFloat(2, value.floatValue());

					pstmt.executeUpdate();
				}
			}

			con.commit();

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void writeIdfToDatabase()
	{
		String tableName = "idf";
		String createSQL = "id bigint NOT NULL IDENTITY PRIMARY KEY,word varchar(200) NOT NULL , value float";
		String databaseName = DatabaseOp.createTable(tableName, createSQL,
				"expert");
		if (null == databaseName)
		{
			return;
		}

		String indexSQL = "CREATE NONCLUSTERED INDEX wordindex ON " + tableName
				+ "(word);";
		DatabaseOp.createIndex(indexSQL, "expert");
		this.writeMapToDatabase(this.idf, tableName);

	}

	/**
	 * ��tfidfֵд�뵽���ݿ���
	 * 
	 */
	public void writeToDatabase()
	{
		this.databaseName = "expert";

		this.tableName = "tfidf";

		// String databaseName = this.createDatabase();

		if (databaseName == null) // ���ݿⴴ��ʧ��
		{
			System.out.println("Failed to createDatabase!");
			return;
		}

		this.getDatabaseConnection();

		PreparedStatement pstmt = null;
		StringBuffer insertSQL = new StringBuffer("INSERT INTO ");
		insertSQL.append(this.tableName).append(" VALUES(?,?,?,?);");
		// String insertSQL = "INSERT INTO tfiidf VALUES (?,?,?,?);";
		try
		{
			pstmt = this.con.prepareStatement(insertSQL.toString());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		this.createTable(this.tableName, this.databaseName);

		Iterator<Map.Entry<String, Map<String, Double>>> ite = this.tfidf
				.entrySet().iterator();
		while (ite.hasNext()) // ��������ר��
		{
			Map.Entry<String, Map<String, Double>> me = ite.next();
			Map<String, Double> expert = me.getValue();
			String expertName = me.getKey();
			this.writeTfidfToDataBase(expertName, expert, pstmt); // ����ĳ��ר�Ҷ�Ӧ�����д�
		}

		try
		{
			this.con.commit();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void compute()
	{
		this.read();
		// this.outputwordlist();
		// this.buildTFMap();
		this.idf();
		this.computeTFIDF();
	}

	private void init()
	{
		this.tfMap = new HashMap<String, Map<String,Double>>();
		this.idf = new HashMap<String, Double>();
		this.tfidf = new HashMap<String, Map<String, Double>>();
		this.dirtWordList = new HashSet<String>();
		this.importantThresholdValue = 0.1;
		// this.read();
		// this.idf();
		// this.computeTFIDF();
	}

	public TfIdf()
	{
		this.wordPath = new File("words");
		this.keyWordPath = new File("keyWords");
		this.init();
	}

	public static void main(String[] args)
	{
		TfIdf ti = new TfIdf();
		// ti.write();
		ti.compute();
		ti.writeToDatabase();
		// ti.writeIdfToDatabase();
		// ti.createDirtWordsDict();
	}
}