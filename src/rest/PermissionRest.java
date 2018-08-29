package rest;

import java.util.List;

import javax.annotation.security.PermitAll;
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

}