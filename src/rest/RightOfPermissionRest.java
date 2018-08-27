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

import dto.RightOfPermission;
import service.ServiceFacade;

@Path("/right")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RightOfPermissionRest {

	@Path("/addRight")
	@POST
	@RolesAllowed("admin")
	public void addRightOfPermission(RightOfPermission rightOfPermission) throws Exception {
		ServiceFacade.getInstance().addRightOfPermission(rightOfPermission);
	}

	@Path("/getRightDetails")
	@POST
	@PermitAll
	public RightOfPermission getRightOfPermission(long sicilNo) throws Exception {
		return	ServiceFacade.getInstance().getRightOfPermission(sicilNo);
	}
	
	
	@Path("/getAllRight")
	@GET
	@RolesAllowed("HR")
	public List<RightOfPermission> getAllRightOfPermission() throws Exception {
		return 	ServiceFacade.getInstance().getAllRightOfPermission();

	}
	@Path("/updateRight")
	@POST
	@RolesAllowed("admin")
	public void updateRightOfPermission(RightOfPermission rightOfPermission) throws Exception {
		ServiceFacade.getInstance().updateRightOfPermission(rightOfPermission);

	}
	
	@Path("/deleteRight")
	@POST
	@RolesAllowed("HR")
	public void deleteRightOfPermission(long sicilNo) throws Exception {
		ServiceFacade.getInstance().deleteRightOfPermission(sicilNo);
	}
	
	@Path("/deleteAllRight")
	@GET
	@RolesAllowed("HR")
	public void deleteRightOfPermission() throws Exception {
		ServiceFacade.getInstance().deleteAllRightOfPermission();
	}
	
	
}
