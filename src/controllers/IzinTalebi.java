package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DatabaseInsert;
import dto.IzinTalebiDTO;

public class IzinTalebi extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		IzinTalebiDTO izinTalebiDTO = new IzinTalebiDTO();
		izinTalebiDTO.setSicilno(Long.parseLong(req.getParameter("sicilno")));
		izinTalebiDTO.setIzinnedeni(req.getParameter("izinnedeni"));
		izinTalebiDTO.setAciklama(req.getParameter("aciklama"));
		izinTalebiDTO.setBaslangictarihi((req.getParameter("izinbaslangictarihi")));
		izinTalebiDTO.setDonustarihi((req.getParameter("izinbitistarihi")));
		DatabaseInsert databaseInsert = new DatabaseInsert();
		try {
			databaseInsert.izinTalep(izinTalebiDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = resp.getWriter();
		out.println("sdgsdgir.");

	}

}
