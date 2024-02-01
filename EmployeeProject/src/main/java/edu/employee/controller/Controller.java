package edu.employee.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.employee.connectionPool.ConnectionPool;
import edu.employee.model.Employee;

public class Controller {

	public Employee saveEmployee(Employee employee) {
	
		int id = employee.getId();
		String name = employee.getName();
		double salary = employee.getSal();
		long contact = employee.getContact();
		
		Connection connection = ConnectionPool.getConnection();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("insert into employee values (?,?,?,?)");
			
			statement.setInt(1, id);
			statement.setString(2, name);
			statement.setDouble(3, salary);
			statement.setLong(4, contact);
//			int result =
			statement.executeUpdate();
//			System.out.println(result + " rows affected\n");
			
			statement.close();
			ConnectionPool.receiveConnection(connection);
			
			return employee;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;	
		}
	}
	
	public Employee deleteEmployee(Employee target) {
		if(target != null ) {
			int id = target.getId();
	
			Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement;
			try {
				statement = connection.prepareStatement("delete from employee where id = ?");
				statement.setInt(1, id);
//				int result = 
				statement.executeUpdate();
//				System.out.println(result + "rows affected \n");
				
				statement.close();
				ConnectionPool.receiveConnection(connection);
				
				return target;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	public List<Employee> getAllEmployee() {
		
		List<Employee> employees=new ArrayList<>();
		try {
			 Connection connection = ConnectionPool.getConnection();
			 PreparedStatement statement = connection.prepareStatement("select * from employee order by id desc ");
			 ResultSet result = statement.executeQuery();
			 while(result.next()) {
				
				Employee employee = new Employee();
	
				employee.setId(result.getInt(1));
				employee.setName(result.getString(2));
				employee.setSal(result.getDouble(3));
				employee.setContact(result.getLong(4));
				
				employees.add(employee);
			}
			statement.close();
			ConnectionPool.receiveConnection(connection);	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	 }
	 
	public Employee getEmployee(int id) {
		 
		Employee employee = null;
		Connection connection = ConnectionPool.getConnection();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("select * from employee where id = ?");
			statement.setInt(1, id);
				
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				employee = new Employee();
				employee.setId(result.getInt(1));
				employee.setName(result.getString(2));
				employee.setSal(result.getDouble(3));
				employee.setContact(result.getLong(4));
			}
			statement.close();
			ConnectionPool.receiveConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return employee;
	}
	 
	public List<Employee> sortedEmployee(String columnName , String sortBy) {
		List<Employee> employees=new ArrayList<>();
		String query = null;
		try {
			Connection connection = ConnectionPool.getConnection();
			if(sortBy.equalsIgnoreCase("A")) {
				query = "select * from employee order by " + columnName +" asc";
			}
			else if(sortBy.equalsIgnoreCase("D")) {
				query = "select * from employee order by " + columnName +" desc";
			}
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Employee employee = new Employee();
				
				employee.setId(resultSet.getInt(1));
				employee.setName(resultSet.getString(2));
				employee.setSal(resultSet.getDouble(3));
				employee.setContact(resultSet.getLong(4));
				
				employees.add(employee);
			}
			statement.close();
			ConnectionPool.receiveConnection(connection);
		}catch(SQLException e) {
			e.printStackTrace();			
		}
		return employees;
	}
	
	public Employee updateEmployee(int newId, String name,double salary, long contact, int id) {
		try {
			Connection connection = ConnectionPool.getConnection();
			String query = "update employee set id = "+newId+", "
					+ "name = '"+name+"', "
					+ "salary = "+salary+", "
					+ "contact = "+contact+" "
					+ "where id = "+id;
			PreparedStatement statement = connection.prepareStatement(query);
//			int result = 
			statement.executeUpdate();
			
//			System.out.println("row updated "+result);			
			statement.close();
			ConnectionPool.receiveConnection(connection);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

//	public Employee updateEmployee(int newId, String name,double salary, long contact, int id, int choice,Employee employee) {
//		
//				try {
//					Connection connection = ConnectionPool.getConnection();
//					String query = "update employee set ";
//					switch(choice) {
//					case 5:{
//						query += "id = "+id+" where id = "+eid+";";
//						break;
//					}
//					}
//					PreparedStatement statement = connection.prepareStatement(query);
//				
//					int result = statement.executeUpdate();
//					
//					System.out.println("row updated "+result);			
//					statement.close();
//					ConnectionPool.receiveConnection(connection);
//					
//					return employee;
//				}catch(SQLException e) {
//					e.printStackTrace();
//				}
//		return null;
//		
//		
//	}

	public Employee updateEmployeeById(int newId, int id) {
		try {
			Connection connection = ConnectionPool.getConnection();
			String query = "update employee set id = "+ newId +" where id = "+ id;
			PreparedStatement statement = connection.prepareStatement(query);
//			int result = 
			statement.executeUpdate();
//			System.out.println("row updated "+result);
			
			statement.close();
			ConnectionPool.receiveConnection(connection);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
		
 	public Employee updateEmployeeByName(String name, int id) {
	
		try {
			Connection connection = ConnectionPool.getConnection();
			String query = "update employee set name = '"+ name +"' where id = "+id;
			PreparedStatement statement = connection.prepareStatement(query);
//			int result =
			statement.executeUpdate();	
//			System.out.println("row updated "+ result);	
			
			statement.close();
			ConnectionPool.receiveConnection(connection);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
 	public Employee updateEmployeeBySal(double sal, int id) {
 		try {
 			Connection connection = ConnectionPool.getConnection();
 			String query = "update employee set salary = "+ sal +" where id = "+ id;
 			PreparedStatement statement = connection.prepareStatement(query);
// 			int result = 
 			statement.executeUpdate();
// 			System.out.println("rows updated "+result); 			
 			
 			statement.close();
 			ConnectionPool.receiveConnection(connection);
 		}catch(SQLException e) {
 			e.printStackTrace();
 		}
		return null;
 	}
 	
 	public Employee updateEmployeeByContact(long contact, int id) {
 		try {
 			Connection connection = ConnectionPool.getConnection();
 			String query = "update employee set contact = "+ contact +" where id = "+ id;
 			PreparedStatement statement = connection.prepareStatement(query);
// 			int result = 
 			statement.executeUpdate();
// 			System.out.println("rows updated "+ result);
 			
 			statement.close();
 			ConnectionPool.receiveConnection(connection);
 		}catch(SQLException e) {
 			e.printStackTrace();
 		}
		return null;
 	}
}
 