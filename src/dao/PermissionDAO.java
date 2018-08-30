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
	Logger logger = Logger.getLogger(PermissionDAO.class);

	public void init(Properties appProperties) {
		logger.debug("PermissionDAO init metodu çalışmaya başladı.");
		DatabaseProperties databaseProperties = new DatabaseProperties();
		databaseProperties.setUsername(appProperties.getProperty("dbuser"));
		databaseProperties.setPassword(appProperties.getProperty("dbpassword"));
		databaseProperties.setDatabaseConnectionURL(appProperties.getProperty("database"));
		databaseProperties.setDatabaseDriver(appProperties.getProperty("databaseDriver"));
		databaseProperties.setJndiName(appProperties.getProperty("jndiName"));
		databaseProperties.setDataSource(Boolean.parseBoolean(appProperties.getProperty("isDataSource")));
		super.init(databaseProperties);
		logger.debug("PermissionDAO init metodu çalışması bitti.");
	}

	public void addPermission(Permission permission) throws Exception {
		logger.debug("PermissionDAO addPermission metodu çalışmaya başladı.");
		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {
			query.append(
					"INSERT INTO permission(SICILNO, PERMISSIONCREATINGHISTORY, STARTINGDATE, DATEOFRETURN, DAY, PERMISSIONREASON, DESCRIPTION, PHONENUMBER, ADDRESS, SECONDMANAGERAPPROVAL, FIRSTMANAGERAPPROVAL, IKAPPROVAL, STATUS, FORMFILLER) ");
			query.append("VALUES(?,NOW(),?,?,?,?,?,?,?,0,0,0,0,?) ");
			String queryString = query.toString();
			logger.trace(query.toString());
			stmt = (PreparedStatement) conn.prepareStatement(queryString);
			stmt.setLong(1, permission.getSicilNo());
			stmt.setString(2, permission.getBaslangicTarihi());
			stmt.setString(3, permission.getBitisTarihi());
			stmt.setInt(4, permission.getGun());
			stmt.setString(5, permission.getIzinNedeni());
			stmt.setString(6, permission.getAciklama());
			stmt.setString(7, permission.getTelefonNumarasi());
			stmt.setString(8, permission.getAdres());
			stmt.setLong(9, permission.getFormFiller());
			stmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO addPermission metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(stmt);
			closeConnection(conn);
			logger.debug("PermissionDAO addPermission metodu çalışması bitti.");
		}
	}

	public Permission getPermission(long id) throws Exception {
		logger.debug("PermissionDAO getPermission metodu çalışmaya başladı.");
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Permission permission = new Permission();
		String query = "SELECT  * FROM permission WHERE ID=?";
		logger.trace(query.toString());
		try {
			conn = (Connection) getConnection();
			pst = (PreparedStatement) conn.prepareStatement(query);
			pst.setLong(1, id);
			rs = pst.executeQuery();
			logger.trace(query.toString());
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
				permission.setFormFiller(rs.getLong("FORMFILLER"));
				conn.commit();
			}
		} catch (Exception e) {
			logger.error("PermissionDAO getPermission metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(pst);
			closeConnection(conn);
			logger.debug("PermissionDAO getPermission metodu çalışması bitti.");
		}
		return permission;
	}

	public ArrayList<Permission> getAllPermission() throws Exception {
		logger.debug("PermissionDAO getAllPermission metodu çalışmaya başladı.");
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = "SELECT  * FROM permission  ";
		ArrayList<Permission> permissions = new ArrayList<>();
		Permission permission;
		logger.trace(query.toString());
		Connection conn = (Connection) getConnection();
		try {
			pst = (PreparedStatement) conn.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				permission = new Permission();
				permission.setId(rs.getLong("ID"));
				permission.setFormTarihi(rs.getString("PERMISSIONCREATINGHISTORY"));
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
				permission.setFormFiller(rs.getLong("FORMFILLER"));
				permissions.add(permission);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO getAllPermission metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(pst);
			closeConnection(conn);
			logger.debug("PermissionDAO getAllPermission metodu çalışması bitti.");
		}
		return permissions;
	}

	public void updatePermission(Permission permission) throws Exception {
		logger.debug("PermissionDAO updatePermission metodu çalışmaya başladı.");
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
			logger.trace(query.toString());
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
			logger.error("PermissionDAO updatePermission metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(stmt);
			closeConnection(conn);
			logger.debug("PermissionDAO updatePermission metodu çalışması bitti.");
		}
	}

	public void deletePermission(Permission permission) throws Exception {
		logger.debug("PermissionDAO deletePermission metodu çalışmaya başladı.");
		int temp = (int) permission.getId();
		int sicilNo = (int) temp;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		StringBuilder queryDeleteDepartment = new StringBuilder();
		try {
			queryDeleteDepartment.append("DELETE FROM permission ");
			queryDeleteDepartment.append("WHERE ID=?");
			logger.trace(queryDeleteDepartment.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(queryDeleteDepartment.toString());
			preparedStatement.setLong(1, sicilNo);
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO deletePermission metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("PermissionDAO deletePermission metodu çalışması bitti.");
		}
	}

	public void firstManagerApprove(Permission permission) throws Exception {
		logger.debug("PermissionDAO firstManagerApprove metodu çalışmaya başladı.");
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
			logger.trace(queryString.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(queryString);
			preparedStatement.setInt(1, firstmanagerapproval);
			preparedStatement.setLong(2, id);
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO firstManagerApprove metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("PermissionDAO firstManagerApprove metodu çalışması bitti.");
		}
	}

	public void secondManagerApprove(Permission permission) throws Exception {
		logger.debug("PermissionDAO secondManagerApprove metodu çalışmaya başladı.");
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
			logger.trace(queryString.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(queryString);
			preparedStatement.setInt(1, secondmanagerapproval);
			preparedStatement.setLong(2, id);
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO secondManagerApprove metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("PermissionDAO firstManagerApprove metodu çalışması bitti.");
		}
	}

	
	public void ikApprove(Permission permission) throws Exception {
		logger.debug("PermissionDAO ikApprove metodu çalışmaya başladı.");
		/*
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
			logger.trace(queryString.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(queryString);
			preparedStatement.setInt(1, ikapproval);
			preparedStatement.setInt(2, ikapproval);
			preparedStatement.setLong(3, id);
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO ikApprove metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("PermissionDAO ikApprove metodu çalışması bitti.");
		}
	}

	public List<Permission> getNewPermissionsForFirstManager() throws Exception {
		logger.debug("PermissionDAO getNewPermissionsForFirstManager metodu çalışmaya başladı.");
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Permission permission;
		ArrayList<Permission> permissions = new ArrayList<>();
		try {
			String query = "SELECT  * FROM permission WHERE FIRSTMANAGERAPPROVAl  IS NULL";
			logger.trace(query.toString());
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
				permission.setFormFiller(rs.getLong("FORMFILLER"));
				permissions.add(permission);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO getNewPermissionsForFirstManager metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("PermissionDAO getNewPermissionsForFirstManager metodu çalışması bitti.");
		}
		return permissions;
	}

	public List<Permission> getNewPermissionsForSecondManager() throws Exception {
		logger.debug("PermissionDAO getNewPermissionsForSecondManager metodu çalışmaya başladı.");
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
			logger.trace(query.toString());
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
				permission.setFormFiller(rs.getLong("FORMFILLER"));
				permissions.add(permission);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO getNewPermissionsForSecondManager metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("PermissionDAO getNewPermissionsForSecondManager metodu çalışması bitti.");
		}
		return permissions;
	}

	public List<Permission> getNewPermissionsForIK() throws Exception {
		logger.debug("PermissionDAO getNewPermissionsForIK metodu çalışmaya başladı.");
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Permission permission;
		ArrayList<Permission> permissions = new ArrayList<>();
		try {
			String query = "SELECT *   FROM permission WHERE FIRSTMANAGERAPPROVAL=1 AND   SECONDMANAGERAPPROVAL =1 OR SECONDMANAGERAPPROVAL=NULL  ";
			logger.trace(query.toString());
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
				permission.setFormFiller(rs.getLong("FORMFILLER"));
				permissions.add(permission);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO getNewPermissionsForIK metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("PermissionDAO getNewPermissionsForIK metodu çalışması bitti.");
		}
		return permissions;
	}

	public List<Permission> getPermissionClaimInfo() throws Exception {
		logger.debug("PermissionDAO getPermissionClaimInfo metodu çalışmaya başladı.");
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Permission permission;
		ArrayList<Permission> permissions = new ArrayList<>();
		try {
			String query = "SELECT *   FROM permission WHERE IKAPPROVAL=1 ";
			logger.trace(query.toString());
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
				permission.setFormFiller(rs.getLong("FORMFILLER"));
				permissions.add(permission);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO getPermissionClaimInfo metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("PermissionDAO getPermissionClaimInfo metodu çalışması bitti.");
		}
		return permissions;
	}

	public List<Permission> getAllPreviousPermissions() throws Exception {
		logger.debug("PermissionDAO getAllPreviousPermissions metodu çalışmaya başladı.");
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Permission permission;
		ArrayList<Permission> permissions = new ArrayList<>();
		try {
			String query = "SELECT *   FROM permission WHERE   SICILNO=12  AND ( STATUS=1 OR STATUS=0) ";
			logger.trace(query.toString());
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
				permission.setFormFiller(rs.getLong("FORMFILLER"));
				permissions.add(permission);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO getAllPreviousPermissions metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("PermissionDAO getAllPreviousPermissions metodu çalışması bitti.");
		}
		return permissions;
	}
}