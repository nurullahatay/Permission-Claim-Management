package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.log4j.Logger;
import com.mysql.jdbc.PreparedStatement;
import bean.DatabaseProperties;
import dto.Department;

public class DepartmentDAO extends DatabaseHelper {
	final Logger logger = Logger.getLogger(DepartmentDAO.class);

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

	public void addDepartment(Department department) throws Exception {
		logger.debug("addDepartment is started");

		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {
			query.append("INSERT INTO department ");
			query.append("VALUES (?,?,?,?) ");
			String queryString = query.toString();
			logger.debug("sql query created : " + queryString);

			stmt = (PreparedStatement) conn.prepareStatement(queryString);

			stmt.setLong(1, department.getId());
			stmt.setString(2, department.getDepartmentName());
			stmt.setLong(3, department.getDepartmentFirstManager());
			stmt.setLong(4, department.getDepartmentSecondManager());

			stmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			throw e;
		} finally {

			closePreparedStatement(stmt);
			closeConnection(conn);

		}
		logger.debug("addDepartment finished. company # is ");

	}

	public Department getDepartment(long id) throws Exception {
		logger.debug("getDepartment is started");

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = (Connection) getConnection();
		Department department = new Department();
		String query = "SELECT * FROM  department WHERE  ID=?";
		logger.debug("sql query created : " + query);

		try {

			pst = (PreparedStatement) conn.prepareStatement(query);

			pst.setLong(1, id);
			rs = pst.executeQuery();

			if (rs.next()) {
				department.setId(rs.getLong("ID"));
				department.setDepartmentName(rs.getString("DEPARTMANNAME"));
				department.setDepartmentFirstManager(rs.getLong("DEPARTMENTFIRSTMANAGER"));
				department.setDepartmentSecondManager(rs.getLong("DEPARTMENTSECONDMANAGER"));

			} else {
				System.out.println("olmadi");
			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("getDepartment error:" + e.getMessage());
		} finally {
			closeResultSet(rs);
			closePreparedStatement(pst);
			closeConnection(conn);
		}
		logger.debug("getDepartment finished.  ");

		return department;

	}

	public ArrayList<Department> getAllDepartment() throws Exception {
		logger.debug("getAllDepartment is started");

		Connection conn = null;

		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Department department;
		ArrayList<Department> departments = new ArrayList<>();
		try {
			String query = "SELECT *   FROM  department";
			logger.debug("sql query created : " + query);
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				department = new Department();
				System.out.println(rs.getLong(1));

				department.setId(rs.getLong("ID"));
				System.out.println("" + rs.getString("DEPARTMANNAME"));
				department.setDepartmentName(rs.getString("DEPARTMANNAME"));
				department.setDepartmentFirstManager(rs.getLong("DEPARTMENTFIRSTMANAGER"));
				department.setDepartmentSecondManager(rs.getLong("DEPARTMENTSECONDMANAGER"));
				departments.add(department);
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
		logger.debug("getAllDepartment finished.  " + departments.size());
		return departments;
	}

	public void updateDepartment(Department department) throws Exception {
		logger.debug("updateDepartment is started");

		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {
			query.append("UPDATE department  set ");
			query.append("DEPARTMANNAME = ? ,DEPARTMENTFIRSTMANAGER = ?,DEPARTMENTSECONDMANAGER = ? WHERE   ID = ?");
			String queryString = query.toString();
			logger.debug("sql query created : " + queryString);

			stmt = (PreparedStatement) conn.prepareStatement(queryString);

			stmt.setLong(3, department.getDepartmentSecondManager());
			stmt.setString(1, department.getDepartmentName());
			stmt.setLong(2, department.getDepartmentFirstManager());

			stmt.setLong(4, department.getId());

			stmt.executeUpdate();

			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			logger.error("update islevinde sýkýntý oldu");

			throw e;
		} finally {

			closePreparedStatement(stmt);
			closeConnection(conn);
		}
		logger.debug("updateDepartment finished.");
	}

	public void deleteDepartment(Department department) throws Exception {
		logger.debug("deleteDepartment is started");
		int temp = (int) department.getId();// ID isterse rollerden gelebilir.
		int sicilNo = (int) temp;

		// departman user ve ticket kontrolu
		Connection conn = null;
		PreparedStatement pstDepartment = null;
		StringBuilder queryDeleteDepartment = new StringBuilder();

		try {

			queryDeleteDepartment.append("DELETE FROM department ");
			queryDeleteDepartment.append("WHERE ID=?");
			String queryString = queryDeleteDepartment.toString();
			logger.debug("sql query created : " + queryString);

			conn = getConnection();
			pstDepartment = (PreparedStatement) conn.prepareStatement(queryString);

			pstDepartment.setLong(1, sicilNo);
			pstDepartment.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("error:" + e.getMessage());
		} finally {
			closePreparedStatement(pstDepartment);

			closeConnection(conn);
		}
		logger.debug("deleteDepartment is finished");
	}
}
