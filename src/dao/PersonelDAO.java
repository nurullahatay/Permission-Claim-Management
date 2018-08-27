package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import bean.DatabaseProperties;
import dto.Department;
import dto.Personel;

public class PersonelDAO extends DatabaseHelper {
	Logger logger = Logger.getLogger(RightOfPermissionDAO.class);

	DatabaseProperties databaseProperties = null;

	public void init(Properties appProperties) {

		DatabaseProperties databaseProperties = new DatabaseProperties();

		databaseProperties.setUsername(appProperties.getProperty("dbuser"));
		databaseProperties.setPassword(appProperties.getProperty("dbpassword"));
		databaseProperties.setDatabaseConnectionURL(appProperties.getProperty("database"));
		databaseProperties.setDatabaseDriver(appProperties.getProperty("databaseDriver"));
		databaseProperties.setJndiName(appProperties.getProperty("jndiName"));
		databaseProperties.setDataSource(Boolean.parseBoolean(appProperties.getProperty("isDataSource")));

		super.init(databaseProperties);

	}

	public void addPersonel(Personel personel) throws Exception {
		logger.debug("addPersonel is started");

		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {
			query.append("INSERT INTO personel ");
			query.append("VALUES (?,?,?,?,?,NOW() ,?,NOW() ,?,?) ");
			String queryString = query.toString();
			logger.debug("sql query created : " + queryString);

			stmt = (PreparedStatement) conn.prepareStatement(queryString);

			stmt.setLong(1, personel.getSicilno());
			stmt.setString(2, personel.getAd());
			stmt.setString(3, personel.getSoyad());
			stmt.setString(4, personel.getEmail());
			stmt.setString(5, personel.getPassword());
			stmt.setObject(6, personel.getDepartman().getDepartmentName());
			stmt.setString(7, personel.getPozisyon());
			stmt.setBoolean(8, personel.isIkinciyoneticionay());
			stmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error(e.getMessage());

			throw e;
		} finally {

			closePreparedStatement(stmt);
			closeConnection(conn);
		}
		logger.debug("addPersonel finished. ");
	}

