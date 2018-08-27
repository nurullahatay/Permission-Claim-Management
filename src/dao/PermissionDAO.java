package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.mysql.jdbc.PreparedStatement;

import bean.DatabaseProperties;
import dto.Permission;

public class PermissionDAO extends DatabaseHelper {
	Logger logger = Logger.getLogger(RightOfPermissionDAO.class);

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

	public void addPermission(Permission permission) throws Exception {
		logger.debug("addPermission is started");
		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {

			query.append("INSERT INTO permission  ");
			query.append("VALUES  (?,?,NOW(),NOW(),NOW(),?,?,?,?,?,0,0,0,0) ");
			String queryString = query.toString();
			logger.debug("sql query created : " + queryString);
			stmt = (PreparedStatement) conn.prepareStatement(queryString);

			stmt.setLong(1, permission.getId());
			stmt.setLong(2, permission.getSicilNo());
			stmt.setInt(3, permission.getGun());
			stmt.setString(4, permission.getIzinNedeni());
			stmt.setString(5, permission.getAciklama());
			stmt.setString(6, permission.getTelefonNumarasi());
			stmt.setString(7, permission.getAdres());
			stmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			logger.error("addPermission error" + e.getMessage());
			conn.rollback();
			throw e;
		} finally {

			closePreparedStatement(stmt);
			closeConnection(conn);

		}
		logger.debug("addPermission is finished");
	}

	public Permission getPermission(long id) throws Exception {
		logger.debug("getPermission is started");
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Permission permission = new Permission();
		String query = "SELECT  * FROM permission WHERE ID=?";
		logger.debug("sql query created : " + query);

		try {
			conn = (Connection) getConnection();

			pst = (PreparedStatement) conn.prepareStatement(query);

			pst.setLong(1, id);
			rs = pst.executeQuery();

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
				conn.commit();
			}
		} catch (Exception e) {
			conn.rollback();
			logger.error("getPermission error:" + e.getMessage());
		} finally {
			closeResultSet(rs);
			closePreparedStatement(pst);
			closeConnection(conn);
		}
		logger.debug("getPermission finished ");

