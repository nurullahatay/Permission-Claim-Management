package dao;

import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import dto.Permission;

public class PermissionDAO extends DatabaseHelper {

	final Logger logger = Logger.getLogger(PermissionDAO.class);

	public void init(Properties appProperties) {
		logger.debug("ClaimForPermissionDAO init metodu çalışmaya başladı.");
		super.init(appProperties);
		logger.debug("ClaimForPermissionDAO init metodu çalışması bitti.");
	}
	
	public void addPermission(Permission permission) throws Exception {

	}

	public Permission getPermission(long id) throws Exception {
		return null;

	}
	
	public List<Permission> getAllPermission() throws Exception {
		return null;

	}
	
	public void updatePermission(Permission permission) throws Exception {
	
	}
	
	public void deletePermission(Permission permission) throws Exception {

	}
	
	

	
	
}
