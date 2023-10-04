package oopconcepts.encapsulation;

//Encapsulate with private type AD and public method to get or set them
//Write-only/Read-only class

class Employee {
	private String id;
	private String name;
	private int age;
	
	public void employee() {
		System.out.println("Employee");
	}
	
	public Employee() {
		
	}

	public Employee(String id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}


public class Encapsulation {

}
