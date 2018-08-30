package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger(LogoutServlet.class);

	public void init() {
		logger.debug("LogoutServlet init metodu çalýþmaya baþladý.");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("LogoutServlet doGet metodu çalýþmaya baþladý.");
		request.getSession().invalidate();  
		response.sendRedirect(request.getContextPath());
		logger.debug("LogoutServlet doGet metodu çalýþmasý bitti.");		
	}

}
