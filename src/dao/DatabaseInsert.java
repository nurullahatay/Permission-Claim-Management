package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import bean.DatabaseProperties;
import dao.base.DatabaseHelper;
import dto.PersonelBilgisiDTO;

public class DatabaseInsert extends DatabaseHelper{
	public void init(Properties appProperties) {

		DatabaseProperties databaseProperties = new DatabaseProperties();
		databaseProperties.setUsername(appProperties.getProperty("dbuser"));
		databaseProperties.setPassword(appProperties.getProperty("dbpassword"));
		databaseProperties.setDatabaseConnectionURL(appProperties.getProperty("database"));
		databaseProperties.setDatabaseDriver(appProperties.getProperty("databaseDriver"));
		databaseProperties.setJndiName(appProperties.getProperty("jndiName"));
		databaseProperties.setDataSource(Boolean.parseBoolean(appProperties.getProperty("isDataSource")));

		super.init(databaseProperties);// propertyler kalıtım alınan class'a gönderildi

	}
	public void personelEkle(PersonelBilgisiDTO personelBilgisiDTO) throws Exception{
		Connection conn=getConnection();
		try {
			PreparedStatement preparedStatement = null;
			preparedStatement = conn.prepareStatement("insert into personelbilgisi(Ad, Soyad, TelefonNumarasi, Adres, Departman, IkinciYoneticiOnay, IseBaslangicTarihi) values(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, personelBilgisiDTO.getAd());
			preparedStatement.setString(2, personelBilgisiDTO.getSoyad());
			preparedStatement.setLong(3, personelBilgisiDTO.getTelefonnumarasi());
			preparedStatement.setString(4, personelBilgisiDTO.getAdres());
			preparedStatement.setString(5, personelBilgisiDTO.getDepartman());
			preparedStatement.setBoolean(6, personelBilgisiDTO.isIkinciyoneticionay());
			preparedStatement.setString(7,personelBilgisiDTO.getIsebaslangictarihi());
			preparedStatement.execute();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
