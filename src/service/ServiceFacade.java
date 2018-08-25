package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import bean.DatabaseProperties;
import dao.base.DepartmentDAO;
import dao.base.PermissionDAO;
import dao.base.PersonelDAO;
import dao.base.RightOfPermissionDAO;
import dto.RightOfPermission;
import dto.Department;
import dto.Permission;
import dto.Personel;


public class ServiceFacede {
private static ServiceFacede serviceFacede;
	
	private DepartmentDAO departmentDAO = null;
	private PersonelDAO personelDAO = null;
	private PermissionDAO permissionDAO = null;
	private RightOfPermissionDAO rightOfPermissionDAO = null;

	private ServiceFacede() {
	}

	public static ServiceFacede getInstance() {
		if (serviceFacede == null) {
			serviceFacede = new ServiceFacede();
		}
		return serviceFacede;
	}

	public void initialize(Properties appProperties) throws Exception {
		departmentDAO = new DepartmentDAO();
		permissionDAO = new PermissionDAO();
		rightOfPermissionDAO = new RightOfPermissionDAO();
		personelDAO = new PersonelDAO();
		departmentDAO.init(appProperties);

		
		personelDAO.init(appProperties);
	
		permissionDAO.init(appProperties);
		
	
		rightOfPermissionDAO.init(appProperties);
	}

	public void shutdown() {

	}
	
	
	public void addDepartment(Department department ) throws Exception {
		departmentDAO.addDepartment(department);
	}

	public Department getDepartment(long id) throws Exception {
		return departmentDAO.getDepartment(id);
	}
	
 
	public void updateDepartment(Department department) throws Exception {
		departmentDAO.updateDepartment(department);
	}
	
	public ArrayList<Department>     getAllDepartment() throws Exception {
		return departmentDAO.getAllDepartment();
	}
 //////////////////////////////////////////////////////////////////////////77777
	
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
	
	
	
	
	/////////////////////////////////////////////////////////////////////////////////77
	public void addRightOfPermission(RightOfPermission rightOfPermission) throws Exception {
		rightOfPermissionDAO.addRightOfPermission(rightOfPermission);
	}

	public RightOfPermission getRightOfPermission(long sicilNo) throws Exception {
		return rightOfPermissionDAO.getRightOfPermission(sicilNo);
	}

 
	
	public void updateRightOfPermission(RightOfPermission rightOfPermission) throws Exception {
		rightOfPermissionDAO.updateRightOfPermission(rightOfPermission);
	}
	
	public void deleteRightOfPermission(RightOfPermission rightOfPermission) throws Exception {
		rightOfPermissionDAO.deleteRightOfPermission(rightOfPermission);
	}
	
	
	public ArrayList<RightOfPermission>     getAllRightOfPermission() throws Exception {
		return rightOfPermissionDAO.getAllRightOfPermission();
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////
	public void addPersonel(Personel personel) throws Exception {
		personelDAO.addPersonel(personel);
	}

	public Personel getPersonel(long sicilNo) throws Exception {
		return personelDAO.getPersonel(sicilNo);
	}
	
	public ArrayList<Personel>     getAllPersonel() throws Exception {
		return personelDAO.getAllPersonel();
	}
	 
	
	public void updatePersonel(Personel personel) throws Exception {
		personelDAO.updatePersonel(personel);
	}
	
	public void deletePersonel(Personel personel) throws Exception {
		personelDAO.deletePersonel(personel);
	}
	
	
	
	
	
	
	
	
	
	
}
