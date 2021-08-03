package application.db;

/*
 * This is the ConnectionFactory class which can be used
 * to establish connections to SQLite DB
 *
 * MyUTSA ID: gos049
 * Assignment: Resume Builder Project
 * Class: CS-3443-01T-Summer-2021-Application Programming
 * 
 * @author: Hamza Hamdan
 * 
 */

import java.sql.*;

public class ConnectionFactory {
	
	/** 
	* getConnection method to create a database connection
	* @return Connection
	* 
	*/
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
