package dao;

import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import dto.RightOfPermission;;

public class RightOfPermissionDAO extends DatabaseHelper {
	Logger logger = Logger.getLogger(RightOfPermissionDAO.class);

	public void init(Properties appProperties) {
		logger.debug("RightOfPermissionDAO init metodu çalışmaya başladı.");
		super.init(appProperties);
		logger.debug("RightOfPermissionDAO init metodu çalışması bitti.");
	}
	
	public void addRightOfPermission(RightOfPermission rightOfPermission) throws Exception {

	}

	public RightOfPermission getRightOfPermission(long sicilNo) throws Exception {
		return null;

	}
	
	public List<RightOfPermission> getAllRightOfPermission() throws Exception {
		return null;

	}
	
	public void updateRightOfPermission(RightOfPermission rightOfPermission) throws Exception {
	
	}
	
	public void deleteRightOfPermission(RightOfPermission rightOfPermission) throws Exception {

	}
	
	
}
