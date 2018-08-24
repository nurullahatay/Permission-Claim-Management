package dao;

import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import dto.Permission;

public class PermissionDAO extends DatabaseHelper {

	final Logger logger = Logger.getLogger(PermissionDAO.class);

	public void init(Properties appProperties) {
		logger.debug("ClaimForPermissionDAO init metodu çalışmaya başladı.");
		super.init(appProperties);
		logger.debug("ClaimForPermissionDAO init metodu çalışması bitti.");
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

	public List<Permission> getAllPermission() throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
	
		String query = "SELECT  * FROM permission  ";
		ArrayList<Permission> permissions = new ArrayList<>();

		try {
			Connection conn = (Connection) getConnection();

			pst = (PreparedStatement) conn.prepareStatement(query);

		 
			rs = pst.executeQuery();
			// dto'da form no var ama database yok ona bak istersen eklerim...
			if (rs.next()) {
				Permission permission = new Permission();
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
			} else {
				System.out.println("olmadi");
			}
		} catch (Exception e) {
			logger.error("getDepartment error:" + e.getMessage());
		} finally {

		}

		return permissions;


	}

	public void updatePermission(Permission permission) throws Exception {
		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {
			query.append(
					"UPDATE permission  SET ");
			query.append("SICILNO = ? ,PERMISSIONCREATINGHISTORY = NOW()  ,STARTINGDATE = NOW()  ,DATEOFRETURN = NOW() ");
			query.append(" ,DAY = ?  ,PERMISSIONREASON = ? ,");
			query.append(" DESCRIPTION = ? ,PHONENUMBER = ?  ,ADDRESS = ?");
			query.append(",SECONDMANAGERAPPROVAL = ? ,FIRSTMANAGERAPPROVAL = ?  ,IKAPPROVAL = ?, STATUS = ?   WHERE ID = ? ");
			String queryString = query.toString();
			
			System.out.println(queryString);
			stmt = (PreparedStatement) conn.prepareStatement(queryString);

			stmt.setLong(1, permission.getSicilNo());
			stmt.setInt(2, permission.getGun());
			stmt.setString(3,permission.getIzinNedeni());

			stmt.setString(4,permission.getAciklama());
			stmt.setString(5,permission.getTelefonNumarasi());
			stmt.setString(6,permission.getAdres());
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
		int temp =(int) permission.getId();//ID isterse rollerden gelebilir.
		int sicilNo = (int)temp;
		 
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
	}
