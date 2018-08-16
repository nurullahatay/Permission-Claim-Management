package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DatabaseInsert;
import dto.PersonelBilgisiDTO;

public class Test extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PersonelBilgisiDTO personelBilgisiDTO = new PersonelBilgisiDTO();
		personelBilgisiDTO.setAd(req.getParameter("ad"));
		personelBilgisiDTO.setSoyad(req.getParameter("soyad"));
		personelBilgisiDTO.setTelefonnumarasi(Integer.parseInt(req.getParameter("telno")));
		personelBilgisiDTO.setAdres(req.getParameter("adres"));
		personelBilgisiDTO.setDepartman(req.getParameter("departman"));
		personelBilgisiDTO.setIkinciyoneticionay(Boolean.parseBoolean(req.getParameter("iyonay")));
		personelBilgisiDTO.setIsebaslangictarihi((req.getParameter("isebaslamatarihi")));
		
		DatabaseInsert databaseInsert = new DatabaseInsert();
		try {
			databaseInsert.personelEkle(personelBilgisiDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = resp.getWriter();
		out.println("sdgsdgir.");

	}

}
