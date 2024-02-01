package edu.employee.connectionPool;

import java.sql.Connection;

public class Test {
	
	public static void main(String[] args) {
		Connection connection = ConnectionP.getConnection();
//		Connection connection1 = ConnectionP.getConnection();
		
		System.out.println(connection);
		
		ConnectionP.receiveConnection(connection);
	}
}
