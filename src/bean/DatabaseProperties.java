package bean;
public class DatabaseProperties {

	private String username;
	private String password;
	private String databaseConnectionURL;
	private String databaseDriver;
	private boolean isDataSource;
	private String jndiName;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDatabaseConnectionURL() {
		return databaseConnectionURL;
	}
	public void setDatabaseConnectionURL(String databaseConnectionURL) {
		this.databaseConnectionURL = databaseConnectionURL;
	}
	public String getDatabaseDriver() {
		return databaseDriver;
	}
	public void setDatabaseDriver(String databaseDriver) {
		this.databaseDriver = databaseDriver;
	}
	public boolean isDataSource() {
		return isDataSource;
	}
	public void setDataSource(boolean isDataSource) {
		this.isDataSource = isDataSource;
	}
	public String getJndiName() {
		return jndiName;
	}
	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}
	
	
}
