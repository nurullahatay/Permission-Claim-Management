package service;

import java.util.Properties;

import bean.DatabaseProperties;
import dao.DatabaseInsert;
import dao.base.DatabaseHelper;


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
		
		System.out.println("Facade initializing");
	
		DatabaseInsert databaseInsert=new DatabaseInsert();
		databaseInsert.init(appProperties);
	}

	public void shutdown() {

	}
}
