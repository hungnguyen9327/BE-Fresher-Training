package oopconcepts.polymorphism;

//1. refer parent class instance variable
//2. invoke parent class method
//3. invoke parent class constructor (code)

class Person {
	int id;  
	String name;
	
	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}  
	
}

class Employee extends Person {
	double salary;
	public Employee(int id, String name, double salary) {
		super(id, name);
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [salary=" + salary + ", id=" + id + ", name=" + name + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}  
	
}

public class SuperKeyword {
	public static void main(String args[]) {
		Employee e = new Employee(1, "Hung", 2000);  
		System.out.print(e.toString());;
	}
}
