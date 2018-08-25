package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;

import bean.DatabaseProperties;
import dto.Permission;

public class PermissionDAO extends DatabaseHelper {

	final Logger logger = Logger.getLogger(PermissionDAO.class);


	public void init(Properties appProperties) {
		logger.debug("PersonelDAO init metodu çalışmaya başladı.");
		
		DatabaseProperties databaseProperties = new DatabaseProperties();
		databaseProperties.setUsername(appProperties.getProperty("dbuser"));
		databaseProperties.setPassword(appProperties.getProperty("dbpassword"));
		databaseProperties.setDatabaseConnectionURL(appProperties.getProperty("database"));
		databaseProperties.setDatabaseDriver(appProperties.getProperty("databaseDriver"));
		databaseProperties.setJndiName(appProperties.getProperty("jndiName"));
		databaseProperties.setDataSource(Boolean.parseBoolean(appProperties.getProperty("isDataSource")));
		super.init(databaseProperties);
		logger.debug("PersonelDAO init metodu çalışması bitti.");
	}

		public void addPermission(Permission permission) throws Exception {
		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {
			// 1.NOW PERMISSIONCREATINGHISTORY
			// 2.NOW STARTINGDATE
			// 3.NOW DATEOFRETURN
			query.append("INSERT INTO permission  ");
			query.append("VALUES  (?,?,NOW(),NOW(),NOW(),?,?,?,?,?,0,0,0,0) ");
			String queryString = query.toString();
			System.out.println(queryString);
			stmt = (PreparedStatement) conn.prepareStatement(queryString);

			stmt.setLong(1, permission.getId());
			stmt.setLong(2, permission.getSicilNo());
			stmt.setInt(3, permission.getGun());
			stmt.setString(4, permission.getIzinNedeni());
			stmt.setString(5, permission.getAciklama());
			stmt.setString(6, permission.getTelefonNumarasi());
			stmt.setString(7, permission.getAdres());
			stmt.executeUpdate();

			logger.error("Liste Basarıyla Göndelirdi");

			System.out.println("2. islev Basarili Bir Sekilde Tamamlandi");

		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		} finally {
			if (stmt != null)
				stmt.close();

		}
	}

	public Permission getPermission(long id) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Permission permission = new Permission();
		String query = "SELECT  * FROM permission WHERE ID=?";

		try {
			Connection conn = (Connection) getConnection();

			pst = (PreparedStatement) conn.prepareStatement(query);

			pst.setLong(1, id);
			rs = pst.executeQuery();
			// dto'da form no var ama database yok ona bak istersen eklerim...
			if (rs.next()) {
				permission.setId(rs.getLong("ID"));
				permission.setSicilNo(rs.getLong("SICILNO"));
				permission.setAciklama(rs.getString("DESCRIPTION"));
				permission.setAdres(rs.getString("ADDRESS"));
				permission.setBaslangicTarihi(rs.getString("STARTINGDATE"));
				permission.setBirinciYoneticiOnayi(rs.getBoolean("FIRSTMANAGERAPPROVAL"));
				permission.setBitisTarihi(rs.getString("DATEOFRETURN"));
				permission.setDurum(rs.getBoolean("STATUS"));
				permission.setIkinciYoneticiOnayi(rs.getBoolean("FIRSTMANAGERAPPROVAL"));
				permission.setIkOnayi(rs.getBoolean("IKAPPROVAL"));
				permission.setTelefonNumarasi(rs.getString("PHONENUMBER"));
				permission.setIzinNedeni(rs.getString("PERMISSIONREASON"));
				permission.setGun(rs.getInt("DAY"));

			} else {
				System.out.println("olmadi");
			}
		} catch (Exception e) {
			logger.error("getDepartment error:" + e.getMessage());
		} finally {

		}

