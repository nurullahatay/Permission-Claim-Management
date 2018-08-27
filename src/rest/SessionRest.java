package rest;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

import dto.Personel;

@Path("/session")
@Produces(MediaType.APPLICATION_JSON)
public class SessionRest {

	final Logger logger = Logger.getLogger(SessionRest.class);

	@Path("/getAuthenticatedPersonel")
	@GET
	@PermitAll
	public Personel getAuthenticatedPersonel(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		Personel authenticatedPersonel = (Personel) session.getAttribute("LOGIN_USER");
		return authenticatedPersonel;
	}
}
