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
		logger.debug("DepartmentDAO init metodu çalýþmaya baþladý.");
		DatabaseProperties databaseProperties = new DatabaseProperties();
		databaseProperties.setUsername(appProperties.getProperty("dbuser"));
		databaseProperties.setPassword(appProperties.getProperty("dbpassword"));
		databaseProperties.setDatabaseConnectionURL(appProperties.getProperty("database"));
		databaseProperties.setDatabaseDriver(appProperties.getProperty("databaseDriver"));
		databaseProperties.setJndiName(appProperties.getProperty("jndiName"));
		databaseProperties.setDataSource(Boolean.parseBoolean(appProperties.getProperty("isDataSource")));
		super.init(databaseProperties);
		logger.debug("DepartmentDAO init metodu çalýþmasý bitti.");
	}

	public void addDepartment(Department department) throws Exception {
		logger.debug("DepartmentDAO addDepartment metodu çalýþmaya baþladý.");
		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {
			query.append("INSERT INTO department ");
			query.append("VALUES (?,?,?,?) ");
			logger.trace(query.toString());
			stmt = (PreparedStatement) conn.prepareStatement(query.toString());
			stmt.setLong(1, department.getId());
			stmt.setString(2, department.getDepartmentName());
			stmt.setLong(3, department.getDepartmentFirstManager());
			stmt.setLong(4, department.getDepartmentSecondManager());
			stmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("DepartmentDAO addDepartment metodu exeption = " + e);
			throw e;
		} finally {
			closePreparedStatement(stmt);
			closeConnection(conn);
			logger.debug("DepartmentDAO addDepartment metodu çalýþmasý bitti.");
		}
	}

	public Department getDepartment(long id) throws Exception {
		logger.debug("DepartmentDAO getDepartment metodu çalýþmaya baþladý.");
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = (Connection) getConnection();
		Department department = new Department();
		String query = "SELECT * FROM  department WHERE  ID=?";
		logger.trace(query.toString());
		try {
			pst = (PreparedStatement) conn.prepareStatement(query);
			pst.setLong(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				department.setId(rs.getLong("ID"));
				department.setDepartmentName(rs.getString("DEPARTMANNAME"));
				department.setDepartmentFirstManager(rs.getLong("DEPARTMENTFIRSTMANAGER"));
				department.setDepartmentSecondManager(rs.getLong("DEPARTMENTSECONDMANAGER"));
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("DepartmentDAO getDepartment metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(pst);
			closeConnection(conn);
			logger.debug("DepartmentDAO getDepartment metodu çalýþmasý bitti.");
		}
		return department;
	}

	public ArrayList<Department> getAllDepartment() throws Exception {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Department department;
		ArrayList<Department> departments = new ArrayList<>();
		try {
			String query = "SELECT *   FROM  department";
			logger.trace(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				department = new Department();
				System.out.println(rs.getLong(1));
				department.setDepartmentName(rs.getString("DEPARTMANNAME"));
				department.setDepartmentFirstManager(rs.getLong("DEPARTMENTFIRSTMANAGER"));
				department.setDepartmentSecondManager(rs.getLong("DEPARTMENTSECONDMANAGER"));
				departments.add(department);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("DepartmentDAO getAllDepartment metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("DepartmentDAO getAllDepartment metodu çalýþmasý bitti.");
		}
		return departments;
	}

	public void updateDepartment(Department department) throws Exception {
		logger.debug("DepartmentDAO updateDepartment metodu çalýþmaya baþladý.");
		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {
			query.append("UPDATE department  set ");
			query.append("DEPARTMANNAME = ? ,DEPARTMENTFIRSTMANAGER = ?,DEPARTMENTSECONDMANAGER = ? WHERE   ID = ?");
			String queryString = query.toString();
			logger.trace(query.toString());
			stmt = (PreparedStatement) conn.prepareStatement(queryString);
			stmt.setLong(3, department.getDepartmentSecondManager());
			stmt.setString(1, department.getDepartmentName());
			stmt.setLong(2, department.getDepartmentFirstManager());
			stmt.setLong(4, department.getId());
			stmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("DepartmentDAO updateDepartment metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(stmt);
			closeConnection(conn);
			logger.debug("DepartmentDAO updateDepartment metodu çalýþmasý bitti.");
		}
	}

	public void deleteDepartment(Department department) throws Exception {
		logger.debug("DepartmentDAO deleteDepartment metodu çalýþmaya baþladý.");
		int temp = (int) department.getId();
		int sicilNo = (int) temp;
		Connection conn = null;
		PreparedStatement pstDepartment = null;
		StringBuilder queryDeleteDepartment = new StringBuilder();
		try {
			queryDeleteDepartment.append("DELETE FROM department ");
			queryDeleteDepartment.append("WHERE ID=?");
			logger.trace( queryDeleteDepartment.toString());
			conn = getConnection();
			pstDepartment = (PreparedStatement) conn.prepareStatement( queryDeleteDepartment.toString());
			pstDepartment.setLong(1, sicilNo);
			pstDepartment.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("DepartmentDAO deleteDepartment metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(pstDepartment);
			closeConnection(conn);
			logger.debug("DepartmentDAO deleteDepartment metodu çalýþmasý bitti.");
		}
	}
}
