package dto;

public class Department {
	private long id;
	private String departmentName;
	private long departmentFirstManager;
	private long departmentSecondManager;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public long getDepartmentFirstManager() {
		return departmentFirstManager;
	}
	public void setDepartmentFirstManager(long departmentFirstManager) {
		this.departmentFirstManager = departmentFirstManager;
	}
	public long getDepartmentSecondManager() {
		return departmentSecondManager;
	}
	public void setDepartmentSecondManager(long departmentSecondManager) {
		this.departmentSecondManager = departmentSecondManager;
	}

	
}
