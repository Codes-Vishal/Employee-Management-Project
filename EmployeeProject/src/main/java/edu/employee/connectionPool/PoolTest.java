package edu.employee.connectionPool;

import java.sql.Connection;

public class PoolTest {
	
	public static void main(String[] args) {
//		ConnectionPool connection = new ConnectionPool();
//		ConnectionPool connection1 = new ConnectionPool();
//		ConnectionPool connection2 = new ConnectionPool();
		
		Connection connection = ConnectionPool.getConnection();
		
//		System.out.println(connection);
//		System.out.println(connection1);
		ConnectionPool.receiveConnection(connection);
	}
}
