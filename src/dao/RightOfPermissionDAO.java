package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.log4j.Logger;
import com.mysql.jdbc.PreparedStatement;
import bean.DatabaseProperties;
import dto.RightOfPermission;
import service.ServiceFacade;;

public class RightOfPermissionDAO extends DatabaseHelper {
	Logger logger = Logger.getLogger(RightOfPermissionDAO.class);

	public void init(Properties appProperties) {
		logger.debug("RightOfPermissionDAO init metodu çalýþmaya baþladý.");
		DatabaseProperties databaseProperties = new DatabaseProperties();
		databaseProperties.setUsername(appProperties.getProperty("dbuser"));
		databaseProperties.setPassword(appProperties.getProperty("dbpassword"));
		databaseProperties.setDatabaseConnectionURL(appProperties.getProperty("database"));
		databaseProperties.setDatabaseDriver(appProperties.getProperty("databaseDriver"));
		databaseProperties.setJndiName(appProperties.getProperty("jndiName"));
		databaseProperties.setDataSource(Boolean.parseBoolean(appProperties.getProperty("isDataSource")));
		super.init(databaseProperties);
		logger.debug("RightOfPermissionDAO init metodu çalýþmasý bitti.");
	}

	public void addRightOfPermission(RightOfPermission rightOfPermission) throws Exception {
		logger.debug("RightOfPermissionDAO addRightOfPermission metodu çalýþmaya baþladý.");
		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {
			query.append(
					"INSERT INTO rightofpermission (SICILNO,VALIDDATE,DAYCOUNTOFDESERVED,DAYCOUNTOFDESERVEDFORYEAR)");
			query.append("VALUES  (?,?,?,?) ");
			String queryString = query.toString();
			logger.trace(query.toString());
			stmt = (PreparedStatement) conn.prepareStatement(queryString);
			stmt.setLong(1, rightOfPermission.getSicilNo());
			stmt.setString(2, rightOfPermission.getValidDate());
			stmt.setInt(3, rightOfPermission.getDayCountOfDeserved());
			stmt.setInt(4, rightOfPermission.getDayCountOfDeservedForYear());
			stmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("RightOfPermissionDAO addRightOfPermission metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(stmt);
			closeConnection(conn);
			logger.debug("RightOfPermissionDAO addRightOfPermission metodu çalýþmasý bitti.");
		}
	}

	public RightOfPermission getRightOfPermission(long sicilNo) throws Exception {
		logger.debug("RightOfPermissionDAO getRightOfPermission metodu çalýþmaya baþladý.");
		Connection conn = (Connection) getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		RightOfPermission rightOfPermission = new RightOfPermission();
		String query = "SELECT * FROM rightofpermission WHERE  SICILNO=?";
		logger.trace(query.toString());
		try {
			pst = (PreparedStatement) conn.prepareStatement(query);
			pst.setLong(1, sicilNo);
			rs = pst.executeQuery();
			if (rs.next()) {
				rightOfPermission.setSicilNo(rs.getLong("SICILNO"));
				rightOfPermission.setDayCountOfDeservedForYear(rs.getInt("DAYCOUNTOFDESERVEDFORYEAR"));
				rightOfPermission.setDayCountOfDeserved(rs.getInt("DAYCOUNTOFDESERVED"));
				rightOfPermission.setValidDate(null);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("RightOfPermissionDAO getRightOfPermission metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(pst);
			closeConnection(conn);
			logger.debug("RightOfPermissionDAO getRightOfPermission metodu çalýþmasý bitti.");
		}
		return rightOfPermission;
	}

	public ArrayList<RightOfPermission> getAllRightOfPermission() throws Exception {
		logger.debug("RightOfPermissionDAO getAllRightOfPermission metodu çalýþmaya baþladý.");
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		RightOfPermission rightOfPermission;
		ArrayList<RightOfPermission> rightOfPermissions = new ArrayList<>();
		try {
			String query = "SELECT *   FROM  rightofpermission ";
			logger.trace(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				rightOfPermission = new RightOfPermission();
				rightOfPermission.setSicilNo(rs.getLong("SICILNO"));
				rightOfPermission.setDayCountOfDeservedForYear(rs.getInt("DAYCOUNTOFDESERVEDFORYEAR"));
				rightOfPermission.setDayCountOfDeserved(rs.getInt("DAYCOUNTOFDESERVED"));
				rightOfPermission.setValidDate(rs.getString("VALIDDATE"));
				rightOfPermissions.add(rightOfPermission);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("RightOfPermissionDAO getAllRightOfPermission metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("RightOfPermissionDAO getAllRightOfPermission metodu çalýþmasý bitti.");
		}
		return rightOfPermissions;
	}

	public void updateRightOfPermission(RightOfPermission rightOfPermission) throws Exception {
		logger.debug("RightOfPermissionDAO updateRightOfPermission metodu çalýþmaya baþladý.");
		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {
			query.append("UPDATE rightofpermission  SET VALIDDATE =");
			query.append(" NOW()    ,DAYCOUNTOFDESERVED = ?   ,DAYCOUNTOFDESERVEDFORYEAR = ?   WHERE SICILNO = ? ");
			String queryString = query.toString();
			logger.trace(query.toString());
			stmt = (PreparedStatement) conn.prepareStatement(queryString);
			stmt.setLong(3, rightOfPermission.getSicilNo());
			stmt.setInt(1, rightOfPermission.getDayCountOfDeserved());
			stmt.setInt(2, rightOfPermission.getDayCountOfDeservedForYear());
			stmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("RightOfPermissionDAO updateRightOfPermission metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(stmt);
			closeConnection(conn);
			logger.debug("RightOfPermissionDAO updateRightOfPermission metodu çalýþmasý bitti.");
		}
	}

	public void deleteRightOfPermission(long sicilNo) throws Exception {
		logger.debug("RightOfPermissionDAO deleteRightOfPermission metodu çalýþmaya baþladý.");
		Connection conn = null;
		PreparedStatement statement = null;
		StringBuilder queryDeleteDepartment = new StringBuilder();
		try {
			queryDeleteDepartment.append("DELETE FROM rightofpermission ");
			queryDeleteDepartment.append("WHERE SICILNO=?");
			String queryString = queryDeleteDepartment.toString();
			logger.trace(queryDeleteDepartment.toString());
			conn = getConnection();
			statement = (PreparedStatement) conn.prepareStatement(queryString);
			statement.setLong(1, sicilNo);
			statement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("RightOfPermissionDAO deleteRightOfPermission metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(statement);
			closeConnection(conn);
			logger.debug("RightOfPermissionDAO deleteRightOfPermission metodu çalýþmasý bitti.");
		}
	}

	public void deleteAllRightOfPermission() throws Exception {
		logger.debug("RightOfPermissionDAO deleteAllRightOfPermission metodu çalýþmaya baþladý.");
		Connection conn = null;
		PreparedStatement statement = null;
		StringBuilder queryDeleteDepartment = new StringBuilder();
		try {
			queryDeleteDepartment.append("DELETE FROM rightofpermission ");
			queryDeleteDepartment.append("WHERE 1=1");
			String queryString = queryDeleteDepartment.toString();
			logger.trace(queryDeleteDepartment.toString());
			conn = getConnection();
			statement = (PreparedStatement) conn.prepareStatement(queryString);
			statement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("RightOfPermissionDAO deleteAllRightOfPermission metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(statement);
			closeConnection(conn);
			logger.debug("RightOfPermissionDAO deleteAllRightOfPermission metodu çalýþmasý bitti.");
		}
	}
	
	public void decreasePermission(long sicilNo, int day) throws Exception {
		logger.debug("RightOfPermissionDAO addPermission metodu çalýþmaya baþladý.");
		Connection conn = null;
		PreparedStatement statement = null;
		StringBuilder queryDeleteDepartment = new StringBuilder();
		RightOfPermission rightOfPermission = ServiceFacade.getInstance().getRightOfPermission(sicilNo);
		rightOfPermission.setDayCountOfDeservedForYear(rightOfPermission.getDayCountOfDeservedForYear()-day);
		
		try {
			queryDeleteDepartment.append("UPDATE rightofpermission SET DAYCOUNTOFDESERVEDFORYEAR =? WHERE SICILNO =?");
			String queryString = queryDeleteDepartment.toString();
			logger.trace(queryDeleteDepartment.toString());
			conn = getConnection();
			statement = (PreparedStatement) conn.prepareStatement(queryString);
			statement.setInt(1, rightOfPermission.getDayCountOfDeservedForYear());
			statement.setLong(2, sicilNo);
			statement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("RightOfPermissionDAO addPermission metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(statement);
			closeConnection(conn);
			logger.debug("RightOfPermissionDAO addPermission metodu çalýþmasý bitti.");
		}
	}
}
