package dao;

import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import dto.RightOfPermission;;

public class RightOfPermissionDAO extends DatabaseHelper {
	Logger logger = Logger.getLogger(RightOfPermissionDAO.class);

	public void init(Properties appProperties) {
		logger.debug("RightOfPermissionDAO init metodu çalışmaya başladı.");
		super.init(appProperties);
		logger.debug("RightOfPermissionDAO init metodu çalışması bitti.");
	}
	
	public void addRightOfPermission(RightOfPermission rightOfPermission) throws Exception {
		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {
			query.append(
					"INSERT INTO rightofpermission (SICILNO,VALIDDATE,DAYCOUNTOFDESERVED,DAYCOUNTOFDESERVEDFORYEAR)");
			query.append("VALUES  (?,NOW(),?,?) ");
			String queryString = query.toString();
			System.out.println(queryString);
			stmt = (PreparedStatement) conn.prepareStatement(queryString);

			stmt.setLong(1, rightOfPermission.getSicilNo());
			stmt.setInt(2, rightOfPermission.getHakedilenGunSayisi());
			stmt.setInt(3, rightOfPermission.getMevcutYilIciHakedilenGunSayisi());

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

	public RightOfPermission getRightOfPermission(long sicilNo) throws Exception {

		PreparedStatement pst = null;
		ResultSet rs = null;
		RightOfPermission rightOfPermission = new RightOfPermission();
		String query = "SELECT * FROM rightofpermission WHERE  SICILNO=?";

		try {
			Connection conn = (Connection) getConnection();

			pst = (PreparedStatement) conn.prepareStatement(query);

			pst.setLong(1, sicilNo);
			rs = pst.executeQuery();
			System.out.println("bue");
			if (rs.next()) {

				rightOfPermission.setSicilNo(rs.getLong("SICILNO"));
				rightOfPermission.setMevcutYilIciHakedilenGunSayisi(rs.getInt("DAYCOUNTOFDESERVEDFORYEAR"));
				rightOfPermission.setHakedilenGunSayisi(rs.getInt("DAYCOUNTOFDESERVED"));
				rightOfPermission.setGecerliOlacagiTarih(null);
				System.out.println(rs.getLong("SICILNO"));

			} else {
				System.out.println("olmadi");
			}
		} catch (Exception e) {
			logger.error("getDepartment error:" + e.getMessage());
		} finally {

		}

		return rightOfPermission;

	}

	public List<RightOfPermission> getAllRightOfPermission() throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<RightOfPermission> rightOfPermissions = new ArrayList<>();
		String query = "SELECT * FROM rightofpermission ";

		try {
			Connection conn = (Connection) getConnection();

			pst = (PreparedStatement) conn.prepareStatement(query);

			rs = pst.executeQuery();

			if (rs.next()) {
				RightOfPermission rightOfPermission = new RightOfPermission();
				rightOfPermission.setSicilNo(rs.getLong("SICILNO"));
				rightOfPermission.setMevcutYilIciHakedilenGunSayisi(rs.getInt("DAYCOUNTOFDESERVEDFORYEAR"));
				rightOfPermission.setHakedilenGunSayisi(rs.getInt("DAYCOUNTOFDESERVED"));
				rightOfPermission.setGecerliOlacagiTarih(null);
				rightOfPermissions.add(rightOfPermission);
			} else {
				System.out.println("olmadi");
			}
		} catch (Exception e) {
			logger.error("getDepartment error:" + e.getMessage());
		} finally {

		}

		return rightOfPermissions;

	}

	public void updateRightOfPermission(RightOfPermission rightOfPermission) throws Exception {
		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {
			query.append("UPDATE rightofpermission  SET VALIDDATE =");
			query.append(" NOW()    ,DAYCOUNTOFDESERVED = ?   ,DAYCOUNTOFDESERVEDFORYEAR = ?   WHERE SICILNO = ? ");
			String queryString = query.toString();
			System.out.println(queryString);
			stmt = (PreparedStatement) conn.prepareStatement(queryString);

			stmt.setLong(3, rightOfPermission.getSicilNo());
			stmt.setInt(1, rightOfPermission.getHakedilenGunSayisi());
			stmt.setInt(2, rightOfPermission.getMevcutYilIciHakedilenGunSayisi());

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

	public void deleteRightOfPermission(RightOfPermission rightOfPermission) throws Exception {

		int temp = (int) rightOfPermission.getSicilNo();// ID isterse rollerden gelebilir.
		int sicilNo = (int) temp;

		// departman user ve ticket kontrolu
		Connection con = null;
		PreparedStatement pstDepartment = null;
		StringBuilder queryDeleteDepartment = new StringBuilder();

		try {

			queryDeleteDepartment.append("DELETE FROM rightofpermission ");
			queryDeleteDepartment.append("WHERE SICILNO=?");
			String queryString = queryDeleteDepartment.toString();
			logger.debug("sql query created : " + queryString);

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

