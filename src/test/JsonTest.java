package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

public class JsonTest
{
	public static void main(String[] args)
	{
		Map<String, Integer> map1 = new HashMap<String, Integer>();
		map1.put("test", 1);
		map1.put("testw", 2);
		
		JSONArray ja = JSONArray.fromObject(map1);
		
		System.out.println(ja.toString());
		
		List<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("world");
		JSONArray jb = JSONArray.fromObject(list);
		
		System.out.println(jb.toString());
		
		JSONArray jc = JSONArray.fromObject(jb.toString());

		System.out.println(jc.toArray());
		
		for(Object o : jc.toArray())
		{
			System.out.println((String)o);
		}
	}
}
