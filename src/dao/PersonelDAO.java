package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import bean.DatabaseProperties;
import dto.Personel;

public class PersonelDAO extends DatabaseHelper {
	Logger logger = Logger.getLogger(PersonelDAO.class);

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

		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {
			
			query.append("INSERT INTO personel(NAME, SURNAME, EMAIL, PASSWORD, DATEOFSTART, POSITION, SECONDMANEGERAPPROVAL,DEPARTMENT) ");
			query.append("VALUES (?,?,?,?,NOW(),?,?,?)");
			String queryString = query.toString();
			System.out.println(queryString);
			stmt = (PreparedStatement) conn.prepareStatement(queryString);

			stmt.setString(1, personel.getAd());
			stmt.setString(2, personel.getSoyad());
			stmt.setString(3, personel.getEmail());
			stmt.setString(4, personel.getPassword());
			stmt.setString(5, personel.getPozisyon());
			stmt.setBoolean(6, personel.isIkinciyoneticionay());
			stmt.setLong(7, personel.getDepartment());
			stmt.executeUpdate();
			conn.commit();
			addPersonelRoles(personel);
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(stmt);
			closeConnection(conn);
		}
	}

	private void addPersonelRoles(Personel personel) throws Exception {
		PreparedStatement pStmt = null;
		StringBuilder query = new StringBuilder();
		Connection conn = null;
		try {
			conn=getConnection();
			query.append("INSERT INTO personel_roles ");
			query.append("(EMAIL,ROLE) values (?,?)");

			pStmt = conn.prepareStatement(query.toString());

			List<String> personelRoles = personel.getPersonelRoles();
			for (String role : personelRoles) {
				pStmt.setString(1, personel.getEmail());
				pStmt.setString(2, role);
				pStmt.executeUpdate();
			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(pStmt);
			closeConnection(conn);
		}
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
				personel.setDepartment(rs.getLong(6));
				personel.setEmail(rs.getString(4));
				personel.setPassword(rs.getString(5));
				personel.setSoyad(rs.getString(3));
				personel.setIsebaslangictarihi(rs.getString(7));
				personel.setPozisyon(rs.getString(8));
				personel.setIkinciyoneticionay(rs.getBoolean(9));

			} else {
				System.out.println("olmadi");
			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error(e.getMessage());
			throw e;

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
			String query = "SELECT * FROM personel ";
			logger.debug("sql query created : " + query);
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				personel = new Personel();
				personel.setAd(rs.getString(2));
				personel.setSicilno(rs.getLong(1));
				personel.setDepartment(rs.getLong(6));
				personel.setEmail(rs.getString(4));
				personel.setPassword(rs.getString(5));
				personel.setSoyad(rs.getString(3));
				personel.setIsebaslangictarihi(rs.getString(7));
				personel.setPozisyon(rs.getString(8));
				personel.setIkinciyoneticionay(rs.getBoolean(9));
				personels.add(personel);
			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			throw e;
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
			stmt.setObject(6, personel.getDepartment());
			stmt.setString(7, null);
			stmt.setString(8, personel.getPozisyon());
			stmt.setBoolean(9, personel.isIkinciyoneticionay());
			stmt.setLong(10, personel.getSicilno());

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
		logger.debug("updatePersonel finished. ");
	}

	public void deletePersonel(long sicilno) throws Exception {

		
		 
		
		Connection con = null;
		PreparedStatement pstDepartment = null;
		StringBuilder queryDeleteDepartment = new StringBuilder();

		try {
			
			queryDeleteDepartment.append("DELETE FROM PERSONEL ");
			queryDeleteDepartment.append("WHERE SICILNO=?");
			String queryString = queryDeleteDepartment.toString();
			logger.debug("sql sorgusu: " + queryString);

			con = getConnection();
			pstDepartment = (PreparedStatement) con.prepareStatement(queryString);

	  
			pstDepartment.setLong(1, sicilno);
			pstDepartment.executeUpdate();
			 con.commit();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error:" + e.getMessage());
			throw e;

		} finally {
			closeConnection(con);
		}
		logger.debug("deletepersonel is finished");
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
				personel.setDepartment(rs.getLong("DEPARTMENT"));
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
			throw e;

		} finally {
			closeResultSet(rs);
			closePreparedStatement(pst);
			closeConnection(conn);
		}
		logger.debug("getPersonelDetailWithEmail finished. ");

		return personel;
	}
	
	
	
	
	
	
	
	
	
}