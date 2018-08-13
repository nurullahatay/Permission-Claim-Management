package properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesService {

	public static Properties getProperties() {

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
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (final IOException e) {
				}
			}

		}
		return props;
	}

}
