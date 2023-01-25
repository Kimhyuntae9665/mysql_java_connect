package data_base;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database_Connector {
	
	private static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/peed";
	private static final String USER_NAME = "root";
	private static final String USER_PASSWORD = "1234";
	
	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			return DriverManager.getConnection(DATABASE_URL,USER_NAME,USER_PASSWORD);
			
		}catch (Exception exception) {
			return null;
		}
		
		
	}

}
