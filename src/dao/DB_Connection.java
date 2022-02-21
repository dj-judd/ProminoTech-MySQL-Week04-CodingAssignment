package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {
	
	private final static String URL = "jdbc:mysql://localhost:3306/vehicles";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "root";
	private static Connection connection;
	private static DB_Connection instance;
	
	private DB_Connection(Connection connection) {
		this.connection = connection;
	}
	
	public static Connection getConneciton() {
		if (instance == null) {
			try {
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				instance = new DB_Connection(connection);
				System.out.println("Connection Successful.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return DB_Connection.connection;
	}

}
