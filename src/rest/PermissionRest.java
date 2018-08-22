package rest;

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

	/*@Path("/addResponse")
	@POST
	@PermitAll
	public PermissionResponse addResponse(PermissionResponse response) {
		return ServiceFacade.getInstance().addResponse(response);
	}

	@Path("/getAllResponses")
	@POST
	@PermitAll
	public ArrayList<PermissionResponse> getAllResponses(long ID) {
		return ServiceFacade.getInstance().getAllResponses(ID);
	}
*/
	@Path("/deletePermission")
	@POST
	@RolesAllowed("admin")
	public void deletePermission(Permission permission) throws Exception {
		ServiceFacade.getInstance().deletePermission(permission);
	}
	@Path("/updatePermission")
	@POST
	@RolesAllowed("admin")
	public void updatePermission(Permission permission) throws Exception {
		ServiceFacade.getInstance().updatePermission(permission);
	}
	/*
	@Path("/closePermission")
	@POST
	@PermitAll
	public void closePermission(long ID) throws Exception {
		ServiceFacade.getInstance().closeTicket(ticketID);
	}
	*/
}
	