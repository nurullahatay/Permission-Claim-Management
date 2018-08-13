package service;

import java.util.Properties;


public class ServiceFacede {

	private static ServiceFacede serviceFacede;

	private ServiceFacede() {
	}

	public static ServiceFacede getInstance() {
		if (serviceFacede == null) {
			serviceFacede = new ServiceFacede();
		}
		return serviceFacede;
	}

	public void initialize(Properties appProperties) throws Exception {
		

	}

	public void shutdown() {

	}
}
