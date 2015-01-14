import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.*;

/**
 * @author Erik
 *
 */
public class MetadataCollector {
	static final String JDBC = "com.mysql.jdbc.Driver";
	private String host=null, uname=null, pwd=null, dbname=null;
	Connection conn = null;
	public void createConnection(String host, String username, String password, String databasename){
		this.host=host;
		this.uname=username;
		this.pwd=password;
		this.dbname=databasename;
		createConnection();
	}
	public void createConnection(){
		try {
			Class.forName(JDBC);
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC can't be located. Make sure it is available");
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + dbname, uname, pwd);
		} catch (SQLException e) {
			System.err.println("Failed to connect to database\n Check credials and the dbname or hostname/ip");
		}
		
	}
}
