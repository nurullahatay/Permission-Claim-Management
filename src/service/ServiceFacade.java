package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import dao.DepartmentDAO;
import dao.PermissionDAO;
import dao.PersonelDAO;
import dao.RightOfPermissionDAO;
import dto.RightOfPermission;
import dto.Department;
import dto.Permission;
import dto.Personel;

public class ServiceFacade {
	private static ServiceFacade serviceFacade;
	final static Logger logger = Logger.getLogger(ServiceFacade.class);

	private DepartmentDAO departmentDAO = null;
	private PersonelDAO personelDAO = null;
	private PermissionDAO permissionDAO = null;
	private RightOfPermissionDAO rightOfPermissionDAO = null;

	private ServiceFacade() {
		logger.info("ServiceFacade nesnesi olu�turuldu.");
	}

	public static ServiceFacade getInstance() {
		logger.info("ServiceFacade getInstance  metodu �al��maya ba�lad�..");
		if (serviceFacade == null) {
			serviceFacade = new ServiceFacade();
		}
		return serviceFacade;
	}

	public void initialize(Properties appProperties) throws Exception {
		logger.info("ServiceFacade initialize  metodu �al��maya ba�lad�..");
		departmentDAO = new DepartmentDAO();
		permissionDAO = new PermissionDAO();
		rightOfPermissionDAO = new RightOfPermissionDAO();
		personelDAO = new PersonelDAO();

		departmentDAO.init(appProperties);
		personelDAO.init(appProperties);
		permissionDAO.init(appProperties);
		rightOfPermissionDAO.init(appProperties);
		logger.debug("ServiceFacade initialize metodu �al��mas� bitti.");
	}

	public void shutdown() {
		logger.info("ServiceFacade shutdown  metodu �al��maya ba�lad�..");
	}

	public void deleteDepartment(Department department) throws Exception {
		departmentDAO.deleteDepartment(department);
	}

	public void addDepartment(Department department) throws Exception {
		departmentDAO.addDepartment(department);
	}
	public void setDepartmentManagers(Department department) throws Exception {
		departmentDAO.setDepartmentManagers(department);
	}
	public Department getDepartment(long id) throws Exception {
		return departmentDAO.getDepartment(id);
	}

	public void updateDepartment(Department department) throws Exception {
		departmentDAO.updateDepartment(department);
	}

	public ArrayList<Department> getAllDepartment() throws Exception {
		return departmentDAO.getAllDepartment();
	}
	////////////////////////////////////////////////////////////////////////// 77777

	public void addPermission(Permission permission) throws Exception {
		permissionDAO.addPermission(permission);
	}

	public Permission getPermission(long id) throws Exception {
		return permissionDAO.getPermission(id);
	}

	public void updatePermission(Permission permission) throws Exception {
		permissionDAO.updatePermission(permission);
	}

	public void deletePermission(Permission permission) throws Exception {
		permissionDAO.deletePermission(permission);
	}

	
	public ArrayList<Permission> getAllPermission() throws Exception {
		return permissionDAO.getAllPermission();
	}
	public ArrayList<Permission> searchPermission(Permission permission) throws Exception {
		return permissionDAO.searchPermission(permission);
	}
	
	public ArrayList<Permission> getFromNowLaterAllPermission() throws Exception {
		return permissionDAO.getFromNowLaterAllPermission();
	}
	
	

	///////////////////////////////////////////////////////////////////////////////// 77
	public void addRightOfPermission(RightOfPermission rightOfPermission) throws Exception {
		rightOfPermissionDAO.addRightOfPermission(rightOfPermission);
	}

	public RightOfPermission getRightOfPermission(long sicilNo) throws Exception {
		return rightOfPermissionDAO.getRightOfPermission(sicilNo);
	}

	public void updateRightOfPermission(RightOfPermission rightOfPermission) throws Exception {
		rightOfPermissionDAO.updateRightOfPermission(rightOfPermission);
	}

	public void deleteRightOfPermission(long sicilNo) throws Exception {
		rightOfPermissionDAO.deleteRightOfPermission(sicilNo);
	}

	public ArrayList<RightOfPermission> getAllRightOfPermission() throws Exception {
		return rightOfPermissionDAO.getAllRightOfPermission();
	}
	

	public void decreasePermissionRight(long sicilNo, int day) throws Exception {
		rightOfPermissionDAO.decreasePermissionRight(sicilNo,day);
		
	}
	

	public void addOnPermissionRight(long sicilNo, int day) throws Exception {
		rightOfPermissionDAO.addOnPermissionRight(sicilNo,day);
		
	}


	/////////////////////////////////////////////////////////////////////////////////////////
	public void addPersonel(Personel personel) throws Exception {
		personelDAO.addPersonel(personel);
	}

	public Personel getPersonel(long sicilNo) throws Exception {
		return personelDAO.getPersonel(sicilNo);
	}

	public ArrayList<Personel> getAllPersonel() throws Exception {
		return personelDAO.getAllPersonel();
	}

	public void updatePersonel(Personel personel) throws Exception {
		personelDAO.updatePersonel(personel);
	}

	public void deletePersonel(long sicilNo) throws Exception {
		personelDAO.deletePersonel(sicilNo);
	}

	public Personel getPersonelDetailWithEmail(String email) throws Exception {
		return personelDAO.getPersonelDetailWithEmail(email);
	}

	public void deleteAllRightOfPermission() throws Exception {
		rightOfPermissionDAO.deleteAllRightOfPermission();

	}

	public ArrayList<Permission> getFirstManagerApproval(long id) throws Exception {

		return permissionDAO.getFirstManagerApproval(id);
	}
	public ArrayList<Permission> getSecondManagerApproval(long id) throws Exception {

		return permissionDAO.getSecondManagerApproval(id);
	}
	public ArrayList<Permission> getHRApproval() throws Exception {

		return permissionDAO.getHRApproval();
	}
	public ArrayList<Permission> getPersonelApproval(long sicilNo) throws Exception {

		return permissionDAO.getPersonelApproval(sicilNo);
	}
	public void confirmedPermissionFirstManager(Permission permission) throws Exception {
		permissionDAO.confirmedPermissionFirstManager( permission);
	}
	public void deniedPermissionFirstManager(Permission permission) throws Exception {
		permissionDAO.deniedPermissionFirstManager(permission);
	}
	public void confirmedPermissionSecondManager(Permission permission) throws Exception {
		permissionDAO.confirmedPermissionSecondManager( permission);
	}
	public void deniedPermissionSecondManager(Permission permission) throws Exception {
		permissionDAO.deniedPermissionSecondManager( permission);
	}
	public void confirmedPermissionHR(Permission permission) throws Exception {
		permissionDAO.confirmedPermissionHR( permission);
	}
	public void deniedPermissionHR(Permission permission) throws Exception {
		permissionDAO.deniedPermissionHR( permission);
	}
	public void confirmedPermissionPersonel(Permission permission) throws Exception {
		permissionDAO.confirmedPermissionPersonel( permission);
	}
	public void deniedPermissionPersonel(Permission permission) throws Exception {
		permissionDAO.deniedPermissionPersonel( permission);
	}

	public void cancelPermission(long permission ) throws Exception {
		permissionDAO.cancelPermission( permission );
		
	}

	public List<Permission> getMyOwnPermissions(long id) throws Exception {
		return permissionDAO.getMyOwnPermissions(id);
	}


}