		return permission;

	}

	public ArrayList<Permission> getAllPermission() throws Exception {
		logger.debug("getAllcompanies is started");

		Connection con = null;
	 
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Permission permission;
		ArrayList<Permission> permissions = new ArrayList<>();
		try {
			String query = "SELECT * FROM permission";
			logger.debug("sql query created : " + query);
			con = getConnection();
			preparedStatement = (PreparedStatement) con.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				  permission = new Permission();
				  System.out.println(rs.getLong("ID"));
				permission.setId(rs.getLong("ID"));
				permission.setSicilNo(rs.getLong("SICILNO"));
				permission.setAciklama(rs.getString("DESCRIPTION"));
				permission.setAdres(rs.getString("ADDRESS"));
				permission.setBaslangicTarihi(rs.getString("STARTINGDATE"));
				permission.setBirinciYoneticiOnayi(rs.getBoolean("FIRSTMANAGERAPPROVAL"));
				permission.setBitisTarihi(rs.getString("DATEOFRETURN"));
				permission.setDurum(rs.getBoolean("STATUS"));
				permission.setIkinciYoneticiOnayi(rs.getBoolean("FIRSTMANAGERAPPROVAL"));
				permission.setIkOnayi(rs.getBoolean("IKAPPROVAL"));
				permission.setTelefonNumarasi(rs.getString("PHONENUMBER"));
				permission.setIzinNedeni(rs.getString("PERMISSIONREASON"));
				permission.setGun(rs.getInt("DAY"));
				permissions.add(permission);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
		 
			closeConnection(con);
		}
		logger.debug("getAllcompany finished. company # is " + permissions.size());
		return permissions;
	}

	public void updatePermission(Permission permission) throws Exception {
		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {
			query.append("UPDATE permission  SET ");
			query.append(
					"SICILNO = ? ,PERMISSIONCREATINGHISTORY = NOW()  ,STARTINGDATE = NOW()  ,DATEOFRETURN = NOW() ");
			query.append(" ,DAY = ?  ,PERMISSIONREASON = ? ,");
			query.append(" DESCRIPTION = ? ,PHONENUMBER = ?  ,ADDRESS = ?");
			query.append(
					",SECONDMANAGERAPPROVAL = ? ,FIRSTMANAGERAPPROVAL = ?  ,IKAPPROVAL = ?, STATUS = ?   WHERE ID = ? ");
			String queryString = query.toString();

			System.out.println(queryString);
			stmt = (PreparedStatement) conn.prepareStatement(queryString);

			stmt.setLong(1, permission.getSicilNo());
			stmt.setInt(2, permission.getGun());
			stmt.setString(3, permission.getIzinNedeni());

			stmt.setString(4, permission.getAciklama());
			stmt.setString(5, permission.getTelefonNumarasi());
			stmt.setString(6, permission.getAdres());
			stmt.setBoolean(7, permission.isIkinciYoneticiOnayi());
			stmt.setBoolean(8, permission.isBirinciYoneticiOnayi());
			stmt.setBoolean(9, permission.isIkOnayi());
			stmt.setBoolean(10, permission.isDurum());
			stmt.setLong(11, permission.getId());

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

	public void deletePermission(Permission permission) throws Exception {
		int temp = (int) permission.getId();// ID isterse rollerden gelebilir.
		int sicilNo = (int) temp;

		// departman user ve ticket kontrolu
		Connection con = null;
		PreparedStatement pstDepartment = null;
		StringBuilder queryDeleteDepartment = new StringBuilder();

		try {

			queryDeleteDepartment.append("DELETE FROM permission ");
			queryDeleteDepartment.append("WHERE ID=?");
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

	public void firstManagerApprove(Permission permission) throws Exception {
	/*
	 * ornek Servlet gonderimi
	 * 
	 Permission permission=new Permission();
	permission.setId(1);
	permission.setBirinciYoneticiOnayi(true);
			ServiceFacede.getInstance().  firstManagerApprove(permission);
	 * 
	 * 
	 * */
		int firstmanagerapproval;
		int temp = (int) permission.getId();
		Boolean temp2 = permission.isBirinciYoneticiOnayi();
		int id = (int) temp;
		if (temp2 == true) {

			firstmanagerapproval = 1;
		} else
			firstmanagerapproval = 0;
		Connection con = null;
		PreparedStatement pstDepartment = null;
		StringBuilder query = new StringBuilder();

		try {

			query.append("UPDATE permission  SET");
			query.append(" FIRSTMANAGERAPPROVAL = ? ");
			query.append("WHERE ID=?");
			String queryString = query.toString();
			System.out.println(queryString);
			logger.debug("sql sorgusu: " + queryString);

			con = getConnection();
			pstDepartment = (PreparedStatement) con.prepareStatement(queryString);

			pstDepartment.setInt(1, firstmanagerapproval);
			pstDepartment.setLong(2, id);
			pstDepartment.executeUpdate();

		} catch (Exception e) {
			logger.error("error:" + e.getMessage());
		} finally {

			closeConnection(con);
		}
	 
	}
	
	public void secondManagerApprove(Permission permission) throws Exception {
	/*
	 * ornek Servlet gonderimi
	 * 
	Permission permission=new Permission();
	permission.setId(1);
	permission.setIkinciYoneticiOnayi(true);
			ServiceFacede.getInstance().  secondManagerApprove(permission);
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	 * 
	 * */
		int secondmanagerapproval;
		int temp = (int) permission.getId();
		Boolean temp2 = permission.isIkinciYoneticiOnayi();
		int id = (int) temp;
		if (temp2 == true) {

			secondmanagerapproval = 1;
		} else
			secondmanagerapproval = 0;
		Connection con = null;
		PreparedStatement pstDepartment = null;
		StringBuilder query = new StringBuilder();

		try {

			query.append("UPDATE permission  SET");
			query.append(" SECONDMANAGERAPPROVAL = ? ");
			query.append("WHERE ID=?");
			String queryString = query.toString();
			System.out.println(queryString);
			logger.debug("sql sorgusu: " + queryString);

			con = getConnection();
			pstDepartment = (PreparedStatement) con.prepareStatement(queryString);

			pstDepartment.setInt(1, secondmanagerapproval);
			pstDepartment.setLong(2, id);
			pstDepartment.executeUpdate();

		} catch (Exception e) {
			logger.error("error:" + e.getMessage());
		} finally {

			closeConnection(con);
		}
	 
	}
	
	public void ikApprove(Permission permission) throws Exception {
		/*
		 * ornek Servlet gonderimi
		 * 
		try {
	Permission permission=new Permission();
	permission.setId(1);
	permission.setIkOnayi(true);
			ServiceFacede.getInstance().  secondManagerApprove(permission);
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 * 
		 * */
			int ikapproval;
			int temp = (int) permission.getId();
			Boolean temp2 = permission.isIkOnayi();
			int id = (int) temp;
			if (temp2 == true) {

				ikapproval = 1;
			} else
				ikapproval = 0;
			Connection con = null;
			PreparedStatement pstDepartment = null;
			StringBuilder query = new StringBuilder();

			try {

				query.append("UPDATE permission  SET");
				query.append(" IKAPPROVAL = ?,STATUS=? ");
				query.append("WHERE ID=?");
				String queryString = query.toString();
				System.out.println(queryString);
				logger.debug("sql sorgusu: " + queryString);

				con = getConnection();
				pstDepartment = (PreparedStatement) con.prepareStatement(queryString);

				pstDepartment.setInt(1, ikapproval);
				pstDepartment.setInt(2, ikapproval);
				pstDepartment.setLong(3, id);
				pstDepartment.executeUpdate();

			} catch (Exception e) {
				logger.error("error:" + e.getMessage());
			} finally {

				closeConnection(con);
			}
	}
	
	public List<Permission> getNewPermissionsForFirstManager() throws Exception {
		logger.debug("getAllcompanies is started");

		Connection con = null;
	 
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Permission permission;
		ArrayList<Permission> permissions = new ArrayList<>();
		try {
			String query = "SELECT  * FROM permission WHERE FIRSTMANAGERAPPROVAl  IS NULL";
			logger.debug("sql query created : " + query);
			con = getConnection();
			preparedStatement = (PreparedStatement) con.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				  permission = new Permission();
				  System.out.println(rs.getLong("ID"));
				permission.setId(rs.getLong("ID"));
				permission.setSicilNo(rs.getLong("SICILNO"));
				permission.setAciklama(rs.getString("DESCRIPTION"));
				permission.setAdres(rs.getString("ADDRESS"));
				permission.setBaslangicTarihi(rs.getString("STARTINGDATE"));
				permission.setBirinciYoneticiOnayi(rs.getBoolean("FIRSTMANAGERAPPROVAL"));
				permission.setBitisTarihi(rs.getString("DATEOFRETURN"));
				permission.setDurum(rs.getBoolean("STATUS"));
				permission.setIkinciYoneticiOnayi(rs.getBoolean("FIRSTMANAGERAPPROVAL"));
				permission.setIkOnayi(rs.getBoolean("IKAPPROVAL"));
				permission.setTelefonNumarasi(rs.getString("PHONENUMBER"));
				permission.setIzinNedeni(rs.getString("PERMISSIONREASON"));
				permission.setGun(rs.getInt("DAY"));
				permissions.add(permission);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
		 
			closeConnection(con);
		}
		logger.debug("getAllcompany finished. company # is " + permissions.size());
		return permissions;
 }

	public List<Permission> getNewPermissionsForSecondManager() throws Exception {
		logger.debug("getAllcompanies is started");

		Connection con = null;
	 
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Permission permission;
		ArrayList<Permission> permissions = new ArrayList<>();
		try {
			String query = "SELECT *   FROM permission WHERE FIRSTMANAGERAPPROVAL=1 AND   SECONDMANAGERAPPROVAL  IS NULL ";
			logger.debug("sql query created : " + query);
			con = getConnection();
			preparedStatement = (PreparedStatement) con.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				  permission = new Permission();
				  System.out.println(rs.getLong("ID"));
				permission.setId(rs.getLong("ID"));
				permission.setSicilNo(rs.getLong("SICILNO"));
				permission.setAciklama(rs.getString("DESCRIPTION"));
				permission.setAdres(rs.getString("ADDRESS"));
				permission.setBaslangicTarihi(rs.getString("STARTINGDATE"));
				permission.setBirinciYoneticiOnayi(rs.getBoolean("FIRSTMANAGERAPPROVAL"));
				permission.setBitisTarihi(rs.getString("DATEOFRETURN"));
				permission.setDurum(rs.getBoolean("STATUS"));
				permission.setIkinciYoneticiOnayi(rs.getBoolean("FIRSTMANAGERAPPROVAL"));
				permission.setIkOnayi(rs.getBoolean("IKAPPROVAL"));
				permission.setTelefonNumarasi(rs.getString("PHONENUMBER"));
				permission.setIzinNedeni(rs.getString("PERMISSIONREASON"));
				permission.setGun(rs.getInt("DAY"));
				permissions.add(permission);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
		 
			closeConnection(con);
		}
		logger.debug("getAllcompany finished. company # is " + permissions.size());
		return permissions;

	}
}
