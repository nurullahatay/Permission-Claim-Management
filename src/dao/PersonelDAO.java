package dao;

import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import dao.DatabaseHelper;
import dto.Personel;

public class PersonelDAO extends DatabaseHelper {
	final Logger logger = Logger.getLogger(PersonelDAO.class);

	public void init(Properties appProperties) {
		logger.debug("PersonelDAO init metodu çalışmaya başladı.");
		super.init(appProperties);
		logger.debug("PersonelDAO init metodu çalışması bitti.");
	}

	public void addPersonel(Personel personel) throws Exception {

	}

	public Personel getPersonel(long sicilNo) throws Exception {
		return null;

	}
	
	public List<Personel> getAllPersonel() throws Exception {
		return null;

	}
	
	public void updatePersonel(Personel personel) throws Exception {
	
	}
	
	public void deletePersonel(Personel personel) throws Exception {

	}

}
