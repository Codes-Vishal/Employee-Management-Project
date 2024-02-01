package edu.employee.connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
	
	private static final int POOL_SIZE = 5;
	private static List<Connection> connections = new ArrayList<>();
	private static String url = "jdbc:postgresql://localhost:5432/Test";
	private static String user = "postgres";
	private static String password = "root";
	
	static {
		try {

			Class.forName("org.postgresql.Driver");
			
			for(int i = 0; i < POOL_SIZE ; i++) {
				connections.add(createConnection());
			}
//			System.out.println("Connection added : "+connections.size());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public static Connection getConnection() {
		
		if(!connections.isEmpty()) {
//			System.out.println("Connection added : "+connections.size());
			return connections.remove(0);
		}
		else {
			return createConnection();
		}
	}
	
	public static Connection createConnection() {
		Connection connection;
		try { 
			connection = DriverManager.getConnection(url, user, password);
			return connection;	
	
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void receiveConnection(Connection connection) {
		
		if(connections.size() < POOL_SIZE) {
			
			connections.add(connection);
		
//			System.out.println("Connection Received! remaining Connections : "+connections.size());
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
