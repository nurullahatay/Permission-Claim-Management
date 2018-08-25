package service;

import java.util.List;
import java.util.Properties;
import dao.DepartmentDAO;
import dao.PermissionDAO;
import dao.PersonelDAO;
import dao.RightOfPermissionDAO;
import dto.Department;
import dto.Permission;
import dto.Personel;
import dto.RightOfPermission;

public class ServiceFacade {

	private static ServiceFacade serviceFacede;
	
	private DepartmentDAO departmentDAO = null;
	private PersonelDAO personelDAO = null;
	private PermissionDAO permissionDAO = null;
	private RightOfPermissionDAO rightOfPermissionDAO = null;

	private ServiceFacade() {
	}

	public static ServiceFacade getInstance() {
		if (serviceFacede == null) {
			serviceFacede = new ServiceFacade();
		}
		return serviceFacede;
	}

	public void initialize(Properties appProperties) throws Exception {
		departmentDAO = new DepartmentDAO();
		
		
		permissionDAO = new PermissionDAO();
		
		rightOfPermissionDAO = new RightOfPermissionDAO();
		personelDAO = new PersonelDAO();
		departmentDAO.init(appProperties);

		rightOfPermissionDAO.init(appProperties);
		permissionDAO.init(appProperties);
		personelDAO.init(appProperties);



	}

	public void shutdown() {

	}
	
	
	public void addDepartment(Department department ) throws Exception {
		departmentDAO.addDepartment(department);
	}

	public Department getDepartment(long id) throws Exception {
		return departmentDAO.getDepartment(id);
	}
	
	public List<Department> getAllDepartment() throws Exception {
		return departmentDAO.getAllDepartment();
	}
	
	public void updateDepartment(Department department) throws Exception {
		departmentDAO.updateDepartment(department);
	}
	
	public void deleteDepartment(Department department) throws Exception {
		departmentDAO.deleteDepartment(department);
	}
	
	
	public void addPermission(Permission permission) throws Exception {
		permissionDAO.addPermission(permission);
	}

	public Permission getPermission(long id) throws Exception {
		return permissionDAO.getPermission(id);
	}
	
	public List<Permission> getAllPermission() throws Exception {
		return permissionDAO.getAllPermission();
	}
	
	public void updatePermission(Permission permission) throws Exception {
		permissionDAO.updatePermission(permission);
	}
	
	public void deletePermission(Permission permission) throws Exception {
		permissionDAO.deletePermission(permission);
	}
	
	
	public void addPersonel(Personel personel) throws Exception {
		personelDAO.addPersonel(personel);
	}

	public Personel getPersonel(long sicilNo) throws Exception {
		return personelDAO.getPersonel(sicilNo);
	}
	
	public List<Personel> getAllPersonel() throws Exception {
		return personelDAO.getAllPersonel();
	}
	
	public void updatePersonel(Personel personel) throws Exception {
		personelDAO.updatePersonel(personel);
	}
	
	public void deletePersonel(Personel personel) throws Exception {
		personelDAO.deletePersonel(personel);
	}
	
	public Personel getPersonelDetailWithEmail(String email) {
		return personelDAO.getPersonelDetailWithEmail(email);
	}
	
	public void addRightOfPermission(RightOfPermission rightOfPermission) throws Exception {
		rightOfPermissionDAO.addRightOfPermission(rightOfPermission);
	}

	public RightOfPermission getRightOfPermission(long sicilNo) throws Exception {
		return rightOfPermissionDAO.getRightOfPermission(sicilNo);
	}
	
	public List<RightOfPermission> getAllRightOfPermission() throws Exception {
		return rightOfPermissionDAO.getAllRightOfPermission();
	}
	
	public void updateRightOfPermission(RightOfPermission rightOfPermission) throws Exception {
		rightOfPermissionDAO.updateRightOfPermission(rightOfPermission);
	}
	
	public void deleteRightOfPermission(RightOfPermission rightOfPermission) throws Exception {
		rightOfPermissionDAO.deleteRightOfPermission(rightOfPermission);
	}

	
}
