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
			fileList.add("ר��.txt");
			fileList.add("�鱨.txt");
			fileList.add("����.txt");
			Watcher watcher = new Watcher(file);
			watcher.SetWatchedFileList(fileList);
			watcher.setBuilder(new ReBuilder());
			watcher.startWatch();

//		}
	}

}
