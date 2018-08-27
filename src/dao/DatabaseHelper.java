package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import bean.DatabaseProperties;

public class DatabaseHelper {
	final Logger logger = Logger.getLogger(DatabaseHelper.class);

	DatabaseProperties databaseProperties = null;

	protected void init(DatabaseProperties databaseProperties) {
		logger.debug("DatabaseHelper init metodu �al��maya ba�lad�.");
		this.databaseProperties = databaseProperties;
		logger.debug("DatabaseHelper init metodu �al��mas� bitti.");
	}

	public Connection getConnection() throws Exception {
		logger.debug("DatabaseHelper getConnection metodu �al��maya ba�lad�.");
		if (databaseProperties.isDataSource()) {
			logger.debug("DatabaseHelper getConnection metodu �al��mas� bitti.");
			return getDataSourceConnection();
		} else {
			logger.debug("DatabaseHelper getConnection metodu �al��mas� bitti.");
			return getTransactionalConnection();
		}
	}

	public Connection getRegularConnection() throws Exception {
		logger.debug("DatabaseHelper getRegularConnection metodu �al��maya ba�lad�.");
		Connection conn = null;
		try {
			Class.forName(databaseProperties.getDatabaseDriver()).newInstance();
			conn = (Connection) DriverManager.getConnection(databaseProperties.getDatabaseConnectionURL(),
					databaseProperties.getUsername(), databaseProperties.getPassword());
		} catch (Exception exception) {
			logger.fatal("DatabaseHelper getRegularConnection metodu exeption = " + exception);
			throw exception;
		}
		logger.debug("DatabaseHelper getRegularConnection metodu �al��mas� bitti.");
		return conn;
	}

	public Connection getDataSourceConnection() throws Exception {
		logger.debug("DatabaseHelper getDataSourceConnection metodu �al��maya ba�lad�.");
		Context initContext = new InitialContext();
		DataSource ds = (DataSource) initContext.lookup(databaseProperties.getJndiName());
		Connection conn = ds.getConnection();
		conn.setAutoCommit(false);
		logger.debug("DatabaseHelper getDataSourceConnection metodu �al��mas� bitti.");
		return conn;

	}

	public Connection getTransactionalConnection() throws Exception {
		logger.debug("DatabaseHelper getTransactionalConnection metodu �al��maya ba�lad�.");
		Connection conn = getRegularConnection();
		conn.setAutoCommit(false);
		logger.debug("DatabaseHelper getTransactionalConnection metodu �al��mas� bitti.");
		return conn;
	}

	public void closeConnection(Connection conn) throws Exception {
		logger.debug("DatabaseHelper closeConnection metodu �al��maya ba�lad�.");
		if (!conn.isClosed()) {
			try {
				conn.close();
			} catch (Exception exception) {
				logger.fatal("DatabaseHelper getTransactionalConnection metodu exeption = " + exception);
			}
		}
		logger.debug("DatabaseHelper closeConnection metodu �al��mas� bitti.");

	}
	
	public void closeResultSet(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			logger.trace("ResultSet closed");
		} catch (Exception e) {
			logger.trace("ResultSet close error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void closePreparedStatement(PreparedStatement pst) {
		try {
			if (pst != null)
				pst.close();
			logger.trace("PreparedStatement closed");
		} catch (Exception e) {
			logger.trace("PreparedStatement close error: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
