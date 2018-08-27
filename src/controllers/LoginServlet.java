package controllers;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import service.ServiceFacade;
import dto.Personel;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String email = request.getUserPrincipal().getName();
		Personel authenticatedPersonel = null;
		try {
			authenticatedPersonel = ServiceFacade.getInstance().getPersonelDetailWithEmail(email);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		HttpSession session = request.getSession();
		session.setAttribute("LOGIN_USER", authenticatedPersonel);
		session.setMaxInactiveInterval(36000); // 60dk - 60*60*10

		System.out.println(authenticatedPersonel.getEmail());
		response.sendRedirect("/Permission-Claim-Management/index.html");


	}

}
