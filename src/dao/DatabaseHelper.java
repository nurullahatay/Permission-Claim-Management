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
		logger.debug("DatabaseHelper init metodu çalýþmaya baþladý.");
		this.databaseProperties = databaseProperties;
		logger.debug("DatabaseHelper init metodu çalýþmasý bitti.");
	}

	public Connection getConnection() throws Exception {
		logger.debug("DatabaseHelper getConnection metodu çalýþmaya baþladý.");
		if (databaseProperties.isDataSource()) {
			logger.debug("DatabaseHelper getConnection metodu çalýþmasý bitti.");
			return getDataSourceConnection();
		} else {
			logger.debug("DatabaseHelper getConnection metodu çalýþmasý bitti.");
			return getTransactionalConnection();
		}
	}

	public Connection getRegularConnection() throws Exception {
		logger.debug("DatabaseHelper getRegularConnection metodu çalýþmaya baþladý.");
		Connection conn = null;
		try {
			Class.forName(databaseProperties.getDatabaseDriver()).newInstance();
			conn = (Connection) DriverManager.getConnection(databaseProperties.getDatabaseConnectionURL(),
					databaseProperties.getUsername(), databaseProperties.getPassword());
		} catch (Exception exception) {
			logger.fatal("DatabaseHelper getRegularConnection metodu exeption = " + exception);
			throw exception;
		}
		logger.debug("DatabaseHelper getRegularConnection metodu çalýþmasý bitti.");
		return conn;
	}

	public Connection getDataSourceConnection() throws Exception {
		logger.debug("DatabaseHelper getDataSourceConnection metodu çalýþmaya baþladý.");
		Context initContext = new InitialContext();
		DataSource ds = (DataSource) initContext.lookup(databaseProperties.getJndiName());
		Connection conn = ds.getConnection();
		conn.setAutoCommit(false);
		logger.debug("DatabaseHelper getDataSourceConnection metodu çalýþmasý bitti.");
		return conn;

	}

	public Connection getTransactionalConnection() throws Exception {
		logger.debug("DatabaseHelper getTransactionalConnection metodu çalýþmaya baþladý.");
		Connection conn = getRegularConnection();
		conn.setAutoCommit(false);
		logger.debug("DatabaseHelper getTransactionalConnection metodu çalýþmasý bitti.");
		return conn;
	}

	public void closeConnection(Connection conn) throws Exception {
		logger.debug("DatabaseHelper closeConnection metodu çalýþmaya baþladý.");
		if (!conn.isClosed()) {
			try {
				conn.close();
			} catch (Exception exception) {
				logger.fatal("DatabaseHelper getTransactionalConnection metodu exeption = " + exception);
			}
		}
		logger.debug("DatabaseHelper closeConnection metodu çalýþmasý bitti.");
	}
	
	public void closeResultSet(ResultSet rs) {
		logger.debug("DatabaseHelper closeResultSet metodu çalýþmaya baþladý.");
		try {
			if (rs != null)
				rs.close();
			logger.trace("closeResultSet metodu çalýþtý.");
		} catch (Exception e) {
			logger.error("closeResultSet close error: " + e.getMessage());
			e.printStackTrace();
		}
		logger.debug("DatabaseHelper closeResultSet metodu çalýþmasý bitti.");

	}

	public void closePreparedStatement(PreparedStatement pst) {
		logger.debug("DatabaseHelper closePreparedStatement metodu çalýþmaya baþladý.");
		try {
			if (pst != null)
				pst.close();
			logger.trace("closePreparedStatement metodu çalýþmaya baþladý.");
		} catch (Exception e) {
			logger.error("closePreparedStatement close error: " + e.getMessage());
			e.printStackTrace();
		}
		logger.debug("DatabaseHelper closePreparedStatement metodu çalýþmasý bitti.");
	}

}
