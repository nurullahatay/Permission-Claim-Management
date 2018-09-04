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

	@Path("/getPermission")
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
	@PermitAll
	public ArrayList<Permission> getPersonelApproval(long sicilNo) throws Exception {
		return ServiceFacade.getInstance().getPersonelApproval(sicilNo);
	}

	@Path("/confirmedPermissionFirstManager")
	@POST
	@RolesAllowed("FirstManager")
	public void confirmedPermissionFirstManager(Permission permission) throws Exception {
		ServiceFacade.getInstance().confirmedPermissionFirstManager(permission);
	}

	@Path("/deniedPermissionFirstManager")
	@POST
	@RolesAllowed("FirstManager")
	public void deniedPermissionFirstManager(Permission permission) throws Exception {
		ServiceFacade.getInstance().deniedPermissionFirstManager(permission);
	}

	@Path("/confirmedPermissionSecondManager")
	@POST
	@RolesAllowed("SecondManager")
	public void confirmedPermissionSecondManager(Permission permission) throws Exception {
		ServiceFacade.getInstance().confirmedPermissionSecondManager( permission);
	}

	@Path("/deniedPermissionSecondManager")
	@POST
	@RolesAllowed("SecondManager")
	public void deniedPermissionSecondManager(Permission permission) throws Exception {
		ServiceFacade.getInstance().deniedPermissionSecondManager( permission);
	}

	@Path("/confirmedPermissionHR")
	@POST
	@RolesAllowed("HR")
	public void confirmedPermissionHR(Permission permission) throws Exception {
		ServiceFacade.getInstance().confirmedPermissionHR( permission);
	}

	@Path("/deniedPermissionHR")
	@POST
	@RolesAllowed("HR")
	public void deniedPermissionHR(Permission permission) throws Exception {
		ServiceFacade.getInstance().deniedPermissionHR( permission);
	}

	@Path("/confirmedPermissionPersonel")
	@POST
	@PermitAll
	public void confirmedPermissionPersonel(Permission permission) throws Exception {
		ServiceFacade.getInstance().confirmedPermissionPersonel( permission);
	}

	@Path("/deniedPermissionPersonel")
	@POST
	@PermitAll
	public void deniedPermissionPersonel(Permission permission) throws Exception {
		ServiceFacade.getInstance().deniedPermissionPersonel( permission);
	}

	@Path("/searchPermission")
	@POST
	@PermitAll
	public ArrayList<Permission> searchPermission(Permission permission) throws Exception {
		return ServiceFacade.getInstance().searchPermission(permission);
	}

	@Path("/cancelPermission")
	@POST
	@PermitAll
	public void cancelPermission(long permission) throws Exception {
		ServiceFacade.getInstance().cancelPermission(permission);
	}
}