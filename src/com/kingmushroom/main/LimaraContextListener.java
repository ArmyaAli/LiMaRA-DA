package com.kingmushroom.main;
//Author: Ali Umar

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import com.dandannyg.main.data.DataSourceFactory;

/**
 * Application Lifecycle Listener implementation class LimaraContextListener
 *
 */
@WebListener
public class LimaraContextListener implements ServletContextListener {
	@Resource(name="StudentDS") // name=res-ref-name from glassfish-web.xml file
	private DataSource studentDS;
    /**
     * Default constructor. 
     */
    public LimaraContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        DataSourceFactory.setDataSource(studentDS);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    	DataSourceFactory.setDataSource(null);
    }
	
}
