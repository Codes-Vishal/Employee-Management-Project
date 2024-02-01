package edu.employee.view;

import java.util.Scanner;

import edu.employee.controller.Controller;
import edu.employee.model.Employee;


public class View {
	
	Scanner scanner=new Scanner(System.in);
	boolean loop=true;
	Controller controller = new Controller();
	
	public static void main(String[] args) {
		View view=new View();
		while(view.loop)
		{
			view.mainView();
		}
	}
	
	public void mainView() {
		
		System.out.print("==================WELCOME==================\n"
			+" SELECT OPERATION TO PERFORM\n"
			+" 1.SAVE EMPLOYEE\n"
			+" 2.GET EMPLOYEE\n"
			+" 3.GET ALL EMPLOYEE\n"
			+" 4.UPDATE\n"
			+" 5.DELETE EMPLOYEE\n"
			+" 6.EXIT\n"
			+"   YOUR CHOICE : ");
		int choice=scanner.nextInt();
		scanner.nextLine();
		switch(choice)
		{
	
		case 1:{
			saveEmployee();
			break;
		}
		case 2:{
			getEmployee();
			break; 
		}
		case 3:{
			getAllEmployee();
			break;
		}
		case 4:{
			updateEmployee();
			break;
		}
		case 5:{
			deleteEmployee();
			break;
		}
		case 6:{
			try {
				this.loop=false;
				exit();
				System.out.println("THANK YOU!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		}
		default:
			System.out.println("INVALID CHOICE PLEASE TRY AGAIN");
		}
	}
	
	public void saveEmployee() {
		
		System.out.print("Enter Employee id: ");
		int id=scanner.nextInt();
		scanner.nextLine();
		
	
		System.out.print("Enter Employee Name: ");
		String name=scanner.nextLine();
	

		System.out.print("Enter Employee Salary: ");
		double sal=scanner.nextDouble();
		scanner.nextLine();

		System.out.print("Enter Employee contact: ");
		long contact=scanner.nextLong();
		scanner.nextLine();
	
		Employee employee=new Employee(id,name,sal,contact);
		if(controller.saveEmployee(employee)!=null)
		{
			System.out.println("Employee saved\n");
		}
		else{
			System.out.println("Something went wrong");
		}
	}
	
	public void getEmployee() {
		
		System.out.print("Enter the user Id : ");
		
		int id = scanner.nextInt();
		Employee employee;
		
		if((employee = controller.getEmployee(id)) != null) {
			System.out.println("--------------------------------");
			
			System.out.println("EMPLOYEE ID: "+employee.getId());
			
			System.out.println("EMPLOYEE NAME: "+employee.getName());
			
			System.out.println("EMPLOYEE SALARY: "+employee.getSal());
			
			System.out.println("EMPLOYEE CONTACT: "+employee.getContact()+"\n");
		}
		else {
			System.out.println("Employee Not Found");
		}
	}
	
	public void getAllEmployee() {
			
		if(controller.getAllEmployee().isEmpty()) {
			System.out.println("Employees not found!\n");
		}
		sortEmployee();
		
//		for(Employee employee:controller.getAllEmployee()) {
//			System.out.println("--------------------------------");
//			System.out.println("Employee ID :"+employee.getId());
//			System.out.println("Employee Name :"+employee.getName());
//			System.out.println("Employee Salary :"+employee.getSal());
//			System.out.println("Employee Contact :"+employee.getContact());
//			}
		}
	
	public void deleteEmployee() {
		
		getAllEmployee();
		
		System.out.println("--------------------------------");
		
		System.out.print("Enter Employee id: ");
		int id=scanner.nextInt();
		scanner.nextLine();
	
		System.out.print("Enter Employee Name: ");
		String name=scanner.nextLine();
		
		System.out.print("Enter Employee Salary: ");
		double sal=scanner.nextDouble();
		scanner.nextLine();

		System.out.print("Enter Employee contact: ");
		long contact=scanner.nextLong();
		scanner.nextLine();
	
		Employee employee=new Employee(id,name,sal,contact);
		
		for(Employee employee1 : controller.getAllEmployee()) {
			if(id == employee.getId()) {
				if(employee1.getName().equalsIgnoreCase(employee.getName())) {
					if(employee1.getSal() == employee.getSal()) {
						if(employee1.getContact() == employee.getContact()) {
							controller.deleteEmployee(employee);
							System.out.println("EMPLOYEE DELETED");
							return;
						}
					}
				}
			}
		}
		System.out.println("!!!!!!Employee Not Found!!!!!!!");
	}
	
	public void updateEmployee() {
		
		getAllEmployee();
		
		System.out.println("--------------------------------");
		System.out.print("Enter the ID to Update : ");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.print(
				"SELECT OPERATION TO PERFORM\n"
				+" 1.ID\n"
				+" 2.NAME\n"
				+" 3.SALARY\n"
				+" 4.CONTACT\n"
				+" 5.UPDATE ALL\n"
				+"   YOUR CHOICE : ");
				
			int choice = scanner.nextInt();
			scanner.nextLine();
				
			switch(choice) {
				case 1 : {
					System.out.print("Enter Updated Employee id: ");
					int id1=scanner.nextInt();
					
					controller.updateEmployeeById(id1, id);
					
					System.out.println("ID is Updated!");
					break;
				}
				case 2 : {
					System.out.print("Enter Updated Employee Name: ");
					String name=scanner.nextLine();
					
					controller.updateEmployeeByName(name, id);
					
					System.out.println("Name is Updated!");
					break;
				}
				case 3 : {
					System.out.print("Enter Updated Employee Salary: ");
					double sal=scanner.nextDouble();
					
					controller.updateEmployeeBySal(sal, id);
					
					System.out.println("Salary is Updated!");
					scanner.nextLine();
					break;
				}
				case 4 : {	
					System.out.print("Enter Updated Employee contact: ");
					long contact=scanner.nextLong();
					
					controller.updateEmployeeByContact(contact, id);
					
					System.out.println("Contact is Updated!");
					scanner.nextLine();			
					break;
				}
				case 5 : {
					System.out.print("Enter Updated Employee Id: ");
					int id1=scanner.nextInt();
					scanner.nextLine();
					System.out.print("Enter Updated Employee Name: ");
					String name=scanner.nextLine();
					System.out.print("Enter Updated Employee Salary: ");
					double sal=scanner.nextDouble();
					scanner.nextLine();
					System.out.print("Enter Updated Employee contact: ");
					long contact=scanner.nextLong();
					scanner.nextLine();
					controller.updateEmployee(id1, name, sal, contact, id);
//					Employee employee = new Employee();
//					controller.updateEmployee(choice, id, employee );
					
					System.out.println("Employee Details Updated!");				
					break;
				}
				default:{
					System.out.println("Invalid Choice Please Try Again!!!");
				}
			}
	}	
	
	public void sortEmployee() {
		
		System.out.print("\nSELECT SORTING BASED ON LIKE \n"
				+ " TYPE 'A' ASCENDING \n"
				+ " TYPE 'D' DESCENDING \n"
				+ " YOUR CHOICE : ");
		String sort = scanner.next();
		System.out.print("\nSELECT SORTING BASED ON: \n"
		+"1.SORT BY ID\n"
		+"2.SORT BY NAME\n"
		+"3.SORT BY SALARY\n"
		+"4.SORT BY CONTACT\n"
		+"  YOUR CHOICE : ");
		int choice = scanner.nextInt();
		scanner.nextLine();
		System.out.println();
		switch(choice) {
		
			case 1:{
				for(Employee employee : controller.sortedEmployee("id", sort)) {
					System.out.println("Employee Id : "+employee.getId());
					System.out.println("Employee Name : "+employee.getName());
					System.out.println("Employee Salary : "+employee.getSal());
					System.out.println("Employee Contact : "+employee.getContact());
					System.out.println("--------------------------------");
				} 
				break;
			}
			case 2:{
				for(Employee employee : controller.sortedEmployee("name", sort)) {
					System.out.println("Employee Id : "+employee.getId());
					System.out.println("Employee Name : "+employee.getName());
					System.out.println("Employee Salary : "+employee.getSal());
					System.out.println("Employee Contact : "+employee.getContact());
					System.out.println("--------------------------------");
				}
				break;
			}
			case 3:{
				for(Employee employee : controller.sortedEmployee("salary", sort)) {
					System.out.println("Employee Id : "+employee.getId());
					System.out.println("Employee Name : "+employee.getName());
					System.out.println("Employee Salary : "+employee.getSal());
					System.out.println("Employee Contact : "+employee.getContact());
					System.out.println("--------------------------------");
				}				
				break;
			}
			case 4:{
				for(Employee employee : controller.sortedEmployee("contact", sort)) {
					System.out.println("Employee Id : "+employee.getId());
					System.out.println("Employee Name : "+employee.getName());
					System.out.println("Employee Salary : "+employee.getSal());
					System.out.println("Employee Contact : "+employee.getContact());
					System.out.println("--------------------------------");
				}				
				break;
			}
			default:{
				System.out.println("Invalid Choice");
			}
		}
	}

	public static void exit() throws InterruptedException {
		int i = 5;
		System.out.print("Exiting");
		while( i != 0) {
			System.out.print(".");
			Thread.sleep(400);
			i--;
		}
		System.out.println();
		
	}
}
