package update;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Listenner implements ServletContextListener
{
	private ThreadTest watcher;

	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{
		if (this.watcher != null && this.watcher.isInterrupted())
		{
			this.watcher.interrupt();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0)
	{
		if (this.watcher == null)
		{
			this.watcher = new ThreadTest();
			this.watcher.start();
		}
	}

}
