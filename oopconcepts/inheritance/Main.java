package oopconcepts.inheritance;

public class Main {
	//Single Inheritance
	public static void example1() {
		Developer d = new Developer(1000);
		System.out.println("Salary: " + d.getSalary());
		System.out.println("Bonus: " + d.getBonus());
	}
	//Multilevel Inheritance
	public static void example2() {
		Developer d = new Developer(1000);
		d.dev();
		d.devTeam();
		d.employee();
	}
	//Hierarchical Inheritance
	public static void example3() {
		Developer d = new Developer(1000);
		Tester t = new Tester(1000);
		System.out.println("Dev Bonus: " + d.getBonus());
		System.out.println("Tester Bonus: " + t.getBonus());
	}
	
	public static void main(String[] args) {
		
	}

}
