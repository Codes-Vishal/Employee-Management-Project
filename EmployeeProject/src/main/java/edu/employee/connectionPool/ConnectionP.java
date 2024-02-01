package edu.employee.connectionPool;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionP {
	
	private static final int POOL_SIZE = 5;
	private static List<Connection> connections = new ArrayList<>();
 //	private static String url = "jdbc:postgresql://localhost:5432/sql_demo";
//	private static String user = "postgres";
//	private static String password = "root";
	static Properties properties = new Properties();
	static {
		try {
			 FileInputStream file = new FileInputStream("src/main/resources/config.properties");
			
			 ConnectionP.properties.load(file);
			 
			 Class.forName(properties.getProperty("driverpath"));
			 
			 for(int i = 0; i < POOL_SIZE; i++) {
				connections.add(createConnection()); 
			 }
			 System.out.println("Connection added : "+connections.size());
			 
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		if(!connections.isEmpty()) {
			System.out.println("connection addes! : "+connections.size());
			return connections.remove(0);
		}
		else {
			return createConnection();
		}
	}
	
	public static Connection createConnection() {
		Connection connection;
		try {
			connection = DriverManager.getConnection(properties.getProperty("dburl"), properties);
			return connection;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void receiveConnection(Connection connection) {
		
		if(connections.size() < POOL_SIZE) {
			connections.add(connection);
			System.out.println("Connection received! remaining connections : "+connections.size());
		}
		else {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}













