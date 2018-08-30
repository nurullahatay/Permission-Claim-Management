package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.log4j.Logger;
import com.mysql.jdbc.PreparedStatement;
import bean.DatabaseProperties;
import dto.RightOfPermission;;

public class RightOfPermissionDAO extends DatabaseHelper {
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

	public void addRightOfPermission(RightOfPermission rightOfPermission) throws Exception {
		logger.debug("addRightOfPermission is started");
		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {
			query.append(
					"INSERT INTO rightofpermission (SICILNO,VALIDDATE,DAYCOUNTOFDESERVED,DAYCOUNTOFDESERVEDFORYEAR)");
			query.append("VALUES  (?,?,?,?) ");
			String queryString = query.toString();
			logger.debug("sql query created : " + queryString);
			stmt = (PreparedStatement) conn.prepareStatement(queryString);

			stmt.setLong(1, rightOfPermission.getSicilNo());
			stmt.setString(2, rightOfPermission.getValidDate());
			stmt.setInt(3, rightOfPermission.getDayCountOfDeserved());
			stmt.setInt(4, rightOfPermission.getDayCountOfDeservedForYear());

			stmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			conn.rollback();
			throw e;
		} finally {

			closePreparedStatement(stmt);
			closeConnection(conn);
		}
		logger.debug("addRightOfPermission finished.");

	}

	public RightOfPermission getRightOfPermission(long sicilNo) throws Exception {
		logger.debug("RightOfPermission is started");
		Connection conn = (Connection) getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		RightOfPermission rightOfPermission = new RightOfPermission();
		String query = "SELECT * FROM rightofpermission WHERE  SICILNO=?";
		logger.debug("sql query created : " + query);
		try {

			pst = (PreparedStatement) conn.prepareStatement(query);

			pst.setLong(1, sicilNo);
			rs = pst.executeQuery();
			System.out.println("bue");
			if (rs.next()) {

				rightOfPermission.setSicilNo(rs.getLong("SICILNO"));
				rightOfPermission.setDayCountOfDeservedForYear(rs.getInt("DAYCOUNTOFDESERVEDFORYEAR"));
				rightOfPermission.setDayCountOfDeserved(rs.getInt("DAYCOUNTOFDESERVED"));
				rightOfPermission.setValidDate(null);

			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("RightOfPermission error:" + e.getMessage());
		} finally {
			closeResultSet(rs);
			closePreparedStatement(pst);
			closeConnection(conn);
		}
		logger.debug("RightOfPermission finished.");
		return rightOfPermission;

	}

	public ArrayList<RightOfPermission> getAllRightOfPermission() throws Exception {
		logger.debug("getAllRightOfPermission is started");

		Connection conn = null;

		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		RightOfPermission rightOfPermission;
		ArrayList<RightOfPermission> rightOfPermissions = new ArrayList<>();
		try {
			String query = "SELECT *   FROM  rightofpermission ";
			logger.debug("sql query created : " + query);
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				rightOfPermission = new RightOfPermission();
				System.out.println(rs.getLong(1));

				rightOfPermission.setSicilNo(rs.getLong("SICILNO"));
				rightOfPermission.setDayCountOfDeservedForYear(rs.getInt("DAYCOUNTOFDESERVEDFORYEAR"));
				rightOfPermission.setDayCountOfDeserved(rs.getInt("DAYCOUNTOFDESERVED"));
				rightOfPermission.setValidDate(rs.getString("VALIDDATE"));
				rightOfPermissions.add(rightOfPermission);
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
		logger.debug("getAllRightOfPermission finished. company # is " + rightOfPermissions.size());
		return rightOfPermissions;

	}

	public void updateRightOfPermission(RightOfPermission rightOfPermission) throws Exception {
		logger.debug("updateRightOfPermission is started");

		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {
			query.append("UPDATE rightofpermission  SET VALIDDATE =");
			query.append(" NOW()    ,DAYCOUNTOFDESERVED = ?   ,DAYCOUNTOFDESERVEDFORYEAR = ?   WHERE SICILNO = ? ");
			String queryString = query.toString();
			logger.debug("sql query created : " + queryString);

			stmt = (PreparedStatement) conn.prepareStatement(queryString);

			stmt.setLong(3, rightOfPermission.getSicilNo());
			stmt.setInt(1, rightOfPermission.getDayCountOfDeserved());
			stmt.setInt(2, rightOfPermission.getDayCountOfDeservedForYear());

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
		logger.debug("updateRightOfPermission finished. company ");
	}

	public void deleteRightOfPermission(long sicilNo) throws Exception {
		logger.debug("deleteRightOfPermission is started");


		// departman user ve ticket kontrolu
		Connection conn = null;
		PreparedStatement statement = null;
		StringBuilder queryDeleteDepartment = new StringBuilder();

		try {

			queryDeleteDepartment.append("DELETE FROM rightofpermission ");
			queryDeleteDepartment.append("WHERE SICILNO=?");
			String queryString = queryDeleteDepartment.toString();
			logger.debug("sql query created : " + queryString);

			conn = getConnection();
			statement = (PreparedStatement) conn.prepareStatement(queryString);

			statement.setLong(1, sicilNo);
			statement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("error:" + e.getMessage());
		} finally {

			closePreparedStatement(statement);
			closeConnection(conn);
		}
		logger.debug("deleteRightOfPermission is finished");
	}

	public void deleteAllRightOfPermission() throws Exception {
		logger.debug("deleteRightOfPermission is started");
		Connection conn = null;
		PreparedStatement statement = null;
		StringBuilder queryDeleteDepartment = new StringBuilder();

		try {

			queryDeleteDepartment.append("DELETE FROM rightofpermission ");
			queryDeleteDepartment.append("WHERE 1=1");
			String queryString = queryDeleteDepartment.toString();
			logger.debug("sql query created : " + queryString);
			conn = getConnection();
			statement = (PreparedStatement) conn.prepareStatement(queryString);
			statement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("error:" + e.getMessage());
		} finally {
			closePreparedStatement(statement);
			closeConnection(conn);
		}
		logger.debug("deleteRightOfPermission is finished");		
	}

}
