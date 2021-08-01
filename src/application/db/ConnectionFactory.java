package application.db;

import java.sql.*;

public class ConnectionFactory {
	
	public static Connection getConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:first.db");
			return connection;
		}catch(ClassNotFoundException | SQLException exc) {
			exc.printStackTrace();
			return null;
		}
	}

}
