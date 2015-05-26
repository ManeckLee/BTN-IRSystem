package update;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import build.Builder;

public class Watcher
{
	private File dir; // 要监视的文件夹

	private Builder builder;

	private Set<String> watchedFileList; // 关注文件列表


	public Watcher(File dir)
	{
		this.watchedFileList = new HashSet<String>();
		this.dir = dir;
	}

	

	public void SetWatchedFileList(Set<String> fileList)
	{
		this.watchedFileList = fileList;
	}

	public void setBuilder(Builder builder)
	{
		this.builder = builder;
	}

	private void watch()
	{

		try
		{
			WatchService watchService = FileSystems.getDefault()
					.newWatchService();
			Paths.get(this.dir.getAbsolutePath()).register(watchService,
					StandardWatchEventKinds.ENTRY_MODIFY);
			Calendar ca = Calendar.getInstance();
			int now;
			while (true)
			{
				now = ca.get(Calendar.HOUR_OF_DAY);
				WatchKey key = null;
				key = watchService.take();

				for (WatchEvent<?> event : key.pollEvents())
				{
					if (now >= 2 && now < 3)
					{
						String eventText = event.context().toString();
						System.out.println(eventText);
						// System.out.println(this.watchedFileList);
						if (this.watchedFileList.contains(eventText))
						{
							this.builder.doBuild();
						}
					}
//					else
//					{
//						break;
//					}
				}
				if (!key.reset())
				{
					break;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void startWatch()
	{
		System.out.println("start watch");
		this.watch();
	}

	

}
