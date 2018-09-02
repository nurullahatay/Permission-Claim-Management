package rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dto.Permission;
import service.ServiceFacade;

@Path("/permission")
@PermitAll
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PermissionRest {

	@Path("/addPermission")
	@POST
	@PermitAll
	public void addPermission(Permission permission) throws Exception {
		ServiceFacade.getInstance().addPermission(permission);
	}

	@Path("/getAllPermission")
	@GET
	@PermitAll
	public List<Permission> getAllPermission() throws Exception {
		return ServiceFacade.getInstance().getAllPermission();
	}

	@Path("/getPermissionDetails")
	@POST
	@PermitAll
	public Permission getPermission(long id) throws Exception {
		return ServiceFacade.getInstance().getPermission(id);
	}

	@Path("/deletePermission")
	@POST
	@PermitAll
	public void deletePermission(Permission permission) throws Exception {
		ServiceFacade.getInstance().deletePermission(permission);
	}

	@Path("/updatePermission")
	@POST
	@PermitAll
	public void updatePermission(Permission permission) throws Exception {
		ServiceFacade.getInstance().updatePermission(permission);
	}

	@Path("/getFirstManagerApproval")
	@POST
	@RolesAllowed("FirstManager")
	public ArrayList<Permission> getFirstManagerApproval(long id) throws Exception {
		return ServiceFacade.getInstance().getFirstManagerApproval(id);
	}

	@Path("/getSecondManagerApproval")
	@POST
	@RolesAllowed("SecondManager")
	public ArrayList<Permission> getSecondManagerApproval(long id) throws Exception {
		return ServiceFacade.getInstance().getSecondManagerApproval(id);
	}

	@Path("/getHRApproval")
	@POST
	@RolesAllowed("HR")
	public ArrayList<Permission> getHRApproval() throws Exception {
		return ServiceFacade.getInstance().getHRApproval();
	}

	@Path("/getPersonelApproval")
	@POST
	@RolesAllowed("personel")
	public ArrayList<Permission> getPersonelApproval(long sicilNo) throws Exception {
		return ServiceFacade.getInstance().getPersonelApproval(sicilNo);
	}

	@Path("/confirmedPermissionFirstManager")
	@POST
	@RolesAllowed("FirstManager")
	public void confirmedPermissionFirstManager(long id) throws Exception {
		ServiceFacade.getInstance().confirmedPermissionFirstManager(id);
	}

	@Path("/deniedPermissionFirstManager")
	@POST
	@RolesAllowed("FirstManager")
	public void deniedPermissionFirstManager(long id) throws Exception {
		ServiceFacade.getInstance().deniedPermissionFirstManager(id);
	}

	@Path("/confirmedPermissionSecondManager")
	@POST
	@RolesAllowed("SecondManager")
	public void confirmedPermissionSecondManager(long id) throws Exception {
		ServiceFacade.getInstance().confirmedPermissionSecondManager(id);
	}

	@Path("/deniedPermissionSecondManager")
	@POST
	@RolesAllowed("SecondManager")
	public void deniedPermissionSecondManager(long id) throws Exception {
		ServiceFacade.getInstance().deniedPermissionSecondManager(id);
	}

	@Path("/confirmedPermissionHR")
	@POST
	@RolesAllowed("HR")
	public void confirmedPermissionHR(long id) throws Exception {
		ServiceFacade.getInstance().confirmedPermissionHR(id);
	}

	@Path("/deniedPermissionHR")
	@POST
	@RolesAllowed("HR")
	public void deniedPermissionHR(long id) throws Exception {
		ServiceFacade.getInstance().deniedPermissionHR(id);
	}

	@Path("/confirmedPermissionPersonel")
	@POST
	@RolesAllowed("personel")
	public void confirmedPermissionPersonel(long id) throws Exception {
		ServiceFacade.getInstance().confirmedPermissionPersonel(id);
	}

	@Path("/deniedPermissionPersonel")
	@POST
	@RolesAllowed("personel")
	public void deniedPermissionPersonel(long id) throws Exception {
		ServiceFacade.getInstance().deniedPermissionPersonel(id);
	}
}