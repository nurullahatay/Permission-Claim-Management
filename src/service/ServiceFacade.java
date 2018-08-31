package service;

import java.util.ArrayList;
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

	public void firstManagerApprove(Permission permission) throws Exception {
		permissionDAO.firstManagerApprove(permission);
	}

	public void secondManagerApprove(Permission permission) throws Exception {
		permissionDAO.secondManagerApprove(permission);
	}

	public void ikApprove(Permission permission) throws Exception {
		permissionDAO.ikApprove(permission);
	}

	public void getNewPermissionsForFirstManager() throws Exception {
		permissionDAO.getNewPermissionsForFirstManager();
	}

	public void getNewPermissionsForSecondManager() throws Exception {
		permissionDAO.getNewPermissionsForFirstManager();
	}

	public ArrayList<Permission> getAllPermission() throws Exception {
		return permissionDAO.getAllPermission();
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
	public void confirmedPermissionFirstManager(long id) throws Exception {
		permissionDAO.confirmedPermissionFirstManager(id);
	}
	public void deniedPermissionFirstManager(long id) throws Exception {
		permissionDAO.deniedPermissionFirstManager(id);
	}
}
