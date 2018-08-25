package controllers;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;


import service.ServiceFacade;
import dto.Personel;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger(LoginServlet.class);

	public void init() {
		logger.debug("login servlet initialized");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.debug("login is started");
		
		String email = request.getUserPrincipal().getName();
		logger.debug("login email:"+email);
		Personel authenticatedPersonel = null;
		try {
			authenticatedPersonel = ServiceFacade.getInstance().getPersonelDetailWithEmail(email);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		HttpSession session = request.getSession();
		logger.debug("session ID:"+session.getId());
		session.setAttribute("LOGIN_USER", authenticatedPersonel);
		session.setMaxInactiveInterval(36000); // 60dk - 60*60*10

		System.out.println(authenticatedPersonel.getEmail());
		response.sendRedirect("/Permission-Claim-Management/index.html");
		logger.debug("login completed. userEmail:" + authenticatedPersonel.getEmail());


	}

}
