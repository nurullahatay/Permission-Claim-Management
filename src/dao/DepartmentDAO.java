package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;

import com.mysql.jdbc.PreparedStatement;

import bean.DatabaseProperties;
import dto.Department;

public class DepartmentDAO extends DatabaseHelper {
	final Logger logger = Logger.getLogger(DepartmentDAO.class);

	public void init(Properties appProperties) {  s
		logger.debug("DepartmentDAO init metodu çalışmaya başladı.");
		super.init(appProperties);
		logger.debug("DepartmentDAO init metodu çalışması bitti.");
	}

	public void addDepartment(Department department ) throws Exception {
		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {
			query.append(
					"INSERT INTO department ");
			query.append("VALUES (?,?,?,?) ");
			String queryString = query.toString();
			System.out.println(queryString);
			stmt = (PreparedStatement) conn.prepareStatement(queryString);

			stmt.setLong(1, department.getId());
			stmt.setString(2,department.getDepartmentName());
			stmt.setLong(3, department.getDepartmentFirstManager());
			stmt.setLong(4, department.getDepartmentSecondManager());

			stmt.executeUpdate();

 

		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		} finally {
			if (stmt != null)
				stmt.close();

		}
	}

	public Department getDepartment(long id) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Department department = new Department();
		String query = "SELECT * FROM  department WHERE  ID=?";

		try {
			Connection conn = (Connection) getConnection();

			pst = (PreparedStatement) conn.prepareStatement(query);

			pst.setLong(1, id);
			rs = pst.executeQuery();
		 
			if (rs.next()) {
				department.setId(rs.getLong("ID"));
				department.setDepartmentName(rs.getString("DEPARTMANNAME"));
				department.setDepartmentFirstManager(rs.getLong("DEPARTMENTFIRSTMANAGER"));
				department.setDepartmentSecondManager(rs.getLong("DEPARTMENTSECONDMANAGER"));
	 System.out.println(rs.getString("DEPARTMANNAME"));

			} else {
				System.out.println("olmadi");
			}
		} catch (Exception e) {
			logger.error("getDepartment error:" + e.getMessage());
		} finally {

		}

		return department;

	}
	
	public List<Department> getAllDepartment() throws Exception {
		logger.debug("getAllDepartments started");

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		ArrayList<Department> departments = new ArrayList<>();

		try {

			String query = "SELECT * FROM department";
			 

			con = getConnection();

			pst = (PreparedStatement) con.prepareStatement(query);

			rs = pst.executeQuery();

			while (rs.next()) {
				Department department = new Department();
				department.setId(rs.getLong("ID"));
				System.out.println(""+rs.getString("DEPARTMANNAME"));
				department.setDepartmentName(rs.getString("DEPARTMANNAME"));
				department.setDepartmentFirstManager(rs.getLong("DEPARTMENTFIRSTMANAGER"));
				department.setDepartmentSecondManager(rs.getLong("DEPARTMENTSECONDMANAGER"));
				departments.add(department);
			}

		} catch (Exception e) {
		 
		} finally {
		 
			closeConnection(con);
		}
	 
		return departments;
	}
	
	public void updateDepartment(Department department) throws Exception {
		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {
			query.append(
					"UPDATE department  set ");
			query.append("DEPARTMANNAME = ? ,DEPARTMENTFIRSTMANAGER = ?,DEPARTMENTSECONDMANAGER = ? WHERE   ID = ?");
			String queryString = query.toString();
			
			System.out.println(queryString);
			stmt = (PreparedStatement) conn.prepareStatement(queryString);

			stmt.setLong(3, department.getDepartmentSecondManager());
			stmt.setString(1, department.getDepartmentName());
			stmt.setLong(2, department.getDepartmentFirstManager());

			stmt.setLong(4,department.getId() );

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
	
	public void deleteDepartment(Department department) throws Exception {
		int temp =(int) department.getId();//ID isterse rollerden gelebilir.
		int sicilNo = (int)temp;
		 
				// departman user ve ticket kontrolu
				Connection con = null;
				PreparedStatement pstDepartment = null;
				StringBuilder queryDeleteDepartment = new StringBuilder();

				try {
					
					queryDeleteDepartment.append("DELETE FROM department ");
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
	}
