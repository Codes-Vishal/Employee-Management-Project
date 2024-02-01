package edu.employee.model;

public class Employee {
	private int id;
	private String name;
	private double sal;
	private long contact;
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getSal() {
		return sal;
	}
	
	public void setSal(double sal) {
		this.sal = sal;
	}
	
	public long getContact() {
		return contact;
	}
	
	public void setContact(long contact) {
		this.contact = contact;
	}
	
	public Employee(int id, String name, double sal, long contact) {
		super();
		this.id = id;
		this.name = name;
		this.sal = sal;
		this.contact = contact;
	}

	public Employee() {
		// TODO Auto-generated constructor stub
	}
	

}
