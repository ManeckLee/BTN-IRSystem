package update;

import java.io.File;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import build.ReBuilder;

public class ThreadTest extends Thread
{
	public void run()
	{
//		if (!this.isInterrupted())
//		{
			File file = new File("d:\\tnb\\bitdata\\");
			Set<String> fileList = new HashSet<String>();
			fileList.add("patent.txt");
			fileList.add("intelligence.txt");
			fileList.add("literature.txt");
			fileList.add("专利.txt");
			fileList.add("情报.txt");
			fileList.add("文献.txt");
			Watcher watcher = new Watcher(file);
			watcher.SetWatchedFileList(fileList);
			watcher.setBuilder(new ReBuilder());
			watcher.startWatch();

//		}
	}

}
