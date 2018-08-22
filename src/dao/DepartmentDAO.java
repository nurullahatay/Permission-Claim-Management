package dao;

import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import dto.Department;

public class DepartmentDAO extends DatabaseHelper {
	final Logger logger = Logger.getLogger(DepartmentDAO.class);

	public void init(Properties appProperties) {
		logger.debug("DepartmentDAO init metodu çalışmaya başladı.");
		super.init(appProperties);
		logger.debug("DepartmentDAO init metodu çalışması bitti.");
	}

	public void addDepartment(Department department ) throws Exception {

	}

	public Department getDepartment(long id) throws Exception {
		return null;

	}
	
	public List<Department> getAllDepartment() throws Exception {
		return null;

	}
	
	public void updateDepartment(Department department) throws Exception {
	
	}
	
	public void deletePersonel(long ID) throws Exception {

	}

}