	public Personel getPersonel(long sicilNo) throws Exception {
		logger.debug("getPersonel is started");

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = (Connection) getConnection();
		Personel personel = new Personel();
		String query = "SELECT * FROM  personel WHERE  SICILNO=?";
		logger.debug("sql query created : " + query);

		try {

			pst = (PreparedStatement) conn.prepareStatement(query);

			pst.setLong(1, sicilNo);
			rs = pst.executeQuery();

			if (rs.next()) {
				personel.setAd(rs.getString(2));
				personel.setSicilno(rs.getLong(1));
				Department departman = new Department();
				departman.setDepartmentName(rs.getString(7));
				personel.setDepartman(departman);
				personel.setEmail(rs.getString(4));
				personel.setPassword(rs.getString(5));
				personel.setSoyad(rs.getString(3));
				personel.setIsebaslangictarihi(rs.getString(6));
				personel.setPozisyon(rs.getString(9));
				personel.setIkinciyoneticionay(rs.getBoolean(10));

			} else {
				System.out.println("olmadi");
			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error(e.getMessage());
		} finally {
			closeResultSet(rs);
			closePreparedStatement(pst);
			closeConnection(conn);
		}
		logger.debug("getPersonel finished. company # is");

		return personel;

	}

	public ArrayList<Personel> getAllPersonel() throws Exception {
		logger.debug("getAllPersonel is started");

		Connection conn = null;

		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Personel personel;
		ArrayList<Personel> personels = new ArrayList<>();
		try {
			String query = "SELECT *   FROM  personel ";
			logger.debug("sql query created : " + query);
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				personel = new Personel();
				System.out.println(rs.getLong(1));

				personel.setAd(rs.getString(2));
				personel.setSicilno(rs.getLong(1));
				Department departman = new Department();
				departman.setDepartmentName(rs.getString(7));
				personel.setDepartman(departman);
				personel.setEmail(rs.getString(4));
				personel.setPassword(rs.getString(5));
				personel.setSoyad(rs.getString(3));
				personel.setIsebaslangictarihi(rs.getString(6));
				personel.setPozisyon(rs.getString(9));
				personel.setIkinciyoneticionay(rs.getBoolean(10));
				personels.add(personel);
			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error(e.getMessage());
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}
		logger.debug("getAllPersonel finished. company # is " + personels.size());
		return personels;
	}

	public void updatePersonel(Personel personel) throws Exception {
		logger.debug("updatePersonel is started");

		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {
			query.append("UPDATE personel   set ");
			query.append(
					"NAME = ? ,SURNAME = ?,EMAIL = ? PASSWORD=? ,ISEBASLANGICTARIHI=?,DEPARTMENT=?,DATEOFSTART=?,POSITION=? ,SECONDMANEGERAPPROVAL=? ");
			query.append(" WHERE   SICILNO = ?");

			String queryString = query.toString();
			logger.debug("sql query created : " + queryString);

			stmt = (PreparedStatement) conn.prepareStatement(queryString);

			stmt.setString(1, personel.getAd());
			stmt.setString(2, personel.getSoyad());
			stmt.setString(3, personel.getEmail());
			stmt.setString(4, personel.getPassword());
			stmt.setString(5, personel.getIsebaslangictarihi());
			stmt.setObject(6, personel.getDepartman());
			stmt.setString(7, null);
			stmt.setString(8, personel.getPozisyon());
			stmt.setBoolean(9, personel.isIkinciyoneticionay());
			stmt.setLong(10, personel.getSicilno());

			stmt.executeUpdate();

			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			logger.error(e.getMessage());
		} finally {

			closePreparedStatement(stmt);
			closeConnection(conn);
		}
		logger.debug("updatePersonel finished. ");
	}

	public void deletePersonel(Personel personel) throws Exception {
		logger.debug("deletePersonel is started");

		int temp = (int) personel.getSicilno();// ID isterse rollerden gelebilir.
		int sicilNo = (int) temp;

		// departman user ve ticket kontrolu
		Connection conn = null;
		PreparedStatement pstDepartment = null;
		StringBuilder queryDeleteDepartment = new StringBuilder();

		try {

			queryDeleteDepartment.append("DELETE FROM personel ");
			queryDeleteDepartment.append("WHERE SICILNO=?");
			String queryString = queryDeleteDepartment.toString();
			logger.debug("sql query created " + queryString);

			conn = getConnection();
			pstDepartment = (PreparedStatement) conn.prepareStatement(queryString);

			pstDepartment.setLong(1, sicilNo);
			pstDepartment.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("error:" + e.getMessage());
		} finally {

			closeConnection(conn);
		}
		logger.debug("deletePersonel is finished");
	}

	public Personel getPersonelDetailWithEmail(String email) throws Exception {
		logger.debug("getPersonelDetailWithEmail is started");
		PreparedStatement pst = null;
		Connection conn = getConnection();
		ResultSet rs = null;
		Personel personel = new Personel();
		String query = "SELECT * FROM  PERSONEL WHERE  EMAIL =?";

		try {

			pst = (PreparedStatement) conn.prepareStatement(query);
			pst.setString(1, email);
			rs = pst.executeQuery();

			if (rs.next()) {
				personel.setAd(rs.getString("NAME"));
				personel.setSicilno(rs.getLong("SICILNO"));
				Department departman = new Department();
				departman.setDepartmentName(rs.getString("DEPARTMENT"));
				personel.setDepartman(departman);
				personel.setEmail(rs.getString("EMAIL"));
				personel.setPassword(rs.getString("PASSWORD"));
				personel.setSoyad(rs.getString("SURNAME"));
				personel.setIsebaslangictarihi(rs.getString("DATEOFSTART"));
				personel.setPozisyon(rs.getString("POSITION"));
				personel.setIkinciyoneticionay(rs.getBoolean("SECONDMANEGERAPPROVAL"));
				System.out.println(rs.getString("NAME"));

			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("getPersonelDetailWithEmail error:" + e.getMessage());
		} finally {
			closeResultSet(rs);
			closePreparedStatement(pst);
			closeConnection(conn);
		}
		logger.debug("getPersonelDetailWithEmail finished. ");

		return personel;
	}
}
