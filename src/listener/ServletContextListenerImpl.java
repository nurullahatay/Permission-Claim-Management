package listener;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import properties.PropertiesService;
import service.ServiceFacade;;

public class ServletContextListenerImpl implements ServletContextListener {
	final Logger logger = Logger.getLogger(ServletContextListenerImpl.class);

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.debug("ServletContextListenerImpl contextInitialized metodu çalýþmaya baþladý.");
		Properties appProperties = PropertiesService.getProperties();//propertyler dosyadan okundu
		try {
			ServiceFacade.getInstance().initialize(appProperties);
		} catch (Exception e) {
			logger.error("ServletContextListenerImpl contextInitialized metodu exeption = " + e);
		}
		logger.debug("ServletContextListenerImpl contextInitialized metodu çalýþmasý bitti.");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.debug("ServletContextListenerImpl contextDestroyed metodu çalýþmaya baþladý.");

	}

}