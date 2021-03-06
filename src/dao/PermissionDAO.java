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
import dto.RightOfPermission;
import service.ServiceFacade;

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
			
			RightOfPermission hakedilengun=ServiceFacade.getInstance().getRightOfPermission(permission.getSicilNo());
			if(hakedilengun.getDayCountOfDeserved()>=permission.getGun()) {
				
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
			stmt.executeUpdate();conn.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
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
				permission.setBirinciYoneticiOnayi(rs.getString("FIRSTMANAGERAPPROVAL"));
				permission.setBitisTarihi(rs.getString("DATEOFRETURN"));
				permission.setDurum(rs.getString("STATUS"));
				permission.setIkinciYoneticiOnayi(rs.getString("FIRSTMANAGERAPPROVAL"));
				permission.setIkOnayi(rs.getString("IKAPPROVAL"));
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

	public ArrayList<Permission> searchPermission(Permission permission2) throws Exception {
		logger.debug("PermissionDAO searchPermission metodu çalışmaya başladı.");
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Permission> permissions = new ArrayList<>();
		Permission permission;
		Connection conn = (Connection) getConnection();
		try {
			if (permission2.getSicilNo() != 0) {
				String query = "select * from permission where(STARTINGDATE BETWEEN ? AND ?) and SICILNO= ?";
				logger.trace(query.toString());
				pst = (PreparedStatement) conn.prepareStatement(query);
				pst.setString(1, permission2.getBaslangicTarihi());
				pst.setString(2, permission2.getBitisTarihi());
				pst.setLong(3, permission2.getSicilNo());
				rs = pst.executeQuery();
			} else {
				String query = "select * from permission where(STARTINGDATE BETWEEN ? AND ?)";
				logger.trace(query.toString());
				pst = (PreparedStatement) conn.prepareStatement(query);
				pst.setString(1, permission2.getBaslangicTarihi());
				pst.setString(2, permission2.getBitisTarihi());
				rs = pst.executeQuery();
			}
			while (rs.next()) {
				permission = new Permission();
				permission.setId(rs.getLong("ID"));
				permission.setFormTarihi(rs.getString("PERMISSIONCREATINGHISTORY"));
				permission.setSicilNo(rs.getLong("SICILNO"));
				permission.setAciklama(rs.getString("DESCRIPTION"));
				permission.setAdres(rs.getString("ADDRESS"));
				permission.setBaslangicTarihi(rs.getString("STARTINGDATE"));
				permission.setBirinciYoneticiOnayi(rs.getString("FIRSTMANAGERAPPROVAL"));
				permission.setBitisTarihi(rs.getString("DATEOFRETURN"));
				permission.setDurum(rs.getString("STATUS"));
				permission.setIkinciYoneticiOnayi(rs.getString("FIRSTMANAGERAPPROVAL"));
				permission.setIkOnayi(rs.getString("IKAPPROVAL"));
				permission.setTelefonNumarasi(rs.getString("PHONENUMBER"));
				permission.setIzinNedeni(rs.getString("PERMISSIONREASON"));
				permission.setGun(rs.getInt("DAY"));
				permission.setFormFiller(rs.getLong("FORMFILLER"));
				permissions.add(permission);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO searchPermission metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(pst);
			closeConnection(conn);
			logger.debug("PermissionDAO searchPermission metodu çalışması bitti.");
		}
		return permissions;
	}

	
	public ArrayList<Permission> getFromNowLaterAllPermission() throws Exception {
		logger.debug("PermissionDAO getFromNowLaterAllPermission metodu çalışmaya başladı.");
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = "SELECT * FROM permission WHERE STARTINGDATE >= CURDATE() and STATUS='Onaylandı';";
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
				permission.setBirinciYoneticiOnayi(rs.getString("FIRSTMANAGERAPPROVAL"));
				permission.setBitisTarihi(rs.getString("DATEOFRETURN"));
				permission.setDurum(rs.getString("STATUS"));
				permission.setIkinciYoneticiOnayi(rs.getString("FIRSTMANAGERAPPROVAL"));
				permission.setIkOnayi(rs.getString("IKAPPROVAL"));
				permission.setTelefonNumarasi(rs.getString("PHONENUMBER"));
				permission.setIzinNedeni(rs.getString("PERMISSIONREASON"));
				permission.setGun(rs.getInt("DAY"));
				permission.setFormFiller(rs.getLong("FORMFILLER"));
				permissions.add(permission);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO getFromNowLaterAllPermission metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(pst);
			closeConnection(conn);
			logger.debug("PermissionDAO getFromNowLaterAllPermission metodu çalışması bitti.");
		}
		return permissions;
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
				permission.setBirinciYoneticiOnayi(rs.getString("FIRSTMANAGERAPPROVAL"));
				permission.setBitisTarihi(rs.getString("DATEOFRETURN"));
				permission.setDurum(rs.getString("STATUS"));
				permission.setIkinciYoneticiOnayi(rs.getString("FIRSTMANAGERAPPROVAL"));
				permission.setIkOnayi(rs.getString("IKAPPROVAL"));
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
			query.append("SICILNO = ? ,PERMISSIONCREATINGHISTORY = NOW()  ,STARTINGDATE = NOW()  ,DATEOFRETURN = NOW() ");
			query.append(" ,DAY = ?  ,PERMISSIONREASON = ? ,");
			query.append(" DESCRIPTION = ? ,PHONENUMBER = ?  ,ADDRESS = ?");
			query.append(",SECONDMANAGERAPPROVAL = ? ,FIRSTMANAGERAPPROVAL = ?  ,IKAPPROVAL = ?, STATUS = ?   WHERE ID = ? ");
			String queryString = query.toString();
			logger.trace(query.toString());
			stmt = (PreparedStatement) conn.prepareStatement(queryString);
			stmt.setLong(1, permission.getSicilNo());
			stmt.setInt(2, permission.getGun());
			stmt.setString(3, permission.getIzinNedeni());
			stmt.setString(4, permission.getAciklama());
			stmt.setString(5, permission.getTelefonNumarasi());
			stmt.setString(6, permission.getAdres());
			stmt.setString(7, permission.getIkinciYoneticiOnayi());
			stmt.setString(8, permission.getBirinciYoneticiOnayi());
			stmt.setString(9, permission.getIkOnayi());
			stmt.setString(10, permission.getDurum());
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
				permission.setBirinciYoneticiOnayi(rs.getString("FIRSTMANAGERAPPROVAL"));
				permission.setBitisTarihi(rs.getString("DATEOFRETURN"));
				permission.setDurum(rs.getString("STATUS"));
				permission.setIkinciYoneticiOnayi(rs.getString("FIRSTMANAGERAPPROVAL"));
				permission.setIkOnayi(rs.getString("IKAPPROVAL"));
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
				permission.setBirinciYoneticiOnayi(rs.getString("FIRSTMANAGERAPPROVAL"));
				permission.setBitisTarihi(rs.getString("DATEOFRETURN"));
				permission.setDurum(rs.getString("STATUS"));
				permission.setIkinciYoneticiOnayi(rs.getString("FIRSTMANAGERAPPROVAL"));
				permission.setIkOnayi(rs.getString("IKAPPROVAL"));
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

	public ArrayList<Permission> getFirstManagerApproval(long id) throws Exception {
		logger.debug("PermissionDAO getFirstManagerApproval metodu çalışmaya başladı.");
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Permission permission;
		ArrayList<Permission> permissions = new ArrayList<>();
		try {
			String query = "SELECT  * FROM permission b INNER JOIN (SELECT SICILNO FROM personel WHERE DEPARTMENT=?) a where a.SICILNO=b.SICILNO and FIRSTMANAGERAPPROVAL='0'";
			logger.trace(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			preparedStatement.setLong(1, id);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				permission = new Permission();
				permission.setId(rs.getLong("ID"));
				permission.setSicilNo(rs.getLong("SICILNO"));
				permission.setAciklama(rs.getString("DESCRIPTION"));
				permission.setAdres(rs.getString("ADDRESS"));
				permission.setFormTarihi(rs.getString("PERMISSIONCREATINGHISTORY"));
				permission.setBaslangicTarihi(rs.getString("STARTINGDATE"));
				permission.setBirinciYoneticiOnayi(rs.getString("FIRSTMANAGERAPPROVAL"));
				permission.setBitisTarihi(rs.getString("DATEOFRETURN"));
				permission.setDurum(rs.getString("STATUS"));
				permission.setIkinciYoneticiOnayi(rs.getString("FIRSTMANAGERAPPROVAL"));
				permission.setIkOnayi(rs.getString("IKAPPROVAL"));
				permission.setTelefonNumarasi(rs.getString("PHONENUMBER"));
				permission.setIzinNedeni(rs.getString("PERMISSIONREASON"));
				permission.setGun(rs.getInt("DAY"));
				permission.setFormFiller(rs.getLong("FORMFILLER"));
				permissions.add(permission);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO getFirstManagerApproval metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("PermissionDAO getFirstManagerApproval metodu çalışması bitti.");
		}
		return permissions;
	}

	public ArrayList<Permission> getSecondManagerApproval(long id) throws Exception {
		logger.debug("PermissionDAO getSecondManagerApproval metodu çalışmaya başladı.");
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Permission permission;
		ArrayList<Permission> permissions = new ArrayList<>();
		try {
			String query = "SELECT  * FROM permission b INNER JOIN (SELECT SICILNO,SECONDMANEGERAPPROVAL FROM personel WHERE DEPARTMENT=?) a where a.SICILNO=b.SICILNO and a.SECONDMANEGERAPPROVAL=true and b.FIRSTMANAGERAPPROVAL='Onaylandı' and b.SECONDMANAGERAPPROVAL='0'";
			logger.trace(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			preparedStatement.setLong(1, id);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				permission = new Permission();
				permission.setId(rs.getLong("ID"));
				permission.setSicilNo(rs.getLong("SICILNO"));
				permission.setAciklama(rs.getString("DESCRIPTION"));
				permission.setAdres(rs.getString("ADDRESS"));
				permission.setFormTarihi(rs.getString("PERMISSIONCREATINGHISTORY"));
				permission.setBaslangicTarihi(rs.getString("STARTINGDATE"));
				permission.setBirinciYoneticiOnayi(rs.getString("FIRSTMANAGERAPPROVAL"));
				permission.setBitisTarihi(rs.getString("DATEOFRETURN"));
				permission.setDurum(rs.getString("STATUS"));
				permission.setIkinciYoneticiOnayi(rs.getString("FIRSTMANAGERAPPROVAL"));
				permission.setIkOnayi(rs.getString("IKAPPROVAL"));
				permission.setTelefonNumarasi(rs.getString("PHONENUMBER"));
				permission.setIzinNedeni(rs.getString("PERMISSIONREASON"));
				permission.setGun(rs.getInt("DAY"));
				permission.setFormFiller(rs.getLong("FORMFILLER"));
				permissions.add(permission);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO getFirstManagerApproval metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("PermissionDAO getFirstManagerApproval metodu çalışması bitti.");
		}
		return permissions;
	}

	public ArrayList<Permission> getHRApproval() throws Exception {
		logger.debug("PermissionDAO getHRApproval metodu çalışmaya başladı.");
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Permission permission;
		ArrayList<Permission> permissions = new ArrayList<>();
		try {
			String query = "SELECT  * FROM permission b INNER JOIN personel a where (a.SICILNO=b.SICILNO and b.IKAPPROVAL='0') and ((a.SECONDMANEGERAPPROVAL=false and b.FIRSTMANAGERAPPROVAL='Onaylandı') or b.SECONDMANAGERAPPROVAL='Onaylandı') ";
			logger.trace(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				permission = new Permission();
				permission.setId(rs.getLong("ID"));
				permission.setSicilNo(rs.getLong("SICILNO"));
				permission.setAciklama(rs.getString("DESCRIPTION"));
				permission.setAdres(rs.getString("ADDRESS"));
				permission.setFormTarihi(rs.getString("PERMISSIONCREATINGHISTORY"));
				permission.setBaslangicTarihi(rs.getString("STARTINGDATE"));
				permission.setBirinciYoneticiOnayi(rs.getString("FIRSTMANAGERAPPROVAL"));
				permission.setBitisTarihi(rs.getString("DATEOFRETURN"));
				permission.setDurum(rs.getString("STATUS"));
				permission.setIkinciYoneticiOnayi(rs.getString("FIRSTMANAGERAPPROVAL"));
				permission.setIkOnayi(rs.getString("IKAPPROVAL"));
				permission.setTelefonNumarasi(rs.getString("PHONENUMBER"));
				permission.setIzinNedeni(rs.getString("PERMISSIONREASON"));
				permission.setGun(rs.getInt("DAY"));
				permission.setFormFiller(rs.getLong("FORMFILLER"));
				permissions.add(permission);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO getHRApproval metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("PermissionDAO getHRApproval metodu çalışması bitti.");
		}
		return permissions;
	}

	public ArrayList<Permission> getPersonelApproval(long sicilNo) throws Exception {
		logger.debug("PermissionDAO getPersonelApproval metodu çalışmaya başladı.");
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Permission permission;
		ArrayList<Permission> permissions = new ArrayList<>();
		try {
			String query = "select * From permission where IKAPPROVAL='Onaylandı' and SICILNO=? and STATUS = '0'";
			logger.trace(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			preparedStatement.setLong(1, sicilNo);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				permission = new Permission();
				permission.setId(rs.getLong("ID"));
				permission.setSicilNo(rs.getLong("SICILNO"));
				permission.setAciklama(rs.getString("DESCRIPTION"));
				permission.setAdres(rs.getString("ADDRESS"));
				permission.setFormTarihi(rs.getString("PERMISSIONCREATINGHISTORY"));
				permission.setBaslangicTarihi(rs.getString("STARTINGDATE"));
				permission.setBirinciYoneticiOnayi(rs.getString("FIRSTMANAGERAPPROVAL"));
				permission.setBitisTarihi(rs.getString("DATEOFRETURN"));
				permission.setDurum(rs.getString("STATUS"));
				permission.setIkinciYoneticiOnayi(rs.getString("FIRSTMANAGERAPPROVAL"));
				permission.setIkOnayi(rs.getString("IKAPPROVAL"));
				permission.setTelefonNumarasi(rs.getString("PHONENUMBER"));
				permission.setIzinNedeni(rs.getString("PERMISSIONREASON"));
				permission.setGun(rs.getInt("DAY"));
				permission.setFormFiller(rs.getLong("FORMFILLER"));
				permissions.add(permission);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO getPersonelApproval metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("PermissionDAO getPersonelApproval metodu çalışması bitti.");
		}
		return permissions;
	}

	public void deniedPermissionFirstManager(Permission permission) throws Exception {
		logger.debug("PermissionDAO deniedPermissionFirstManager metodu çalışmaya başladı.");
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			String query = "UPDATE permission SET FIRSTMANAGERAPPROVAL = 'Reddedildi' ,STATUS = 'Reddedildi' , COMMENT= ? WHERE ID =?";
			logger.trace(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			preparedStatement.setString(1, permission.getComment());
			preparedStatement.setLong(2, permission.getId());
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO deniedPermissionFirstManager metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("PermissionDAO deniedPermissionFirstManager metodu çalışması bitti.");
		}
	}

	public void confirmedPermissionFirstManager(Permission permission) throws Exception {
		logger.debug("PermissionDAO confirmedPermissionFirstManager metodu çalışmaya başladı.");
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			String query = "UPDATE permission SET FIRSTMANAGERAPPROVAL = 'Onaylandı' , COMMENT = ? WHERE ID =?";
			logger.trace(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			preparedStatement.setString(1, permission.getComment());
			preparedStatement.setLong(2, permission.getId());
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO confirmedPermissionFirstManager metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("PermissionDAO confirmedPermissionFirstManager metodu çalışması bitti.");
		}
	}

	public void deniedPermissionSecondManager(Permission permission) throws Exception {
		logger.debug("PermissionDAO deniedPermissionSecondManager metodu çalışmaya başladı.");
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			String query = "UPDATE permission SET SECONDMANAGERAPPROVAL = 'Reddedildi' ,STATUS = 'Reddedildi', COMMENT=?  WHERE ID =?";
			logger.trace(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			preparedStatement.setString(1, permission.getComment());
			preparedStatement.setLong(2, permission.getId());
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO deniedPermissionSecondManager metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("PermissionDAO deniedPermissionSecondManager metodu çalışması bitti.");
		}
	}

	public void confirmedPermissionSecondManager(Permission permission) throws Exception {
		logger.debug("PermissionDAO confirmedPermissionSecondManager metodu çalışmaya başladı.");
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			String query = "UPDATE permission SET SECONDMANAGERAPPROVAL = 'Onaylandı',COMMENT=?  WHERE ID =?";
			logger.trace(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			preparedStatement.setString(1, permission.getComment());
			preparedStatement.setLong(2, permission.getId());
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO confirmedPermissionSecondManager metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("PermissionDAO confirmedPermissionSecondManager metodu çalışması bitti.");
		}
	}

	public void deniedPermissionHR(Permission permission) throws Exception {
		logger.debug("PermissionDAO deniedPermissionHR metodu çalışmaya başladı.");
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			String query = "UPDATE permission SET IKAPPROVAL = 'Reddedildi' ,STATUS = 'Reddedildi', COMMENT=? WHERE ID =?";
			logger.trace(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			preparedStatement.setString(1, permission.getComment());
			preparedStatement.setLong(2, permission.getId());
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO deniedPermissionHR metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("PermissionDAO deniedPermissionHR metodu çalışması bitti.");
		}
	}

	public void confirmedPermissionHR(Permission permission) throws Exception {
		logger.debug("PermissionDAO confirmedPermissionHR metodu çalışmaya başladı.");
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			String query = "UPDATE permission SET IKAPPROVAL = 'Onaylandı' , COMMENT= ?WHERE ID =?";
			logger.trace(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			preparedStatement.setString(1, permission.getComment());
			preparedStatement.setLong(2, permission.getId());
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO confirmedPermissionHR metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("PermissionDAO confirmedPermissionHR metodu çalışması bitti.");
		}
	}

	public void deniedPermissionPersonel(Permission permission1) throws Exception {
		logger.debug("PermissionDAO deniedPermissionPersonel metodu çalışmaya başladı.");
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			String query = "UPDATE permission SET STATUS = 'Personel reddetti' , COMMENT=? WHERE ID =?";
			logger.trace(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			preparedStatement.setString(1, permission1.getComment());
			preparedStatement.setLong(2, permission1.getId());
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO deniedPermissionPersonel metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("PermissionDAO deniedPermissionPersonel metodu çalışması bitti.");
		}
	}

	public void confirmedPermissionPersonel(Permission permission1) throws Exception {
		logger.debug("PermissionDAO confirmedPermissionPersonel metodu çalışmaya başladı.");
		Permission permission = ServiceFacade.getInstance().getPermission(permission1.getId());
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			String query = "UPDATE permission SET STATUS = 'Onaylandı', COMMENT=? WHERE ID =?";
			logger.trace(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			preparedStatement.setString(1, permission1.getComment());
			preparedStatement.setLong(2, permission1.getId());
			preparedStatement.executeUpdate();
			conn.commit();
			ServiceFacade.getInstance().decreasePermissionRight(permission.getSicilNo(), permission.getGun());
		} catch (Exception e) {
			logger.error("PermissionDAO confirmedPermissionPersonel metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("PermissionDAO confirmedPermissionPersonel metodu çalışması bitti.");
		}
	}

	public void cancelPermission(long permissionId) throws Exception {
		logger.debug("PermissionDAO cancelPermission metodu çalışmaya başladı.");
		Permission permission = ServiceFacade.getInstance().getPermission(permissionId);
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			String query = "UPDATE permission SET STATUS = 'İptal' WHERE ID =?";
			logger.trace(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			preparedStatement.setLong(1, permissionId);
			preparedStatement.executeUpdate();
			conn.commit();
			ServiceFacade.getInstance().addOnPermissionRight(permission.getSicilNo(), permission.getGun());
		} catch (Exception e) {
			logger.error("PermissionDAO cancelPermission metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("PermissionDAO cancelPermission metodu çalışması bitti.");
		}

	}

	public List<Permission> getMyOwnPermissions(long id) throws Exception {
		logger.debug("PermissionDAO getMyOwnPermissions metodu çalışmaya başladı.");
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = "SELECT  * FROM permission where SICILNO=?  ";
		ArrayList<Permission> permissions = new ArrayList<>();
		Permission permission;
		logger.trace(query.toString());
		Connection conn = (Connection) getConnection();
		try {
			pst = (PreparedStatement) conn.prepareStatement(query);
			pst.setLong(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				permission = new Permission();
				permission.setId(rs.getLong("ID"));
				permission.setFormTarihi(rs.getString("PERMISSIONCREATINGHISTORY"));
				permission.setSicilNo(rs.getLong("SICILNO"));
				permission.setAciklama(rs.getString("DESCRIPTION"));
				permission.setAdres(rs.getString("ADDRESS"));
				permission.setBaslangicTarihi(rs.getString("STARTINGDATE"));
				permission.setBirinciYoneticiOnayi(rs.getString("FIRSTMANAGERAPPROVAL"));
				permission.setBitisTarihi(rs.getString("DATEOFRETURN"));
				permission.setDurum(rs.getString("STATUS"));
				permission.setIkinciYoneticiOnayi(rs.getString("FIRSTMANAGERAPPROVAL"));
				permission.setIkOnayi(rs.getString("IKAPPROVAL"));
				permission.setTelefonNumarasi(rs.getString("PHONENUMBER"));
				permission.setIzinNedeni(rs.getString("PERMISSIONREASON"));
				permission.setGun(rs.getInt("DAY"));
				permission.setFormFiller(rs.getLong("FORMFILLER"));
				permissions.add(permission);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("PermissionDAO getMyOwnPermissions metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(pst);
			closeConnection(conn);
			logger.debug("PermissionDAO getMyOwnPermissions metodu çalışması bitti.");
		}
		return permissions;
	}
}