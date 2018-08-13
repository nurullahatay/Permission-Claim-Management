package listener;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import properties.PropertiesService;
import service.ServiceFacede;

public class ServletContextListenerImpl implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		Properties appProperties = PropertiesService.getProperties();
		try {
			ServiceFacede.getInstance().initialize(appProperties);
		} catch (Exception e) {
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

}