		return permission;

	}

	public ArrayList<Permission> getAllPermission() throws Exception {
		logger.debug("getAllcompanies is started");

		Connection conn = null;

		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Permission permission;
		ArrayList<Permission> permissions = new ArrayList<>();
		try {
			String query = "SELECT * FROM permission";
			logger.debug("sql query created : " + query);
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
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
				conn.commit();
			}
		} catch (Exception e) {
			conn.rollback();
			logger.error(e.getMessage());
		} finally {

			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}
		logger.debug("getAllcompany finished. company # is " + permissions.size());
		return permissions;
	}

	public void updatePermission(Permission permission) throws Exception {
		logger.debug("updatePermission is started");

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
			logger.debug("sql query created : " + queryString);

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
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("update islevinde sýkýntý oldu");

		} finally {

			closePreparedStatement(stmt);
			closeConnection(conn);

		}
		logger.debug("updatePermission finished. company  ");

	}

	public void deletePermission(Permission permission) throws Exception {
		logger.debug("deletePermission is started");
		int temp = (int) permission.getId();
		int sicilNo = (int) temp;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		StringBuilder queryDeleteDepartment = new StringBuilder();

		try {

			queryDeleteDepartment.append("DELETE FROM permission ");
			queryDeleteDepartment.append("WHERE ID=?");
			String queryString = queryDeleteDepartment.toString();

			logger.debug("sql query created : " + queryString);

			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(queryString);

			preparedStatement.setLong(1, sicilNo);
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("error:" + e.getMessage());
		} finally {

			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}
		logger.debug("deletePermission is finished");
	}

	public void firstManagerApprove(Permission permission) throws Exception {
		logger.debug("firstManagerApprove is started");

		/*
		 * ornek Servlet gonderimi
		 * 
		 * Permission permission=new Permission(); permission.setId(1);
		 * permission.setBirinciYoneticiOnayi(true); ServiceFacede.getInstance().
		 * firstManagerApprove(permission);
		 * 
		 * 
		 */
		int firstmanagerapproval;
		int temp = (int) permission.getId();
		Boolean temp2 = permission.isBirinciYoneticiOnayi();
		int id = (int) temp;
		if (temp2 == true) {

			firstmanagerapproval = 1;
		} else
			firstmanagerapproval = 0;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		StringBuilder query = new StringBuilder();

		try {

			query.append("UPDATE permission  SET");
			query.append(" FIRSTMANAGERAPPROVAL = ? ");
			query.append("WHERE ID=?");
			String queryString = query.toString();
			System.out.println(queryString);
			logger.debug("sql query created: " + queryString);

			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(queryString);

			preparedStatement.setInt(1, firstmanagerapproval);
			preparedStatement.setLong(2, id);
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("error:" + e.getMessage());
		} finally {

			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}
		logger.debug("firstManagerApprove finished. ");

	}

	public void secondManagerApprove(Permission permission) throws Exception {
		logger.debug("secondManagerApprove is started");
		int secondmanagerapproval;
		/*
		 * ornek Servlet gonderimi
		 * 
		 * Permission permission=new Permission(); permission.setId(1);
		 * permission.setIkinciYoneticiOnayi(true); ServiceFacede.getInstance().
		 * secondManagerApprove(permission); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace();
		 * 
		 */

		int temp = (int) permission.getId();
		Boolean temp2 = permission.isIkinciYoneticiOnayi();
		int id = (int) temp;
		if (temp2 == true) {

			secondmanagerapproval = 1;
		} else
			secondmanagerapproval = 0;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		StringBuilder query = new StringBuilder();

		try {

			query.append("UPDATE permission  SET");
			query.append(" SECONDMANAGERAPPROVAL = ? ");
			query.append("WHERE ID=?");
			String queryString = query.toString();
			System.out.println(queryString);
			logger.debug("sql query created : " + queryString);

			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(queryString);

			preparedStatement.setInt(1, secondmanagerapproval);
			preparedStatement.setLong(2, id);
			preparedStatement.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			logger.error("error:" + e.getMessage());
		} finally {

			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}

		logger.debug("secondManagerApprove finished  ");

	}

	public void ikApprove(Permission permission) throws Exception {
		logger.debug("ikApprove is started"); /*
												 * ornek Servlet gonderimi
												 * 
												 * try { Permission permission=new Permission(); permission.setId(1);
												 * permission.setIkOnayi(true); ServiceFacede.getInstance().
												 * secondManagerApprove(permission); } catch (Exception e) { // TODO
												 * Auto-generated catch block e.printStackTrace(); }
												 * 
												 */
		int ikapproval;
		int temp = (int) permission.getId();
		Boolean temp2 = permission.isIkOnayi();
		int id = (int) temp;
		if (temp2 == true) {

			ikapproval = 1;
		} else
			ikapproval = 0;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		StringBuilder query = new StringBuilder();

		try {

			query.append("UPDATE permission  SET");
			query.append(" IKAPPROVAL = ?,STATUS=? ");
			query.append("WHERE ID=?");
			String queryString = query.toString();

			logger.debug("sql query created: " + queryString);

			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(queryString);

			preparedStatement.setInt(1, ikapproval);
			preparedStatement.setInt(2, ikapproval);
			preparedStatement.setLong(3, id);
			preparedStatement.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			logger.error("error:" + e.getMessage());
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}
		logger.debug("ikApprove finished. company # is ");
	}

	public List<Permission> getNewPermissionsForFirstManager() throws Exception {
		logger.debug("getNewPermissionsForFirstManager is started");

		Connection conn = null;

		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Permission permission;
		ArrayList<Permission> permissions = new ArrayList<>();
		try {
			String query = "SELECT  * FROM permission WHERE FIRSTMANAGERAPPROVAl  IS NULL";
			logger.debug("sql query created : " + query);
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
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
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			logger.error(e.getMessage());
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}
		logger.debug("getNewPermissionsForFirstManager finished. company # is " + permissions.size());
		return permissions;
	}

	public List<Permission> getNewPermissionsForSecondManager() throws Exception {
		logger.debug("getNewPermissionsForSecondManager is started");

		Connection conn = null;

		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Permission permission;
		StringBuilder query = new StringBuilder();
		ArrayList<Permission> permissions = new ArrayList<>();
		try {
			query.append("SELECT * FROM permission ");
			query.append("INNER JOIN personel ON permission.SICILNO = ");
			query.append("personel.SICILNO WHERE permission.FIRSTMANAGERAPPROVAL=1 ");
			query.append("AND personel.SECONDMANEGERAPPROVAL=1 ");
			String queryString = query.toString();
			logger.debug("sql query created : " + queryString);
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
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
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error(e.getMessage());
		} finally {

			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}
		logger.debug("getNewPermissionsForSecondManager finished. company # is " + permissions.size());
		return permissions;
	}

	public List<Permission> getNewPermissionsForIK() throws Exception {
		logger.debug("getNewPermissionsForIK is started");

		Connection conn = null;

		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Permission permission;
		ArrayList<Permission> permissions = new ArrayList<>();
		try {
			String query = "SELECT *   FROM permission WHERE FIRSTMANAGERAPPROVAL=1 AND   SECONDMANAGERAPPROVAL =1 OR SECONDMANAGERAPPROVAL=NULL  ";
			logger.debug("sql query created : " + query);
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
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
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			logger.error(e.getMessage());
		} finally {

			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}
		logger.debug("getNewPermissionsForIK finished. company # is " + permissions.size());
		return permissions;
	}

	public List<Permission> getPermissionClaimInfo() throws Exception {
		logger.debug("getPermissionClaimInfo is started");

		Connection conn = null;

		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Permission permission;
		ArrayList<Permission> permissions = new ArrayList<>();
		try {
			String query = "SELECT *   FROM permission WHERE IKAPPROVAL=1 ";
			logger.debug("sql query created : " + query);
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
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
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			logger.error(e.getMessage());
		} finally {

			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}
		logger.debug("getPermissionClaimInfo finished. company # is " + permissions.size());
		return permissions;
	}

	public List<Permission> GetAllPreviousPermissions() throws Exception {
		logger.debug("GetAllPreviousPermissions is started");

		Connection conn = null;

		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Permission permission;
		ArrayList<Permission> permissions = new ArrayList<>();
		try {
			String query = "SELECT *   FROM permission WHERE   SICILNO=12  AND ( STATUS=1 OR STATUS=0) ";
			logger.debug("sql query created : " + query);
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
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

			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error(e.getMessage());
		} finally {

			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}
		logger.debug("GetAllPreviousPermissions finished. company # is " + permissions.size());
		return permissions;
	}
}
