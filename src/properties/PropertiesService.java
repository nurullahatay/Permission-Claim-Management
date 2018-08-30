package properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

public class PropertiesService {
	final static Logger logger = Logger.getLogger(PropertiesService.class);

	public static  Properties getProperties() {
		logger.debug("PropertiesService getProperties metodu çalýþmaya baþladý.");
		Properties props = new Properties();
		InputStream input = null;
		try {
			String filename = "application.properties";
			input = PropertiesService.class.getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
			}
			props.load(input);
		} catch (Exception ex) {
			logger.error("PropertiesService getProperties metodu exeption = " + ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (final IOException e) {
					logger.error("PropertiesService getProperties metodu exeption = " + e);
				}
			}
			logger.debug("PropertiesService getProperties metodu çalýþmasý bitti.");
		}
		return props;
	}
}
