package utility;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// for establishing connection with database
public class ConnectionManager {
         
// loading driver class and creating connection object and checking whether connection established or not
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Properties prop=null;
		try {
			prop=loadPropertiesFile();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		final String driver=prop.getProperty("driver");
		final String url=prop.getProperty("url");
		final String username=prop.getProperty("username");
		final String password=prop.getProperty("password");
		
		// Registering the driver class
		Class.forName(driver);
		
		// creating connection object
		Connection con=null;
		
		con=DriverManager.getConnection(url,username,password);
		
		if(con==null)
			System.out.println("Check ur connection");
		return con;
	}
	
	// To load the jdbc properties from the jdbc.properties file.	
	public static Properties loadPropertiesFile() throws Exception {
		Properties prop = new Properties();	
		InputStream in = ConnectionManager.class.getClassLoader().getResourceAsStream("jdbc.properties");
		prop.load(in);
		in.close(); 
		return prop;
	}
}
