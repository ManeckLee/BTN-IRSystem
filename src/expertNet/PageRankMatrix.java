package expertNet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Map;

import matrix.TripleTable;

public class PageRankMatrix<T, U, V> extends TripleTable<T, U, V>
{
	@Override
	public V get(T index1, U index2)
	{
		Map<U, V> m = null;
		if ((m = this.tripleTable.get(index1)) == null)
		{
			this.tripleTable.put(index1, null);
			return null;
		}
		else
		{
			return m.get(index2);
		}
	}
	
	public boolean containsKey(T key)
	{
		return this.tripleTable.containsKey(key);
	}
	
	public void put(T key, Map<U,V> value)
	{
		this.tripleTable.put(key, value);
	}

	public boolean contains(T index1, U index2)
	{
		return this.get(index1, index2) != null;
	}

	protected String mapToString(Map<U, V> map)
	{
		int countFlag = 1;
		StringBuffer sb = new StringBuffer();
		Iterator<U> ite = map.keySet().iterator();
		while (ite.hasNext())
		{
			if (countFlag != 1)
			{
				sb.append(" ");
			}
			U key = ite.next();
			V value = map.get(key);
			sb.append(key).append(":").append(value);
			++countFlag;
		}

		return sb.toString();
	}

	public void writeToFile(String filePath)
	{

		BufferedWriter writer = null;

		try
		{
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filePath), "UTF-8"));
			Iterator<T> ite = this.tripleTable.keySet().iterator();
			while (ite.hasNext())
			{
				T index = ite.next();
				Map<U, V> map = this.tripleTable.get(index);
				writer.append(index.toString());
				writer.append("\t");
				if (null != map)
				{
					String line = this.mapToString(map);
					writer.append(line);
				}
				writer.append("\n");
			}
			writer.flush();
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
