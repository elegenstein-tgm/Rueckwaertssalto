import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.*;

/**
 * @author Erik
 *
 */
public class MetadataCollector {
	static final String JDBC = "com.mysql.jdbc.Driver";
	private String host = null, uname = null, pwd = null, dbname = null;
	private DatabaseMetaData dbmd = null;
	private Connection conn = null;

	public void createConnection(String host, String username, String password,
			String databasename) {
		this.host = host;
		this.uname = username;
		this.pwd = password;
		this.dbname = databasename;
		createConnection();
	}

	public void createConnection() {
		try {
			Class.forName(JDBC);
		} catch (ClassNotFoundException e) {
			System.err
			.println("JDBC can't be located. Make sure it is available");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + this.host
					+ "/" + dbname, uname, pwd);
		} catch (SQLException e) {
			System.err
			.println("Failed to connect to database\n Check credials and the dbname or hostname/ip");
			e.printStackTrace();
		}
	}

	public String[] getTabNames(){
		if(conn == null){
			System.err.println("Got no Connection");
		}else{
			try {
				dbmd=conn.getMetaData();
			} catch (SQLException e) {
				System.err.println("can't get Database-Metadata");
				e.printStackTrace();
			}
			if(dbmd!=null){
				ResultSet rs=null;
				try {
					rs = dbmd.getTables(null, null, "%", null);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					if(rs != null){
						while (rs.next()) {
							System.out.println(rs.getString(3));
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
