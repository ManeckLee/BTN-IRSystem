package matrix;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.StampedLock;

import database.DatabaseOp;
import database.DbConntion;

public class TripleTable<T,U,V>
{
	protected Map<T, Map<U, V>> tripleTable;

	protected int databaseIndexCount = 0;

	public TripleTable()
	{
		this.tripleTable = new TreeMap<T, Map<U, V>>();
	}

	public boolean contains(int keyindex, int expertindex)
	{
		if (this.tripleTable.containsKey(keyindex))
		{
			if (this.tripleTable.get(keyindex).containsKey(expertindex))
			{
				return true;
			}
		}
		return false;
	}

	public void put(T keyindex, U expertindex, V value)
	{
		Map<U, V> entryMap = new TreeMap<U, V>();
		entryMap.put(expertindex, value);
		this.tripleTable.put(keyindex, entryMap);
	}

	public V get(T keyindex, U expertindex)
	{
		Map<U, V> map = null;
		if ((map = this.tripleTable.get(keyindex)) != null)
		{
			return map.get(expertindex);
		}
		return null;
	}

	public Map<U, V> get(T keyindex)
	{
		return this.tripleTable.get(keyindex);
	}

	public Set<T> keySet()
	{
		return this.tripleTable.keySet();
	}

	public void createIndex()
	{
		String keyWordIndexSQL = "CREATE NONCLUSTERED INDEX keywordindex ON SpMatrix(keywordid);";

		String expertIndexSQL = "CREATE NONCLUSTERED INDEX expertidindex ON SpMatrix(expertid);";

		DatabaseOp.createIndex(expertIndexSQL, "expert");
		DatabaseOp.createIndex(keyWordIndexSQL, "expert");
	}

	public void writeToDatabase()
	{
		String tableName = "SpMatrix";
		String columLable = "id bigint NOT NULL PRIMARY KEY,"
				+ " keywordid bigint NOT NULL REFERENCES keywords (ID) ON UPDATE CASCADE ON DELETE CASCADE, "
				+ "expertid bigint NOT NULL  REFERENCES Expert (ID) ON UPDATE CASCADE ON DELETE CASCADE, "
				+ "value float NOT NULL ";

		DatabaseOp.createTable(tableName, columLable, "expert"); // 创建表
		
		this.createIndex();
		
		DbConntion dc = new DbConntion();
		Connection mcon = dc.getManualCommitConnection();

		StringBuffer insertSQL = new StringBuffer("INSERT INTO ").append(
				tableName).append(" VALUES (?,?,?,?);");
		PreparedStatement pstmt = null;
		try
		{
			pstmt = mcon.prepareStatement(insertSQL.toString());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		Iterator<Map.Entry<T, Map<U, V>>> iite = this.tripleTable
				.entrySet().iterator();

		while (iite.hasNext())
		{
			Map.Entry<T, Map<U, V>> wme = iite.next();
			T keywordID = wme.getKey();
			Map<U, V> m = wme.getValue(); // 关键词对应的 expert<-->value
														// Map
			Iterator<Map.Entry<U, V>> ite = m.entrySet().iterator();
			while (ite.hasNext())
			{
				Map.Entry<U, V> me = ite.next();
				U expertID = me.getKey();
				V value = me.getValue();
				try
				{
					pstmt.setInt(1, ++this.databaseIndexCount);
					pstmt.setObject(2, keywordID);
					pstmt.setObject(3, expertID);
					pstmt.setObject(4, value);
					pstmt.executeUpdate();
				}
				catch (SQLException e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
		
		// TODO : 建立 keywordid expertid 的 非聚集索引
		try
		{
			mcon.commit();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		System.out.println("total : " + this.databaseIndexCount);
	}
}
