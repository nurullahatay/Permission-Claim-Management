package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.log4j.Logger;
import bean.DatabaseProperties;
import dao.DatabaseHelper;

import dto.Department;
import dto.Personel;

public class PersonelDAO extends DatabaseHelper {
	final Logger logger = Logger.getLogger(PersonelDAO.class);

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
			query.append("INSERT INTO personel ");
			query.append("VALUES (?,?,?,?,?,NOW() ,?,NOW() ,?,?) ");
			String queryString = query.toString();
			System.out.println(queryString);
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

		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		} finally {
			if (stmt != null)
				stmt.close();

		}
	}

	public Personel getPersonel(long sicilNo) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Personel personel = new Personel();
		String query = "SELECT * FROM  personel WHERE  SICILNO=?";

		try {
			Connection conn = (Connection) getConnection();

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

				System.out.println(rs.getString("NAME"));

			} else {
				System.out.println("olmadi");
			}
		} catch (Exception e) {
		System.out.println("e.getMessage()");
		} finally {

		}

		return personel;

	}

	public ArrayList<Personel> getAllPersonel() throws Exception {
		logger.debug("getAllcompanies is started");

		Connection con = null;
	 
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Personel personel;
		ArrayList<Personel> personels = new ArrayList<>();
		try {
			String query = "SELECT *   FROM  personel ";
			logger.debug("sql query created : " + query);
			con = getConnection();
			preparedStatement = (PreparedStatement) con.prepareStatement(query.toString());
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
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
		 
			closeConnection(con);
		}
		logger.debug("getAllcompany finished. company # is " + personels.size());
		return personels;
	}

	public void updatePersonel(Personel personel) throws Exception {
		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {
			query.append(
					"UPDATE personel   set ");
			query.append("NAME = ? ,SURNAME = ?,EMAIL = ? PASSWORD=? ,ISEBASLANGICTARIHI=?,DEPARTMENT=?,DATEOFSTART=?,POSITION=? ,SECONDMANEGERAPPROVAL=? ");
			query.append(" WHERE   SICILNO = ?");
			
			String queryString = query.toString();
			
			System.out.println(queryString);
			stmt = (PreparedStatement) conn.prepareStatement(queryString);

			 
			stmt.setString(1, personel.getAd());
			stmt.setString(2, personel.getSoyad());
			stmt.setString(3, personel.getEmail());
			stmt.setString(4, personel.getPassword());
			stmt.setString(5, personel.getIsebaslangictarihi());
			stmt.setObject(6, personel.getDepartman());
			stmt.setString(7,    null )  ;
			stmt.setString(8, personel.getPozisyon());
			stmt.setBoolean(9,  personel.isIkinciyoneticionay());
			stmt.setLong(10, personel.getSicilno());
 
 

			stmt.executeUpdate();

			logger.error("update Basarıyla Göndelirdi");

 

		} catch (Exception e) {
			logger.error("update islevinde sıkıntı oldu");

			throw e;
		} finally {
			if (stmt != null)
				stmt.close();

		}
	}

	public void deletePersonel(Personel personel) throws Exception {

		int temp =(int) personel.getSicilno();//ID isterse rollerden gelebilir.
		int sicilNo = (int)temp;
		 
				// departman user ve ticket kontrolu
				Connection con = null;
				PreparedStatement pstDepartment = null;
				StringBuilder queryDeleteDepartment = new StringBuilder();

				try {
					
					queryDeleteDepartment.append("DELETE FROM personel ");
					queryDeleteDepartment.append("WHERE SICILNO=?");
					String queryString = queryDeleteDepartment.toString();
					logger.debug("sql sorgusu: " + queryString);

					con = getConnection();
					pstDepartment = (PreparedStatement) con.prepareStatement(queryString);

			  
					pstDepartment.setLong(1, sicilNo);
					pstDepartment.executeUpdate();

				} catch (Exception e) {
					logger.error("error:" + e.getMessage());
				} finally {
					 
					closeConnection(con);
				}
				logger.debug("deleteDepartment is finished");
			}
	public Personel getPersonelDetailWithEmail(String email)  {
		System.out.println("1");
		PreparedStatement pst = null;
		System.out.println("2");
		ResultSet rs = null;
		Personel personel = new Personel();
		String query = "SELECT * FROM  PERSONEL WHERE  EMAIL =?";
 
		try {
			Connection conn =getConnection();
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

			} else {
				System.out.println("olmadi");
			}
		} catch (Exception e) {
			logger.error("getDepartment error:" + e.getMessage());
		} finally {

		}

		return personel;
	}
}
