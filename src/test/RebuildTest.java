package test;

import java.util.Dictionary;

import matrix.TfIdf;
import process.DirtWordsDict;
import process.Spliter;
import build.ReBuilder;

public class RebuildTest
{
	public static void main(String[] args) throws Exception
	{
		ReBuilder.reBuild();
		
//		Spliter splite = new Spliter();
//		splite.spliteFile("d:\\tnb\\abs\\", "d:\\tnb\\words\\");
//		TfIdf ti = new TfIdf("d:\\tnb\\words");
//		ti.compute();
//		ti.writeToDatabase();
	}
}
