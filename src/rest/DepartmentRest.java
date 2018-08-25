package rest;

import java.util.ArrayList;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import dto.Department;
import service.ServiceFacade;



@Path("department")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DepartmentRest {

	@Path("/getAllDepartments")
	@GET
	@PermitAll
	public ArrayList<Department> getAllDepartments() throws Exception {
		return (ArrayList<Department>) ServiceFacade.getInstance().getAllDepartment();
	}
	
	@POST
	@Path("/addDepartment")
	@RolesAllowed("admin")
	public void addDepartment(Department department) throws Exception {
		 ServiceFacade.getInstance().addDepartment(department);
	}
	
	@POST
	@Path("/getDepartmentDetails")
	@RolesAllowed("admin")
	public Department getDepartmentDetails(long ID) throws Exception {
		return ServiceFacade.getInstance().getDepartment(ID);
	}
	
	@POST
	@Path("/deleteDepartment")
	@RolesAllowed("admin")
	public void deleteDepartment(Department department) throws Exception {
		 ServiceFacade.getInstance().deleteDepartment(department);
	}

}
