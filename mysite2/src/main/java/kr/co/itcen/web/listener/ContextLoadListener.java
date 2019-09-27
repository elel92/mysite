package kr.co.itcen.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoadListener implements ServletContextListener {

    public ContextLoadListener() {
    	
    	
    	System.out.println("Mysite2 Application Starts...");
    }

    public void contextDestroyed(ServletContextEvent arg0)  {
    	
    }

    public void contextInitialized(ServletContextEvent ServletContextListener)  {
    	String contextLocation = ServletContextListener.getServletContext().getInitParameter("contextConfigLocation");
    }
	
}
