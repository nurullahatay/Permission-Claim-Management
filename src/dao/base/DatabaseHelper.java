package dao.base;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bean.DatabaseProperties;

public class DatabaseHelper {

	DatabaseProperties databaseProperties = null;

	protected void init(DatabaseProperties databaseProperties) {
		this.databaseProperties = databaseProperties;
	}

	public Connection getConnection() throws Exception {
		if (databaseProperties.isDataSource()) {
			return getDataSourceConnection();
		} else {
			return getTransactionalConnection();
		}
	}

	public Connection getRegularConnection() throws Exception {
		Connection conn = null;
		try {
			Class.forName(databaseProperties.getDatabaseDriver()).newInstance();
			conn = (Connection) DriverManager.getConnection(databaseProperties.getDatabaseConnectionURL(),
					databaseProperties.getUsername(), databaseProperties.getPassword());
		} catch (Exception exception) {
			throw exception;
		}
		return conn;
	}

	public Connection getDataSourceConnection() throws Exception {
		Context initContext = new InitialContext();
		DataSource ds = (DataSource) initContext.lookup(databaseProperties.getJndiName());
		Connection conn = ds.getConnection();
		conn.setAutoCommit(false);
		return conn;

	}

	public Connection getTransactionalConnection() throws Exception {
		Connection conn = getRegularConnection();
		conn.setAutoCommit(false);
		return conn;
	}

	public void closeConnection(Connection conn) throws Exception {
		if (!conn.isClosed()) {
			try {
				conn.close();
			} catch (Exception exception) {
			}
		}
	}

}